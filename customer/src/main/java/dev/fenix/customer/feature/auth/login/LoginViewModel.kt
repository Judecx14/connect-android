package dev.fenix.customer.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.auth.AuthCredentials
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.fold
import com.fenix.domain.use_case.auth.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

import dev.fenix.customer.feature.auth.login.form.LoginForm

data class LoginUiState(
    val isLoading: Boolean = false,
)

sealed class LoginUiEvent {
    data object Success : LoginUiEvent()
    data class Error(val reason: FailureReason) : LoginUiEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    val login: Login,
    val form: LoginForm
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<LoginUiEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    fun onSubmit() {
        if (!form.validate()) return
        
        viewModelScope.launch {
            _uiState.update { curr -> curr.copy(isLoading = true) }
            
            val onLoginEvent = login(
                AuthCredentials(
                    email = form.email.value,
                    password = form.password.value
                )
            ).fold(
                onSuccess = { LoginUiEvent.Success },
                onFailure = { reason -> LoginUiEvent.Error(reason) }
            )

            _event.send(onLoginEvent)
            
            _uiState.update { curr -> curr.copy(isLoading = false) }
        }
    }
}