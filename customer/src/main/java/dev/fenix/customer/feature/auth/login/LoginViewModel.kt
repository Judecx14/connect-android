package dev.fenix.customer.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.auth.Credentials
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

sealed class LoginUiEffect {
    data object Success : LoginUiEffect()
    data object Error : LoginUiEffect()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    val login: Login
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _effects = Channel<LoginUiEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update { curr -> curr.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { curr -> curr.copy(password = value) }
    }

    fun onSubmit() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val isLogged = login(
                Credentials(
                    email = _uiState.value.email,
                    password = _uiState.value.password
                )
            )

            if (isLogged) {
                _effects.send(LoginUiEffect.Success)
            } else {
                _effects.send(LoginUiEffect.Error)
            }
        }
    }
}