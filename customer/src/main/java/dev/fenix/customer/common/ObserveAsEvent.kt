package dev.fenix.customer.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> ObserveAsEvent(
    flow: Flow<T>,
    onEvent: (T) -> Unit,
    key: Any? = Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            flow.collect { event -> onEvent(event)  }
        }
    }
}