package com.conkers.siceapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.conkers.siceapp.Data.RetrofitContenedor

@Composable
fun LoginScreen(
    navController: NavController,
    retrofitContenedor: RetrofitContenedor,
    viewModel: LoginViewModel = remember { LoginViewModel(retrofitContenedor) }
) {
    var usernameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de texto para el nombre de usuario
        TextField(
            value = usernameState,
            onValueChange = { usernameState = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la contraseña
        TextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                viewModel.loginUser(navController, usernameState, passwordState)
            },
            enabled = !viewModel.isLoading.collectAsState().value,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (viewModel.isLoading.collectAsState().value) {
                CircularProgressIndicator()
            } else {
                Text("Login")
            }
        }

        // Mostrar mensaje de error si existe
        if (viewModel.errorText.collectAsState().value != null) {
            Text(
                text = viewModel.errorText.collectAsState().value!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
