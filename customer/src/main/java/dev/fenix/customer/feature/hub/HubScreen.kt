package dev.fenix.customer.feature.hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.fenix.ui.component.button.ConnectButton
import dev.fenix.ui.theme.ConnectTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
private fun Content(
    logout: () -> Unit,
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ConnectButton(
                label = "Logout",
                onClick = logout
            )
        }
    }
}


@Composable
fun HubScreen(
    hubViewModel: HubViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {
    LaunchedEffect(Unit) {
        hubViewModel.effects.collectLatest { effect ->
            when (effect) {
                HomeUiEffect.FailureLogout -> {}
                HomeUiEffect.SuccessLogout -> navigateToLogin()
            }
        }
    }

    Content(logout = hubViewModel::doLogout)
}

@Preview
@Composable
private fun HubScreenPreview() {
    ConnectTheme {
        Content(
             logout = {}
        )
    }

}