package com.example.quoteapp.screens

import QuoteOfTheDay
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quoteapp.Data.DailyQuote.TodayQuote
import com.example.quoteapp.Data.DailyQuote.TodayQuoteViewModel
import com.example.quoteapp.Data.DataBase.RoomData
import com.example.quoteapp.Data.DataBase.myViewModel
import com.example.quoteapp.Data.shareFun
import com.example.quoteapp.R
import com.example.quoteapp.ui.theme.splashScreenFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
object HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(viewModel: myViewModel, viewModel2: TodayQuoteViewModel) {
    val context = LocalContext.current
    var todayQuote by remember { mutableStateOf("") }
    var todayAuthor by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val data by viewModel2.quoteLiveData.observeAsState()
    val liked by viewModel.isQuoteLiked(todayQuote).observeAsState(0)
    val today = LocalDate.now().dayOfWeek.toString()

    LaunchedEffect(Unit) {
        scope.launch {
            delay(50)
            if (today != data?.dayAddQuote) {
                val randomQuote = QuoteOfTheDay.getRandomQuote()
                todayQuote = randomQuote.quote
                todayAuthor = randomQuote.author
                viewModel2.insert(
                    TodayQuote(
                        id = 1,
                        dayQuote = todayQuote,
                        dayAuthor = todayAuthor!!,
                        dayAddQuote = today
                    )
                )
            } else {
                todayQuote = data!!.dayQuote
                todayAuthor = data!!.dayAuthor
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxSize(), Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Today's Best Quote",
                        fontSize = 32.sp,
                        fontFamily = splashScreenFont,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 32.dp),
                        textDecoration = TextDecoration.Underline
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(16.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.White),
                    ) {

                        IconButton(onClick = {
                            val randomQuote = QuoteOfTheDay.getRandomQuote()
                            todayQuote = randomQuote.quote
                            todayAuthor = randomQuote.author
                            viewModel2.insert(
                                TodayQuote(
                                    id = 1,
                                    dayQuote = todayQuote,
                                    dayAuthor = todayAuthor!!,
                                    dayAddQuote = today
                                )
                            )
                        }, modifier = Modifier.align(Alignment.BottomEnd)) {
                            Icon(Icons.Default.ChangeCircle, contentDescription = "Change", tint = Color.Black)
                        }
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            todayQuote?.let {
                                Text(
                                    text = it,
                                    fontSize = 24.sp,
                                    fontFamily = splashScreenFont,
                                    lineHeight = 40.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(width = 8.dp, height = 1.dp)
                                        .align(Alignment.CenterVertically)
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                todayAuthor?.let {
                                    Text(
                                        text = it,
                                        fontSize = 16.sp,
                                        fontFamily = splashScreenFont,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Row(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = {if (liked>0){
                    Toast.makeText(context, "Your likes quota has been added.", Toast.LENGTH_SHORT).show()
                }
                else{
                    todayQuote.let { RoomData(quote = it) }?.let { viewModel.insertQuote(it) }
                }
                }, modifier = Modifier.size(60.dp)) {
                    Icon(
                        painter = painterResource(id =
                        if (liked > 0) R.drawable.filled_heart else R.drawable.outlined_heart
                        ),
                        contentDescription = "Liked",
                        modifier = Modifier.size(50.dp),
                        tint = if (liked > 0) Color.Red else Color.White
                    )
                }
                IconButton(onClick = {
                    todayQuote?.let { shareFun(context, it) }
                }, modifier = Modifier.size(60.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "Share",
                        modifier = Modifier.size(55.dp),
                        tint = Color.White,
                    )
                }
            }
        }
    }
}
