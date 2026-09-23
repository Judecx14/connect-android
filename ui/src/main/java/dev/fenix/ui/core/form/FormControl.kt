package dev.fenix.ui.core.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
/**
 * Type alias for a validator function. 
 * Takes a value [V] and returns an error [E] if invalid, or null if valid.
 */
typealias Validator<V, E> = (V) -> E?

/**
 * Represents a single form field.
 * Manages its own StateFlow, value, errors, and validation logic.
 */
class FormControl<V, E>(
    val initialValue: V,
    private val validators: List<Validator<V, E>> = emptyList(),
    private val validateOnChange: Boolean = false,
) {
    var value: V by mutableStateOf(initialValue)
        private set

    var error: E? by mutableStateOf(null)
        private set

    fun onChange(entry: V) {
        this.value = entry
        this.error = null

        if (validateOnChange) {
            validate()
        }
    }

    fun reset() {
        this.value = initialValue
        this.error = null
    }

    fun validate(): Boolean {
        for (validator in validators) {
            val validationError = validator(value)
            if (validationError != null) {
                this.error = validationError
                return false
            }
        }

        this.error = null
        return true
    }
}