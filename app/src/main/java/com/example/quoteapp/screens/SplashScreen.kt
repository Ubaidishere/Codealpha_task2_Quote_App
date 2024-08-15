package com.example.quoteapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quoteapp.R
import com.example.quoteapp.ui.theme.splashScreenFont
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Composable
fun SplashScreen(navHostController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(bottom = 30.dp),
        contentAlignment = Alignment.Center) {
        Column {
            Image(painterResource(id = R.drawable.quote), contentDescription = "icon", modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally))
            Text(text = "Quote App", color = Color.White, fontSize = 42.sp, fontFamily = splashScreenFont)
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navHostController.navigate(MyScaffold)
    }
}

