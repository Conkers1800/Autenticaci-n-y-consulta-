package com.conkers.siceapp.Data

import android.util.Log
import com.conkers.siceapp.Network.AccesoAlumnoApi
import com.conkers.siceapp.Network.DatosAlumnoApi
import com.conkers.siceapp.model.AccesoAlAlumnoEnvelope
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.StringReader

private const val TAG_ERROR = "ERROR"

class ConexionRepositorio(
    private val accesoAlumnoApi: AccesoAlumnoApi,
    private val datosAlumnoApi: DatosAlumnoApi
) {
    suspend fun ObtenerAcceso(matricula: String, contrasenia: String, tipoUsuario: String): String? {
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${matricula}</strMatricula>
                  <strContrasenia>${contrasenia}</strContrasenia>
                  <tipoUsuario>${tipoUsuario}</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        Log.d("xml: ", xml)
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = accesoAlumnoApi.ObtenerAcceso(requestBody)
            val responseBodyString = response.string()

            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(AccesoAlAlumnoEnvelope::class.java, reader)
            var respuestaJson = envelope.body?.response?.result.toString()

            respuestaJson
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }

    suspend fun getAlumnoAcademicoWithLineamiento(): String? {
        val xml =
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <getAlumnoAcademicoWithLineamiento xmlns=\"http://tempuri.org/\" />\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>".trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = datosAlumnoApi.getAlumnoAcademicoWithLineamiento(requestBody)
            val responseBodyString = response.string()

            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(AccesoAlAlumnoEnvelope::class.java, reader)

            val jsonString = envelope.body?.response?.result.toString()
            jsonString
        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }
}
