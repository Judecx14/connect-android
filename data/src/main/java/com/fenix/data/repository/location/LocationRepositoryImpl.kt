package com.fenix.data.repository.location

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Looper
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.repository.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
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
    ): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCancellableCoroutine { continuation ->
                geocoder.getFromLocation(
                    latitude, longitude, 1,
                    object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: List<Address?>) {
                            val address = addresses.firstOrNull()?.getAddressLine(0)

                            if (continuation.isActive && address != null) {
                                continuation.resume(address)
                            }
                        }

                        override fun onError(errorMessage: String?) {
                            super.onError(errorMessage)

                            if (continuation.isActive) {
                                continuation.resume(null)
                            }
                        }
                    }
                )
            }
        } else {
            withContext(Dispatchers.IO) {
                val result = runCatching {
                    geocoder.getFromLocation(latitude, longitude, 1)
                        ?.firstOrNull()
                        ?.getAddressLine(0)
                }

                if (result.isFailure) return@withContext null

                result.getOrNull()
            }
        }
    }


    // TODO Request permission
    @SuppressLint("MissingPermission")
    override fun currentLocation(): Flow<Resource<String, FailureReason>> = callbackFlow {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            5000L
        ).build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)

                result.lastLocation?.let { location ->
                    launch {
                        val address = getAddressFromLocation(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )

                        if (address != null) {
                            trySend(Resource.Success(address))
                        } else {
                            trySend(Resource.Failure(FailureReason.Hardware.GpsDisabled))
                        }

                    }
                }
            }
        }

        locationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            locationClient.removeLocationUpdates(locationCallback)
        }
    }
}