package com.example.quoteapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quoteapp.Data.DailyQuote.TodayQuoteViewModel
import com.example.quoteapp.Data.DataBase.myViewModel
import com.example.quoteapp.screens.MyScaffold
import com.example.quoteapp.screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenNavigation(viewModel:myViewModel,viewModel2: TodayQuoteViewModel) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = SplashScreen) {
        composable<SplashScreen> { SplashScreen(navHostController) }
        composable<MyScaffold> { MyScaffold(viewModel,viewModel2) }
}
}