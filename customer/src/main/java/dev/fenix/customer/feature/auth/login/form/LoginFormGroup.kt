package dev.fenix.customer.feature.auth.login.form

import android.util.Patterns
import dev.fenix.ui.core.form.FormControl
import dev.fenix.ui.core.form.FormGroup
import javax.inject.Inject

class LoginFormGroup @Inject constructor() : FormGroup() {

    val email = FormControl(
        initialValue = "",
        validators = listOf(
            { value -> if (value.isBlank()) LoginFormControlError.Empty else null },
            { value -> if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) LoginFormControlError.InvalidEmail else null }
        ),
        validateOnChange = true
    )

    val password = FormControl(
        initialValue = "",
        validators = listOf(
            { value -> if (value.isBlank()) LoginFormControlError.Empty else null }
        ),
        validateOnChange = true
    )

    override val controls: List<FormControl<*, *>> = listOf(email, password)
}