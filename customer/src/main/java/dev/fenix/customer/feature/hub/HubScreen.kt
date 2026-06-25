package dev.fenix.customer.feature.hub

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.fenix.customer.common.ObserveFlowAsEvent
import dev.fenix.customer.feature.hub.component.BottomBar
import dev.fenix.customer.feature.hub.component.Categories
import dev.fenix.customer.feature.hub.component.Orders
import dev.fenix.customer.feature.hub.component.TopBar
import dev.fenix.ui.modifier.ambient_glow.ambientGlow
import dev.fenix.ui.modifier.ambient_glow.model.Position
import dev.fenix.ui.theme.ConnectTheme

@Composable
private fun Content(
    currentLocation: String,
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
        topBar = { TopBar(currentLocation = currentLocation, logout = logout) },
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
    val currentLocation by hubViewModel.location.collectAsStateWithLifecycle()

    ObserveFlowAsEvent(
        flow = hubViewModel.event
    ) { event ->
        when (event) {
            is HomeUiEvent.FailureLogout -> {}
            is HomeUiEvent.SuccessLogout -> navigateToLogin()
            is HomeUiEvent.FailureGetLocation -> {}
        }
    }

    Content(
        currentLocation = currentLocation ?: "",
        logout = hubViewModel::doLogout
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HubScreenPreviewLight() {
    ConnectTheme {
        Content(
            currentLocation = "Street #123 Boulevard Example between",
            logout = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HubScreenPreviewNight() {
    ConnectTheme {
        Content(
            currentLocation = "Street #123 Boulevard Example between",
            logout = {}
        )
    }
}