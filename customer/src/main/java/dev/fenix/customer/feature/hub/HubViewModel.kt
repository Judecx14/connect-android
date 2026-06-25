package dev.fenix.customer.feature.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.fold
import com.fenix.domain.model.resource.getOrNull
import com.fenix.domain.model.resource.onFail
import com.fenix.domain.use_case.location.CurrentLocation
import com.fenix.domain.use_case.auth.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiEvent {
    data object SuccessLogout : HomeUiEvent()
    data class FailureLogout(val reason: FailureReason) : HomeUiEvent()
    data class FailureGetLocation(val reason: FailureReason) : HomeUiEvent()
}

@HiltViewModel
class HubViewModel @Inject constructor(
    currentLocation: CurrentLocation, private val logout: Logout
) : ViewModel() {
    private val _event = Channel<HomeUiEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow(

    )
    val location: StateFlow<String?> = currentLocation()
        .onEach { resource ->
            resource.onFail { failure ->
                _event.send(
                    HomeUiEvent.FailureGetLocation(failure.reason)
                )
            }
        }
        .map { resource -> resource.getOrNull() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = null
        )


    fun doLogout() {
        viewModelScope.launch {
            val onLogoutEvent = logout().fold(
                onSuccess = { HomeUiEvent.SuccessLogout },
                onFailure = { reason -> HomeUiEvent.FailureLogout(reason) }
            )

            _event.send(onLogoutEvent)
        }
    }

}