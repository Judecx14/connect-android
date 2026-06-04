package dev.fenix.customer.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.button.ConnectButton
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun HomeScreen() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ConnectButton(
                label = "Crash",
                onClick = {
                    throw RuntimeException("Test crash")
                }
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ConnectTheme {
        HomeScreen()
    }

}