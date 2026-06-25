package dev.fenix.customer

import androidx.lifecycle.ViewModel
import com.fenix.domain.model.auth.AuthState
import com.fenix.domain.model.resource.bind
import com.fenix.domain.use_case.auth.CurrentAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val currentAuthState: CurrentAuthState
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState = _authState.asStateFlow()

    init {
        checkCurrentAuthState()
    }

    private fun checkCurrentAuthState() {
        val state = currentAuthState().bind { _ ->
            _authState.update { AuthState.Unauthenticated }
            return
        }

        _authState.update { state }
    }
}
