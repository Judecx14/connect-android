package dev.fenix.customer.feature.hub

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.use_case.auth.CurrentLocation
import com.fenix.domain.use_case.auth.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiEffect {
    data object SuccessLogout : HomeUiEffect()
    data object FailureLogout : HomeUiEffect()
}

@HiltViewModel
class HubViewModel @Inject constructor(
    private val currentLocation: CurrentLocation,
    private val logout: Logout
) : ViewModel() {
    private val _effects = Channel<HomeUiEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    private val _location = MutableStateFlow("...")
    val location = _location.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _location.update {
                currentLocation()
            }
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            val result = logout()

            if (result) {
                _effects.send(HomeUiEffect.SuccessLogout)
            } else {
                _effects.send(HomeUiEffect.FailureLogout)
            }
        }
    }

}