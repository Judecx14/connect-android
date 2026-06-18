package com.fenix.data.repository.location

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.fenix.domain.repository.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationRepositoryImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val geocoder: Geocoder
) : LocationRepository {

    private suspend fun getAddressFromLocation(
        latitude: Double,
        longitude: Double,
    ): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCancellableCoroutine { continuation ->
                geocoder.getFromLocation(latitude, longitude, 1,
                    object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: List<Address?>) {
                            val address = addresses.firstOrNull()?.getAddressLine(0)
                            if (continuation.isActive) {
                                continuation.resume(address ?: "Unknown :(")
                            }
                        }

                        override fun onError(errorMessage: String?) {
                            super.onError(errorMessage)
                            if (continuation.isActive) {
                                continuation.resume("Unknown :(")
                            }
                        }
                    }
                )
            }
        } else {
            withContext(Dispatchers.IO) {
                try {
                    geocoder.getFromLocation(latitude, longitude, 1)
                        ?.firstOrNull()
                        ?.getAddressLine(0) ?: "Unknown :("
                } catch (_: Exception) {
                    "Unknown :("
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun currentLocation(): String {
        val location = locationClient.lastLocation.await() ?: return "Unknown :("

        return getAddressFromLocation(
            latitude = location.latitude,
            longitude = location.longitude,
        )
    }
}