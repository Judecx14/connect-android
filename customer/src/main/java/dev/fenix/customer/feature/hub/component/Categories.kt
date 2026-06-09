package dev.fenix.customer.feature.hub.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.card.ConnectCard
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.theme.token.color_scheme.Colors


@Composable
fun Categories(
    modifier: Modifier = Modifier
) {
    val contentColor = Colors.Dark.onSurface

    Column(modifier = modifier) {
        ConnectText(
            modifier = Modifier.padding(
                horizontal = ConnectTheme.dimensions.padding.medium,
                vertical = ConnectTheme.dimensions.padding.large
            ),
            text = "Categorias",
            color = contentColor,
            style = ConnectTextStyle.Headline,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ConnectTheme.dimensions.padding.normal),
            horizontalArrangement = Arrangement.spacedBy(
                ConnectTheme.dimensions.padding.normal
            )
        ) {
            ConnectCard(
                modifier = Modifier.weight(1f),
                icon = ConnectIcons.Zap,
                title = "Pedido rapido",
                description = "Menos de 30 minutos"
            )
            ConnectCard(
                modifier = Modifier.weight(1f),
                icon = ConnectIcons.Store,
                title = "Mandado",
                description = "Solicita un pedido mas complejo"
            )
        }
    }
}

@Preview
@Composable
private fun CategoriesPreview() {
    ConnectTheme {
        Categories()
    }
}