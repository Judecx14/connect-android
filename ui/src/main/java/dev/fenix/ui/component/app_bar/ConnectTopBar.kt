package dev.fenix.ui.component.app_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun ConnectTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .statusBarsPadding()
            .padding(ConnectTheme.dimensions.padding.normal)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        leading?.let { component ->
            Box(modifier = Modifier.align(Alignment.CenterStart)) {
                component()
            }
        }

        ConnectText(
            text = title,
            style = ConnectTextStyle.Title,
            align = TextAlign.Center
        )

        trailing?.let { component ->
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                component()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConnectTopBarPreview() {
    ConnectTheme {
        Column {
            // All
            ConnectTopBar(
                title = "Top Bar Title",
                leading = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Outlined
                    ) { color ->
                        ConnectText(text = "<", color = color)
                    }
                },
                trailing = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Ghost
                    ) { color ->
                        ConnectText(text = ":", color = color)
                    }
                }
            )
            // Only Title
            ConnectTopBar(
                title = "Top Bar Title",
            )
            // Without leading and title
            ConnectTopBar(
                title = "Top Bar Title",
                leading = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Outlined
                    ) { color ->
                        ConnectText(text = "<", color = color)
                    }
                },
            )
            // Without trailing and title
            ConnectTopBar(
                title = "Top Bar Title",
                trailing = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Ghost
                    ) { color ->
                        ConnectText(text = ":", color = color)
                    }
                },
            )
            // Without title
            ConnectTopBar(
                leading = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Outlined
                    ) { color ->
                        ConnectText(text = "<", color = color)
                    }
                },
                trailing = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Ghost
                    ) { color ->
                        ConnectText(text = ":", color = color)
                    }
                }
            )
            // Only leading
            ConnectTopBar(
                leading = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Outlined
                    ) { color ->
                        ConnectText(text = "<", color = color)
                    }
                },
            )
            // Only trailing
            ConnectTopBar(
                trailing = {
                    ConnectIconButton(
                        onClick = {},
                        variant = Variant.Ghost
                    ) { color ->
                        ConnectText(text = ":", color = color)
                    }
                }
            )
        }
    }
}

