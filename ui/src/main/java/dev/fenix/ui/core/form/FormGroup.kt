package dev.fenix.ui.core.form

/**
 * Base class for a form.
 * Holds a collection of FormControls and provides global form operations.
 */
abstract class FormGroup {
    /**
     * You must provide a list of all the controls in this form.
     */
    protected abstract val controls: List<FormControl<*, *>>

    /**
     * Validates all controls in the group.
     * Note: Uses map to ensure ALL controls run their validate() method, 
     * so all errors appear on screen simultaneously.
     */
    fun validate(): Boolean {
        return controls.map { control -> control.validate() }.all { isValid -> isValid }
    }

    /**
     * Resets all controls in the group to their initial values.
     */
    fun reset() {
        controls.forEach { control -> control.reset() }
    }
}