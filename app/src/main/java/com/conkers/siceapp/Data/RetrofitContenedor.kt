package com.conkers.siceapp.Data

import android.content.Context
import com.conkers.siceapp.Network.AccesoAlumnoApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.conkers.siceapp.Network.ReceivedCookiesInterceptor
import com.conkers.siceapp.Network.AddCookiesInterceptor
import com.conkers.siceapp.Network.DatosAlumnoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface RetrofitContenedor {
    val ConexionRepositorio: conexionRepositorio
}
class ContenedorApis(applicationContext: Context

){
    val client = OkHttpClient.Builder()
        .addInterceptor(AddCookiesInterceptor(applicationContext))
        .addInterceptor(ReceivedCookiesInterceptor(applicationContext))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://sicenet.surguanajuato.tecnm.mx")
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitAccesoDelAlumno: AccesoAlumnoApi by lazy {
        retrofit.create(AccesoAlumnoApi::class.java)
    }
    private val retrofitDatosDeAlumno: DatosAlumnoApi by lazy {
        retrofit.create(DatosAlumnoApi::class.java)
    }
}
