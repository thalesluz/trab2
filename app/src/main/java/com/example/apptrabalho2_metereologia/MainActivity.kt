package com.example.apptrabalho2_metereologia

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.apptrabalho2_metereologia.ui.feature.WeatherRoute
import com.example.apptrabalho2_metereologia.ui.theme.AppTrabalho2MetereologiaTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
/** ChatGPT - início
 * Prompt: get latitude and longitude from phone gps
 *
 */
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            getLocation()
        } else {
            Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show()
        }
    }
/** ChatGPT - final */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTrabalho2MetereologiaTheme {
                WeatherRoute()
            }
        }
/** ChatGPT - início
 * Prompt: get latitude and longitude from phone gps
 *
 */
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun getLocation() {
        Toast.makeText(this, "Acessando localização...", Toast.LENGTH_SHORT).show()
    }
/** ChatGPT - final */
}