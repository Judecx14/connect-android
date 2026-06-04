package dev.fenix.customer

import androidx.lifecycle.ViewModel
import com.fenix.domain.model.auth.AuthState
import com.fenix.domain.use_case.auth.CurrentAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel  @Inject constructor(
    val currentAuthState: CurrentAuthState
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState = _authState.asStateFlow()

    init {
        checkCurrentAuthState()
    }

    private fun checkCurrentAuthState() {
        val authState = currentAuthState()
        _authState.value = authState
    }
}
