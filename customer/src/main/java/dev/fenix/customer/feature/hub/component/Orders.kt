package dev.fenix.customer.feature.hub.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun EmptyOrdes(
    modifier: Modifier = Modifier
) {
    val contentColor = ConnectTheme.colors.onSurface.copy(alpha = 0.5f)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        ConnectText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = ConnectTheme.dimensions.padding.medium,
                ),
            text = "Sin pedidos activos",
            align = TextAlign.Center,
            style = ConnectTextStyle.Label,
            color = contentColor
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    vertical = ConnectTheme.dimensions.padding.normal
                ),
            contentAlignment = Alignment.Center
        ) {
            ConnectIcon(
                modifier = Modifier
                    .width(ConnectTheme.dimensions.icon.xxl)
                    .height(ConnectTheme.dimensions.icon.xxl),
                icon = ConnectIcons.StickyNotePlus,
                tint = contentColor
            )
        }

        ConnectText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = ConnectTheme.dimensions.padding.medium,
                ),
            align = TextAlign.Center,
            text =
                "No cuentas con pedidos en curso en este momento. Para visualizar información, es necesario que generes un pedido",
            style = ConnectTextStyle.Body,
            color = contentColor
        )
    }
}

@Composable
fun Orders(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ConnectText(
            modifier = Modifier.padding(
                horizontal = ConnectTheme.dimensions.padding.medium,
                vertical = ConnectTheme.dimensions.padding.large
            ),
            text = "Pedidos activos",
            style = ConnectTextStyle.Headline,
        )

        EmptyOrdes(
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OrdersPreview() {
    ConnectTheme {
        Orders()
    }
}