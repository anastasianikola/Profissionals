package com.example.matulewithstyle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matulewithstyle.activity.Home
import com.example.matulewithstyle.activity.SignIn
import com.example.matulewithstyle.ui.theme.MatuleWithStyleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleWithStyleTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "signin" // Указываем начальный экран
                ) {
                    // Экран входа
                    composable("signin") {
                        SignIn(navController) // Передаем NavController для навигации
                    }

                    // Экран регистрации
                    composable("home") {
                        Home(navController)
                    }
                }
            }
        }
    }
}
