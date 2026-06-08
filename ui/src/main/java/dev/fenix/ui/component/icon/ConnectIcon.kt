package dev.fenix.ui.component.icon

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.icon.type.ConnectIcons

@Composable
fun ConnectIcon(
    modifier: Modifier = Modifier,
    icon: ConnectIcons,
    tint: Color = LocalContentColor.current,
    contentDescription: String = "",
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = icon.resId),
        contentDescription = contentDescription,
        tint = tint
    )
}


@Preview(showBackground = true)
@Composable
private fun ConnectIconPreview() {
    ConnectIcon(icon = ConnectIcons.ChevronLeft)
}