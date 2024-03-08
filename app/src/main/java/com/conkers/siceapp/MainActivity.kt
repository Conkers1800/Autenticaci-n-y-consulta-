package com.conkers.siceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.conkers.siceapp.Data.RetrofitContenedorImpl
import com.conkers.siceapp.ui.theme.LoginScreen
import com.conkers.siceapp.ui.theme.SicenetResponseScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val retrofitContenedor = remember { RetrofitContenedorImpl(applicationContext) }
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        navController = navController,
                        retrofitContenedor = retrofitContenedor
                    )
                }
                composable("SicenetResponseScreen") {
                    SicenetResponseScreen()
                }
            }
        }
    }
}
