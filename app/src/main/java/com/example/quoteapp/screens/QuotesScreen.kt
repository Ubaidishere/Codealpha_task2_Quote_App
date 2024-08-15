package com.example.quoteapp.screens

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.quoteapp.Data.DataBase.RoomData
import com.example.quoteapp.Data.DataBase.myViewModel
import com.example.quoteapp.Data.Quotes.Retrofit2Instance
import com.example.quoteapp.Data.Unsplash.RetrofitInstance
import com.example.quoteapp.Data.shareFun
import com.example.quoteapp.R
import com.example.quoteapp.ui.theme.splashScreenFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object QuotesScreen

@Composable
fun QuotesScreen(viewModel: myViewModel) {
    val state = rememberPagerState(pageCount = { 200 })

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        VerticalPager(
            state = state,
            modifier = Modifier.fillMaxSize()
        ) {
            QuoteCard(viewModel)
        }
    }
}

@Composable
fun QuoteCard(viewModel: myViewModel) {
    val copyManager = LocalClipboardManager.current
    val context = LocalContext.current
    var todayQuote by remember { mutableStateOf("") }
    var todayAuthor by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val liked by viewModel.isQuoteLiked(todayQuote).observeAsState(0)
    var isLoading by remember { mutableStateOf(true) }
    var background by remember { mutableStateOf<String?>(null) }
    var showAlertDialog by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }
    var alertTitle by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        scope.launch {
            if (isInternetAvailable(context)) {
                try {
                    val resources = RetrofitInstance.api.getRandomPhoto()
                    background = resources.urls.regular
                    val resources2 = Retrofit2Instance.api.getQuotes()
                    delay(500L)
                    todayQuote = resources2[0].q
                    todayAuthor = resources2[0].a
                } catch (e: Exception) {
                    alertTitle = "Limit Exceeded"
                    todayQuote = "Please try again later."
                    alertMessage = "We’ve temporarily reached our limit for fetching new quotes. This usually resets shortly. Thanks for your patience."
                    showAlertDialog = true
                }
                isLoading = false
            }else{
                delay(1000)
                alertTitle = "Connection Error"
                alertMessage = "No internet connection. Please check your network."
                showAlertDialog = true
                isLoading = false
            }
        }
    }
    if(isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator( color = Color.White)
        }
    }
    else if (isInternetAvailable(context)) {
        Box(modifier = Modifier.fillMaxSize()) {
            background?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "bg",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: run { }
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 40.dp, bottom = 40.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    todayAuthor?.let {
                        Text(
                            text = it,
                            fontSize = 34.sp,
                            fontFamily = splashScreenFont,
                            color = Color.White,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .align(Alignment.CenterHorizontally),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.8f),
                                    offset = Offset(4f, 4f),
                                    blurRadius = 4f
                                )
                            ),overflow = TextOverflow.Ellipsis)

                    }
                    Text(
                        text = "''$todayQuote''",
                        fontSize = 34.sp,
                        fontFamily = splashScreenFont,
                        lineHeight = 34.sp,
                        color = Color.White,
                        modifier = Modifier
                            .width(250.dp)
                            .padding(bottom = 20.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.8f),
                                offset = Offset(4f, 4f),
                                blurRadius = 4f
                            )
                    ),overflow = TextOverflow.Ellipsis)
                }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(y = (-80).dp)
            ) {
                Column {
                    IconButton(
                        onClick = { if (liked>0){
                            Toast.makeText(context, "Your likes quota has been added.", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else if (todayQuote.isNullOrEmpty()){
                            Toast.makeText(context, "No data available Please try latter.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            todayQuote?.let { RoomData(quote = it) }
                                ?.let { viewModel.insertQuote(it) }
                }
                        },
                        Modifier.padding(end = 15.dp, bottom = 25.dp)
                    ) {
                        Icon(
                            painter = painterResource(id =
                            if (liked > 0) R.drawable.filled_heart else R.drawable.outlined_heart),
                            contentDescription = "Liked",
                            tint = if (liked > 0) Color.Red else Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            copyManager.setText(buildAnnotatedString { append(todayQuote) })
                            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                        },
                        Modifier.padding(end = 15.dp, bottom = 25.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "", tint = Color.White)
                    }
                    IconButton(
                        onClick = { todayQuote?.let { shareFun(context, it) } },
                        Modifier.padding(end = 15.dp, bottom = 25.dp)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = "", tint = Color.White)
                    }
                }
            }
        }
    }else{
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), contentAlignment = Alignment.Center){
            Row {
                Icon(Icons.Default.NotInterested, contentDescription = "No Internet", tint = Color.Red,modifier = Modifier.padding(end = 5.dp))
                Text(text = "Unable to connect to the Internet...", color = Color.White)
            }
        }
    }
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            confirmButton = {
                Button(onClick = { showAlertDialog = false }) {
                    Text("OK")
                }
            },
            title = {
                Text(text = alertTitle)
            },
            text = {
                Text(text = alertMessage)
            }
        )
    }
}


@SuppressLint("ServiceCast")
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}