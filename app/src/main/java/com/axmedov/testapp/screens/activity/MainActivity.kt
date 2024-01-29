package com.axmedov.testapp.screens.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.axmedov.testapp.R
import com.axmedov.testapp.navigation.NavigationHandler
import com.axmedov.testapp.screens.splash.SplashScreen
import com.axmedov.testapp.ui.theme.TestAppTheme
import com.axmedov.testapp.utils.CheckInternetReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler

    private val checkInternetReceiver = CheckInternetReceiver()
    private var isStartListening: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                Column {
                    InternetConnectionAlert()
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
    fun InternetConnectionAlert() {
        val viewModel: MainActivityViewModel = viewModel(MainActivityViewModelImpl::class.java)
        val internetConnected by viewModel.internetConnectionLiveData.collectAsState()
        val topMessage by viewModel.showMessageOnTopOfScreenLiveData.collectAsState()

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
            ShowTop(isError = false, message = topMessage)
        }
    }

    @Composable
    fun ShowTop(isError: Boolean, message: String) {
        if (message.isNotEmpty()) {
            AnimatedVisibility(
                visible = false,
                enter = fadeIn(animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = if (isError) R.color.error_red else R.color.green))
                        .padding(8.dp),
                    text = message,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    private fun Content() {
        Navigator(SplashScreen()) { navigator ->
            LaunchedEffect(key1 = navigator) {
                navigationHandler.navigationBuffer
                    .onEach { it.invoke(navigator) }
                    .collect()
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
