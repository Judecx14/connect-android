package dev.fenix.customer.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.auth.AuthCredentials
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.use_case.auth.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)

sealed class LoginUiEvent {
    data object Success : LoginUiEvent()
    data class Error(val reason: FailureReason) : LoginUiEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    val login: Login
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<LoginUiEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update { curr -> curr.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { curr -> curr.copy(password = value) }
    }

    fun onSubmit() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val isLogged = login(
                AuthCredentials(
                    email = _uiState.value.email,
                    password = _uiState.value.password
                )
            )

            if (isLogged) {
                _event.send(LoginUiEvent.Success)
            } else {
                _event.send(LoginUiEvent.Error)
            }
        }
    }
}