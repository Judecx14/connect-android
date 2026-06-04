package dev.fenix.ui.component.field

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.component.text.ConnectText

@Composable
fun ConnectField(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    placeholder: String? = null,
    leading: @Composable ((color: Color) -> Unit)? = null,
    trailing: @Composable ((color: Color) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.normal)
    ) {
        label?.let { text ->
            ConnectText(text = text)
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,

            value = value,
            onValueChange = { change -> onChange(change) },

            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,

                    placeholder = placeholder?.let { text ->
                        { ConnectText(text = text) }
                    },

                    leadingIcon = leading?.let { component ->
                        {
                            component(ConnectTheme.colors.primary)
                        }
                    },
                    trailingIcon = trailing?.let { component ->
                        {
                            component(ConnectTheme.colors.primary)
                        }
                    },

                    shape = ConnectTheme.shapes.medium,
                    colors = colors
                )
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun ConnectFieldPreview() {
    ConnectTheme {
        Column(verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.normal)) {
            ConnectField(
                value = "", placeholder = "Write your email"
            ) {}

            ConnectField(
                label = "Password",
                value = "password",
                visualTransformation = PasswordVisualTransformation()
            ) {}
        }
    }
}