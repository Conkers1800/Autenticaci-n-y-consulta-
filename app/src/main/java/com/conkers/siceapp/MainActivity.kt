package com.conkers.siceapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ControlledComposition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.conkers.siceapp.Data.conexionRepositorio
import com.conkers.siceapp.ui.theme.LoggedInScreen
import com.conkers.siceapp.ui.theme.LoginScreen
import com.conkers.siceapp.ui.theme.SiceAppTheme

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
    val inicioSesion=conexionRepositorio.Ob
    if (Entrar) {
        LoggedInScreen(navController = navController, cookie = cookie)
    } else {
        LoginScreen(
            context = context,
            matricula = username,
            contrasenia = password,
            onUsernameChange = { username = it },
            onPasswordChange = { password = it },

            onLoginClicked = {
                (context, username.text, password.text, navController) { success, message, receivedCookie ->
                    if (success) {
                        loggedIn = true
                        successMessage = message ?: "Inicio de sesion exitoso"
                        cookie = receivedCookie ?: ""
                    } else {
                        errorText = message ?: "Error desconocido"
                        // Mostrar un mensaje de error al usuario
                        Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

    if (errorText.isNotEmpty()) {
        Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
    }
}