package dev.fenix.customer.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.auth.SignUpInput
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.fold
import com.fenix.domain.use_case.auth.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SignUpEvent {
    data object NavigateToHome : SignUpEvent()
    data class Error(val reason: FailureReason) : SignUpEvent()
}

data class SignUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val signUp: SignUp,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<SignUpEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update { curr -> curr.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { curr -> curr.copy(password = value) }
    }

    fun onSubmit() {
        _uiState.update { curr -> curr.copy(isLoading = true) }

        viewModelScope.launch {
            val onSignUpEvent = signUp(
                SignUpInput(
                    email = _uiState.value.email,
                    password = _uiState.value.password,
                    firstName = "Cesar",
                    lastName = "Hernandez",
                )
            ).fold(
                onSuccess = { SignUpEvent.NavigateToHome },
                onFailure = { reason -> SignUpEvent.Error(reason) }
            )

            _event.send(onSignUpEvent)

            _uiState.update { curr -> curr.copy(isLoading = false) }
        }
    }
}