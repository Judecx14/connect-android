package dev.fenix.customer.feature.auth.signup.form

import android.util.Patterns
import dev.fenix.ui.core.form.FormControl
import dev.fenix.ui.core.form.FormGroup

import javax.inject.Inject

class SignUpFormGroup @Inject constructor() : FormGroup() {

    val email = FormControl(
        initialValue = "",
        validators = listOf(
            { value -> if (value.isBlank()) SignUpFormControlError.Empty else null },
            { value -> if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) SignUpFormControlError.InvalidEmail else null }
        ),
        validateOnChange = true
    )

    val password = FormControl(
        initialValue = "",
        validators = listOf(
            { value -> if (value.isBlank()) SignUpFormControlError.Empty else null },
            { value -> if (value.length < 6) SignUpFormControlError.PasswordTooShort else null }
        ),
        validateOnChange = true
    )

    override val controls: List<FormControl<*, *>> = listOf(email, password)
}