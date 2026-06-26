package com.route.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.news.ui.screens.home.HomeScreen
import com.route.news.ui.screens.splash.SplashScreen
import com.route.news.ui.theme.NewsTheme
import com.route.news.ui.utils.HomeRoute
import com.route.news.ui.utils.SplashRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsTheme {
                App()
            }
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeRoute)
    {
        composable<HomeRoute> {
            HomeScreen(navController)
        }
        composable<SplashRoute> {
            SplashScreen(navController)
        }

    }
}


