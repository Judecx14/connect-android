package dev.fenix.customer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import dev.fenix.customer.core.navigation.Navigator
import dev.fenix.ui.theme.ConnectTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private fun setupUi()  {
        enableEdgeToEdge()
        setContent {
            ConnectTheme {
                Navigator()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUi()
    }
}