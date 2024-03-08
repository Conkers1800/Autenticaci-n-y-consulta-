package com.conkers.siceapp.Data

import android.content.Context
import com.conkers.siceapp.Network.AccesoAlumnoApi
import com.conkers.siceapp.Network.AddCookiesInterceptor
import com.conkers.siceapp.Network.DatosAlumnoApi
import com.conkers.siceapp.Network.ReceivedCookiesInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface RetrofitContenedor {
    val conexionRepositorio: ConexionRepositorio
}

class RetrofitContenedorImpl(applicationContext: Context) : RetrofitContenedor {
    private val client = OkHttpClient.Builder()
        .addInterceptor(AddCookiesInterceptor(applicationContext))
        .addInterceptor(ReceivedCookiesInterceptor(applicationContext))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://sicenet.surguanajuato.tecnm.mx")
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitAccesoDelAlumno: AccesoAlumnoApi by lazy {
        retrofit.create(AccesoAlumnoApi::class.java)
    }

    private val retrofitDatosAlumno: DatosAlumnoApi by lazy {
        retrofit.create(DatosAlumnoApi::class.java)
    }

    override val conexionRepositorio: ConexionRepositorio by lazy {
        ConexionRepositorio(retrofitAccesoDelAlumno, retrofitDatosAlumno)
    }
}
