package com.conkers.siceapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.NavController
import com.conkers.siceapp.Data.RetrofitContenedor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val retrofitContenedor: RetrofitContenedor) : ViewModel() {
    private val _errorText = MutableStateFlow<String?>(null)
    val errorText: StateFlow<String?> = _errorText

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loginUser(navController: NavController, username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val respuestaApi = retrofitContenedor.conexionRepositorio.ObtenerAcceso(username, password, "ALUMNO")

            // Simulamos una llamada a la API con un retraso de 2 segundos
            delay(2000)

            if (!respuestaApi.isNullOrEmpty()) {
                // Si se obtiene una respuesta de la API, navega a la pantalla de respuesta
                navController.navigate("SicenetResponseScreen")
            } else {
                // Si no se obtiene respuesta, mostrar un mensaje de error
                _errorText.value = "Error al obtener la respuesta de la API"
            }
            _isLoading.value = false
        }
    }
}

