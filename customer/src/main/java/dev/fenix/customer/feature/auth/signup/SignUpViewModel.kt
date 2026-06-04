package dev.fenix.customer.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.auth.Credentials
import com.fenix.domain.use_case.auth.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SignUpEffect {
    data object NavigateToHome : SignUpEffect()
    data object Error : SignUpEffect()
}

data class SignUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val signUp: SignUp
) : ViewModel() {
    private val _uiState = MutableStateFlow<SignUiState>(SignUiState())
    val uiState = _uiState.asStateFlow()

    private val _effects = Channel<SignUpEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update { curr -> curr.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { curr -> curr.copy(password = value) }
    }

    fun onSubmit() {
        _uiState.update { curr -> curr.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val isSigned = signUp(
                Credentials(
                    email = _uiState.value.email,
                    password = _uiState.value.password
                )
            )

            if (isSigned) {
                _effects.send(SignUpEffect.NavigateToHome)
            } else {
                _effects.send(SignUpEffect.Error)
            }

            _uiState.update { curr -> curr.copy(isLoading = false) }
        }
    }
}