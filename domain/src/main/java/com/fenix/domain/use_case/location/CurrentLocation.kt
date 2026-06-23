package com.fenix.domain.use_case.location

import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.repository.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class CurrentLocation @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(): Flow<Resource<String, FailureReason>> {
        return locationRepository.currentLocation()
            .distinctUntilChanged { old, new ->
                when (old) {
                    is Resource.Success if new is Resource.Success -> old.data == new.data

                    is Resource.Failure if new is Resource.Failure -> old.reason == new.reason

                    else -> false
                }
            }
    }
}