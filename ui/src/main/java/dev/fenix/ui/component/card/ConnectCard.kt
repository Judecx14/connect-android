package dev.fenix.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.theme.token.color_scheme.Colors

@Composable
fun ConnectCard(
    modifier: Modifier = Modifier,
    icon: ConnectIcons,
    title: String,
    description: String
) {
    val contentColor = Colors.Light.onSecondary

    Box(
        modifier = modifier
            .height(ConnectTheme.dimensions.height.large)
            .clip(shape = ConnectTheme.shapes.large)
            .background(ConnectTheme.colors.secondaryContainer)
            .padding(
                horizontal = ConnectTheme.dimensions.padding.normal,
                vertical = ConnectTheme.dimensions.padding.small
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            ConnectIconButton(
                enabled = false,
                onClick = { },
            ) {
                ConnectIcon(icon = icon, tint = contentColor)
            }
            ConnectText(
                text = title,
                color = contentColor,
                style = ConnectTextStyle.Label
            )
            ConnectText(
                text = description, 
                color = contentColor.copy(alpha = 0.5f)
            )
        }
    }
}


@Preview
@Composable
private fun ConnectCardPreview() {
    ConnectTheme {
        ConnectCard(
            icon = ConnectIcons.Bolt,
            title = "Title",
            description = "Description"
        )
    }
}