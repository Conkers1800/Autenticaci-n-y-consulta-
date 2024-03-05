package com.conkers.siceapp.Data

class conexionRepositorio(
    private val accesoAlumnoApi: AccesoAlumnoRetro)
suspend fun getAcceso(matricula: String, contrasenia: String, tipoUsuario: String): String? {
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
    Log.d("xml: ",xml)
    val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
    return try {
        val response = accesoAlumnoApi.getAcceso(requestBody)
        val responseBodyString = response.string()

        val serializer = Persister()
        val reader = StringReader(responseBodyString)
        val envelope = serializer.read(AccesoAlumnoEnvelope::class.java, reader)
        var respuestaJson = envelope.body?.response?.result.toString()

        // Utiliza Gson para convertir el JSON a un objeto Kotlin
        respuestaJson
    } catch (e: IOException){
        Log.e(TAG_ERROR,"${e.message}")
        ""
    }
}