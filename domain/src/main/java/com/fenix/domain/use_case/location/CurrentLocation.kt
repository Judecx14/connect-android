package com.fenix.domain.use_case.location

import com.fenix.domain.repository.location.LocationRepository
import javax.inject.Inject

class CurrentLocation @Inject constructor(private val locationRepository: LocationRepository) {
    suspend operator fun invoke() : String {
        return locationRepository.currentLocation()
    }
 }