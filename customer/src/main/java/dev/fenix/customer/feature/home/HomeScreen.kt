package dev.fenix.customer.feature.home

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
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    LaunchedEffect(Unit) { 
        homeViewModel.effects.collectLatest { effect -> 
            when (effect) {
                HomeUiEffect.FailureLogout -> { }
                HomeUiEffect.SuccessLogout -> navigateToLogin()
            }
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ConnectButton(
                label = "Logout",
                onClick = homeViewModel::onLogout
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ConnectTheme {
        HomeScreen(
            navigateToLogin = {}
        )
    }

}