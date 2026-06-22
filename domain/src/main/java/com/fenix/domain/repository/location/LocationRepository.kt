package com.fenix.domain.repository.location

interface LocationRepository {
   suspend fun currentLocation() : String
}