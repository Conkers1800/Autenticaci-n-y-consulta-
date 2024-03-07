package com.conkers.siceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.compose.rememberNavController
import com.conkers.siceapp.Data.conexionRepositorio


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lanzador()
        }
    }
}
@Composable
fun Lanzador(){
    val context = LocalContext.current
    val navController = rememberNavController()
    var Entrar by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var cookie by remember { mutableStateOf("") }
    val inicioSesion=conexionRepositorio.ObtenerAcceso("","","")


}