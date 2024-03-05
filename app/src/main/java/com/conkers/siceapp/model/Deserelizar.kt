package com.conkers.siceapp.model

import kotlinx.serialization.Serializable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root
//Acceso al Alumno
@Serializable
@Root(name = "soap:Envelope")
@NamespaceList(
    Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
    Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"),
    Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
)
class AccesoAlAlumnoEnvelope(
    @field:Element(name = "soap:Body", required = false)
    @param:Element(name = "soap:Body", required = false)
    val body: BodyAccesoAlAlumno? = null
)

@Serializable
@Root(name = "soap:Body")
@NamespaceList(
    Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
    Namespace(reference = "http://tempuri.org/")
)
class BodyAccesoAlAlumno(

    @Element(name = "accesoLoginResponse")
    @Namespace(reference = "http://tempuri.org/")
    val response: AccesoAlAlumnoResponse? = null
)

@Serializable
@Root(name = "accesoLoginResponse")
@NamespaceList(
    Namespace(reference = "http://tempuri.org/")
)
class AccesoAlAlumnoResponse (

    @Element(name = "accesoLoginResult")
    @Namespace(reference = "http://tempuri.org/")
    val Result: String? = null
)