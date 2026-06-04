package dev.fenix.customer.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.domain.use_case.auth.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiEffect {
    data object SuccessLogout : HomeUiEffect()
    data object FailureLogout : HomeUiEffect()
}

@HiltViewModel
class HomeViewModel  @Inject constructor(
    val logout: Logout
) : ViewModel() {
    private val _effects = Channel<HomeUiEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onLogout() {
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