package com.fenix.domain.repository.auth

interface LocationRepository {
   suspend fun currentLocation() : String
}