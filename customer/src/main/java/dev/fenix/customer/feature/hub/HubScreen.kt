package dev.fenix.customer.feature.hub

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.fenix.customer.feature.hub.component.BottomBar
import dev.fenix.customer.feature.hub.component.Categories
import dev.fenix.customer.feature.hub.component.Orders
import dev.fenix.customer.feature.hub.component.TopBar
import dev.fenix.ui.modifier.ambient_glow.ambientGlow
import dev.fenix.ui.modifier.ambient_glow.model.Position
import dev.fenix.ui.theme.ConnectTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
private fun Content(
    logout: () -> Unit,
) {
    val colorPrimary = ConnectTheme.colors.primary

    Scaffold(
        modifier = Modifier
            .ambientGlow(background = ConnectTheme.colors.background) {
                spot(
                    color = colorPrimary,
                    x = Position.Start,
                    y = Position.Start,
                    ratio = 1.25f
                )

                spot(
                    color = colorPrimary,
                    x = Position.End,
                    y = Position.Start,
                    ratio = 0.65f
                )
            },
        containerColor = Color.Transparent,
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Categories()

            Orders()
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HubScreenPreviewLight() {
    ConnectTheme {
        Content(
            logout = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HubScreenPreviewNight() {
    ConnectTheme {
        Content(
            logout = {}
        )
    }
}