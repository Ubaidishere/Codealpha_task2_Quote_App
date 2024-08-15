package com.example.quoteapp.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quoteapp.Data.DailyQuote.TodayQuoteViewModel
import com.example.quoteapp.Data.DataBase.myViewModel
import kotlinx.serialization.Serializable
import com.example.quoteapp.R

data class BottomNavigationBar(
    val name : String,
    val selectedIcon : Int,
    val unselectedIcon : Int,
)

@Serializable
object MyScaffold

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyScaffold(viewModel: myViewModel,viewModel2: TodayQuoteViewModel) {
    val navHostController = rememberNavController()
    var check by remember { mutableIntStateOf(1) }
    val list = listOf(
        BottomNavigationBar("Likes", R.drawable.filled_heart,R.drawable.outlined_heart ),
        BottomNavigationBar("Home", R.drawable.filled_home, R.drawable.outlined_home),
        BottomNavigationBar("Quotes", R.drawable.filled_chat, R.drawable.outlined_chat)
    )

    Scaffold(
        bottomBar = {
            NavigationBar (modifier = Modifier.height(65.dp)){
                list.forEachIndexed { index, item ->
                    NavigationBarItem(selected = check==index, onClick = {
                        check = index
                        when (index) {
                            0 -> navHostController.navigate(LikesScreen)
                            1 -> navHostController.navigate(HomeScreen)
                            2 -> navHostController.navigate(QuotesScreen)
                        }
                    }
                        , label = {
                            Text(text = item.name,
                            fontSize = 10.sp,
                            fontWeight = if (check == index) FontWeight.Bold else FontWeight.Normal)
                        }, icon = {

                                Icon(imageVector = if (index == check) ImageVector.vectorResource(id = item.selectedIcon) else ImageVector.vectorResource(
                                    id = item.unselectedIcon
                                ), contentDescription = null,modifier = Modifier.size(22.dp))
                        })
                }
            }
        }
    ) { paddingValue ->
        NavHost(navController = navHostController, startDestination = HomeScreen, modifier = Modifier.padding(paddingValue)) {
            composable<HomeScreen> { HomeScreen(viewModel,viewModel2) }
            composable<QuotesScreen> { QuotesScreen(viewModel) }
            composable<LikesScreen> { LikesScreen(viewModel) }
        }
    }
}