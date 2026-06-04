package dev.fenix.customer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fenix.domain.model.auth.AuthState
import dagger.hilt.android.AndroidEntryPoint
import dev.fenix.customer.core.navigation.Navigator
import dev.fenix.customer.core.navigation.Route
import dev.fenix.ui.theme.ConnectTheme
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private fun setupUi() {
        enableEdgeToEdge()
        setContent {
            ConnectTheme {
                val authState by mainViewModel.authState.collectAsStateWithLifecycle()

                when (authState) {
                    AuthState.Authenticated -> Navigator(startRoute = Route.Home)
                    AuthState.Unauthenticated -> Navigator(startRoute = Route.Login)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUi()
    }
}