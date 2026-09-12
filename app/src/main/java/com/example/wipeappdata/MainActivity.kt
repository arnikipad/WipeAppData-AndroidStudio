package com.example.wipeappdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                WipeAppDataScreen(
                    onWipe = {
                        // Clears this app's private files and cache.
                        // It does not erase the phone or other apps.
                        filesDir.deleteRecursively()
                        cacheDir.deleteRecursively()
                        externalCacheDir?.deleteRecursively()

                        // Clear this sample app's preferences.
                        getSharedPreferences(
                            "app_settings",
                            MODE_PRIVATE
                        ).edit().clear().apply()

                        // If your real app has databases, delete them here:
                        // deleteDatabase("my_database.db")
                    }
                )
            }
        }
    }
}

@Composable
private fun WipeAppDataScreen(onWipe: () -> Unit) {
    var showConfirmation by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("App data is currently stored.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Wipe App Data",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "This clears data belonging to this app only. " +
                    "It does not factory-reset the phone."
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { showConfirmation = true }
        ) {
            Text("Wipe App Data")
        }

        Spacer(Modifier.height(16.dp))

        Text(message)

        if (showConfirmation) {
            AlertDialog(
                onDismissRequest = { showConfirmation = false },
                title = { Text("Wipe this app's data?") },
                text = {
                    Text(
                        "This will delete this app's private files, cache, " +
                        "and saved settings."
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            onWipe()
                            showConfirmation = false
                            message = "App data has been cleared."
                        }
                    ) {
                        Text("Wipe")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showConfirmation = false }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
