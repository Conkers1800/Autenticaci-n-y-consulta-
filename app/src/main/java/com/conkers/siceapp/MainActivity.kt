package com.conkers.siceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
