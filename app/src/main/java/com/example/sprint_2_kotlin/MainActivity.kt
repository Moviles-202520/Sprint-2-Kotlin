package com.example.sprint_2_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.sprint_2_kotlin.ui.theme.Sprint2KotlinTheme
import com.example.sprint_2_kotlin.view.AuthScreen
import com.example.sprint_2_kotlin.view.NewsFeedScreen

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(
            androidx.compose.ui.platform.ComposeView(this).apply {
                setContent {
                    Sprint2KotlinTheme {
                        var loggedIn by remember { mutableStateOf(false) }
                        var showBiometricPrompt by remember { mutableStateOf(false) }

                        if (loggedIn && showBiometricPrompt.not()) {
                            showBiometricPrompt = true
                            showBiometricPrompt(
                                onSuccess = { showBiometricPrompt = false },
                                onFailure = { loggedIn = false }
                            )
                        }

                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            if (loggedIn && !showBiometricPrompt) {
                                NewsFeedScreen(modifier = Modifier.padding(innerPadding))
                            } else {
                                AuthScreen(onLoginSuccess = { loggedIn = true })
                            }
                        }
                    }
                }
            }
        )
    }

    private fun showBiometricPrompt(
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(this)

        val biometricManager = BiometricManager.from(this)
        val canAuthenticate = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG
                    or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )

        if (canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS) {
            onFailure()
            return
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock with Fingerprint")
            .setSubtitle("Authenticate to access your News Feed")
            .setNegativeButtonText("Cancel")
            .build()

        val biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onFailure()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailure()
                }
            }
        )

        biometricPrompt.authenticate(promptInfo)
    }
}


























/***package com.example.sprint_2_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import com.example.sprint_2_kotlin.ui.theme.Sprint2KotlinTheme
import com.example.sprint_2_kotlin.view.AuthScreen
import com.example.sprint_2_kotlin.view.NewsFeedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprint2KotlinTheme {
                var loggedIn by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (loggedIn) {
                        NewsFeedScreen(modifier = Modifier.padding(innerPadding))
                    } else {
                        AuthScreen(onLoginSuccess = { loggedIn = true })
                    }
                }
            }
        }
    }
}


//class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprint2KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsFeedScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}***/
