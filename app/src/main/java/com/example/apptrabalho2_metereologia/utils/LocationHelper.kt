package com.example.apptrabalho2_metereologia.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/** ChatGPT - início
 * Prompt: get latitude and longitude from phone gps
 *
 */
class LocationHelper(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location? {
        Log.d("LocationHelper", "Iniciando busca de localização")
        return suspendCancellableCoroutine { cont ->
            fusedLocationClient.getCurrentLocation(
                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Log.d("LocationHelper", "Localização obtida com sucesso: ${location.latitude}, ${location.longitude}")
                } else {
                    Log.e("LocationHelper", "Localização retornou null")
                }
                cont.resume(location)
            }.addOnFailureListener { exception ->
                Log.e("LocationHelper", "Erro ao obter localização: ${exception.message}", exception)
                cont.resumeWithException(exception)
            }
        }
    }
}
/** ChatGPT - final */