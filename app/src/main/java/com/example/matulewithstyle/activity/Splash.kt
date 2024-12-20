package com.example.matulewithstyle.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import com.example.matulewithstyle.R

@Composable
fun Splash(navController: NavController) {
    // Запускаем таймер с помощью LaunchedEffect
    LaunchedEffect(key1 = true) {
        delay(4000) // Задержка 4 секунды
        navController.navigate("on_boarding") // Переход на экран home
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(72, 178, 231, 255))
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(129.dp)
                .align(Alignment.Center)
        )
    }
}
