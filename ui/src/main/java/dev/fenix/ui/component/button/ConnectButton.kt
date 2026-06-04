package dev.fenix.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.component.button.colors.buildButtonColors
import dev.fenix.ui.core.type.Emphasis
import dev.fenix.ui.component.button.type.Variant


@Composable
private fun ConnectButtonContent(
    modifier: Modifier = Modifier,
    label: String,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    color: Color
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leading?.invoke()

        // TODO: Change by our button text
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            textAlign = TextAlign.Center,
            style = ConnectTheme.typography.labelLarge,
            color = color
        )

        trailing?.invoke()
    }
}

@Composable
fun ConnectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    leading: @Composable ((color: Color) -> Unit)? = null,
    trailing: @Composable ((color: Color) -> Unit)? = null,
    enabled: Boolean = true,
    emphasis: Emphasis = Emphasis.Primary,
    variant: Variant = Variant.Filled,
) {
    val buttonColors = buildButtonColors(variant = variant, emphasis = emphasis)
    val shape = ConnectTheme.shapes.medium

    val setupModifier = modifier
        .height(ConnectTheme.dimensions.button.medium)
        .fillMaxWidth()

    when (variant) {
        Variant.Filled -> {
            Button(
                modifier = setupModifier,
                onClick = onClick,
                enabled = enabled,
                shape = shape,
                colors = buttonColors
            ) {
                ConnectButtonContent(
                    label = label,
                    leading = {
                        leading?.invoke(buttonColors.contentColor)
                    },
                    trailing = {
                        trailing?.invoke(buttonColors.contentColor)
                    },
                    color = buttonColors.contentColor
                )
            }
        }

        Variant.Outlined -> {
            OutlinedButton(
                modifier = setupModifier,
                onClick = onClick,
                enabled = enabled,
                shape = shape,
                colors = buttonColors
            ) {
                ConnectButtonContent(
                    label = label,
                    leading = {
                        leading?.invoke(buttonColors.contentColor)
                    },
                    trailing = {
                        trailing?.invoke(buttonColors.contentColor)
                    },
                    color = buttonColors.contentColor
                )
            }
        }

        Variant.Ghost -> {
            TextButton(
                modifier = setupModifier,
                onClick = onClick,
                enabled = enabled,
                shape = shape,
                colors = buttonColors
            ) {
                ConnectButtonContent(
                    label = label,
                    leading = {
                        leading?.invoke(buttonColors.contentColor)
                    },
                    trailing = {
                        trailing?.invoke(buttonColors.contentColor)
                    },
                    color = buttonColors.contentColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryPreview() {
    ConnectTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ConnectTheme.dimensions.padding.normal),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ConnectButton(
                onClick = {},
                label = "Primary",
                leading = { Text("L") },
                trailing = { Text("R") }
            )

            ConnectButton(
                onClick = {},
                label = "Primary Outlined",
                leading = { Text("L") },
                trailing = { Text("R") },
                variant = Variant.Outlined
            )


            ConnectButton(
                onClick = {},
                label = "Primary Ghost",
                leading = { Text("L") },
                trailing = { Text("R") },
                variant = Variant.Ghost
            )

            // Secondary

            ConnectButton(
                onClick = {},
                label = "Primary",
                leading = { Text("L") },
                trailing = { Text("R") },
                emphasis = Emphasis.Secondary
            )

            ConnectButton(
                onClick = {},
                label = "Primary Outlined",
                leading = { Text("L") },
                trailing = { Text("R") },
                variant = Variant.Outlined,
                emphasis = Emphasis.Secondary
            )


            ConnectButton(
                onClick = {},
                label = "Primary Ghost",
                leading = { Text("L") },
                trailing = { Text("R") },
                variant = Variant.Ghost,
                emphasis = Emphasis.Secondary
            )


        }
    }
}