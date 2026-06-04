package dev.fenix.customer.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.auth.Credentials
import com.fenix.domain.use_case.auth.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

data class FormState(
    val email: String = "",
    val password: String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    val login: Login
) : ViewModel() {
    private val _formState = MutableStateFlow(FormState())
    val formState = _formState.asStateFlow()

    fun onEmailChange(value: String) {
        _formState.update { curr -> curr.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _formState.update { curr -> curr.copy(password = value) }
    }

    fun onSubmit() {
        viewModelScope.launch(context = Dispatchers.IO) {
            login(
                Credentials(
                    email = _formState.value.email,
                    password = _formState.value.password
                )
            )
        }
    }
}