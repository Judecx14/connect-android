package dev.fenix.ui.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.component.text.type.ConnectTextStyle

@Composable
fun ConnectText(
    modifier: Modifier = Modifier,
    text: String,
    style: ConnectTextStyle = ConnectTextStyle.Body,
    color: Color = ConnectTheme.colors.onSurface,
    align: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
) {
    val typography = when (style) {
        ConnectTextStyle.Display -> ConnectTheme.typography.displayLarge
        ConnectTextStyle.Headline -> ConnectTheme.typography.headlineLarge
        ConnectTextStyle.Title -> ConnectTheme.typography.titleLarge
        ConnectTextStyle.Label -> ConnectTheme.typography.labelLarge
        ConnectTextStyle.Body -> ConnectTheme.typography.bodyLarge
    }

    Text(
        modifier = modifier,
        text = text,
        style = typography,
        color = color,
        overflow = overflow,
        maxLines = maxLines,
        textAlign = align,
    )
}


@Preview(showBackground = true)
@Composable
private fun ConnectTextPreview() {
    ConnectTheme {
        Column(verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.large)) {
            ConnectText(text = "Display example", style = ConnectTextStyle.Display)

            ConnectText(text = "Headline example", style = ConnectTextStyle.Headline)

            ConnectText(text = "Title example", style = ConnectTextStyle.Title)

            ConnectText(text = "Label example", style = ConnectTextStyle.Label)

            ConnectText(text = "Body example", style = ConnectTextStyle.Body)

            ConnectText(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus eget venenatis nunc, at hendrerit purus. Fusce magna erat, suscipit ac mattis id, scelerisque vel velit. Maecenas posuere turpis ac mollis semper. Suspendisse sagittis rhoncus libero, nec vulputate quam pulvinar dictum. Nunc imperdiet gravida risus, non ornare dolor vulputate auctor. Praesent ultricies feugiat odio. In efficitur non velit non mattis. Vivamus id congue lacus. Cras est lectus, fringilla sit amet luctus vel, faucibus quis risus.",
                style = ConnectTextStyle.Body,
                maxLines = 1
            )
        }

    }
}