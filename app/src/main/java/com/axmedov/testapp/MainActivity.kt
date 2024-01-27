package com.axmedov.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.axmedov.testapp.navigation.nav_graph.SetUpNavGraph
import com.axmedov.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestAppTheme {
                navHostController = rememberNavController()
                SetUpNavGraph(navHostController = navHostController)
            }
        }
    }
}
