package dev.fenix.customer.feature.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.use_case.location.CurrentLocation
import com.fenix.domain.use_case.auth.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiEffect {
    data object SuccessLogout : HomeUiEffect()
    data object FailureLogout : HomeUiEffect()
    data class FailureGetLocation(val reason: FailureReason) : HomeUiEffect()
}

@HiltViewModel
class HubViewModel @Inject constructor(
    currentLocation: CurrentLocation,
    private val logout: Logout
) : ViewModel() {
    private val _effects = Channel<HomeUiEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow(

    )
    val location: StateFlow<String?> = currentLocation()
        .onEach { resource ->
            if (resource is Resource.Failure) {
                _effects.send(
                    HomeUiEffect.FailureGetLocation(resource.reason)
                )
            }
        }.map { resource ->
            if (resource is Resource.Success) {
                resource.data
            } else {
                null
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = null
        )


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