package com.conkers.siceapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.conkers.siceapp.model.AccesoAlumno
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun SicenetResponseScreen(responseViewModel: ResponseViewModel = viewModel()) {
    // Observar el LiveData en el ViewModel
    val responseState = responseViewModel.response

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Respuesta de la API:",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        responseState.value?.let { json ->
            val accesoAlumno = Json.decodeFromString<AccesoAlumno>(json)
            Text(
                text = buildString {
                    append("Acceso: ${accesoAlumno.acceso}\n")
                    append("Matrícula: ${accesoAlumno.matricula}\n")
                    append("Estatus: ${accesoAlumno.estatus}\n")
                    append("Tipo de Usuario: ${accesoAlumno.tipoUsuario}\n")
                    append("Contraseña: ${accesoAlumno.contrasenia}")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } ?: Text(
            text = "No hay respuesta",
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

class ResponseViewModel : ViewModel() {
    private val _response = MutableStateFlow<String?>(null)
    val response: StateFlow<String?> = _response

    fun updateResponse(newResponse: String?) {
        viewModelScope.launch {
            _response.emit(newResponse)
        }
    }
}
