package dev.fenix.customer.feature.auth.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class FormState(
    val email: String = "",
    val password: String = ""
)

class SignUpViewModel : ViewModel() {
    private val _formState = MutableStateFlow(FormState())
    val formState = _formState.asStateFlow()

    fun onEmailChange(value: String) {
        _formState.update { curr -> curr.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _formState.update { curr -> curr.copy(password = value) }
    }

    fun onSubmit() {
        // TODO implementation
    }
 }