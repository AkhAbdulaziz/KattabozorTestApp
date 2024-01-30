package com.axmedov.testapp.screens.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.axmedov.testapp.R
import com.axmedov.testapp.navigation.NavigationHandler
import com.axmedov.testapp.screens.home.HomeScreen
import com.axmedov.testapp.ui.theme.TestAppTheme
import com.axmedov.testapp.utils.CheckInternetReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModelImpl>()

    @Inject
    lateinit var navigationHandler: NavigationHandler
    private val checkInternetReceiver = CheckInternetReceiver()
    private var isStartListening: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val internetConnected by viewModel.internetConnectionState.collectAsState()
            val topMessage by viewModel.showMessageOnTopOfScreenState.collectAsState()

            TestAppTheme {
                Column {
                    InternetConnectionAlert(
                        internetConnected, topMessage
                    )
                    Content()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isStartListening = false
    }

    @Composable
    fun InternetConnectionAlert(internetConnected: Boolean, topMessage: String) {
        if (internetConnected) {
            if (isStartListening) {
                ShowTop(isError = false, message = stringResource(id = R.string.successfully_online))
            }
            isStartListening = true
        } else {
            ShowTop(isError = true, message = stringResource(id = R.string.no_internet))
            isStartListening = true
        }

        if (topMessage.isNotEmpty()) {
            ShowTop(isError = true, message = topMessage)
        }
    }

    @Composable
    private fun ShowTop(isError: Boolean, message: String) {
        var expanded by remember { mutableStateOf(true) }
        val targetSize = if (expanded) 40.dp else 0.dp
        val animatedSize by animateDpAsState(
            targetValue = targetSize, animationSpec = androidx.compose.animation.core.spring(
                dampingRatio = 0.8f, stiffness = 700f
            ), finishedListener = {
                // Do something when the animation finishes, if needed
            }, label = ""
        )

        if (message.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(if (isError) 40.dp else animatedSize)
                    .background(colorResource(id = if (isError) R.color.error_red else R.color.green))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = message,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }

        LaunchedEffect(message) {
            delay(3000L)
            expanded = false
        }
    }

    @Composable
    private fun Content() {
        Navigator(HomeScreen()) { navigator ->
            LaunchedEffect(key1 = navigator) {
                navigationHandler.navigationBuffer.onEach { it.invoke(navigator) }.collect()
            }
            CurrentScreen()
        }
    }

    override fun onStart() {
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(checkInternetReceiver, intent)
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(checkInternetReceiver)
    }
}
