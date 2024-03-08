package com.conkers.siceapp.model

import kotlinx.serialization.Serializable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

// Clase que representa el sobre SOAP para la respuesta del acceso al alumno
//@Serializable
//@Root(name = "soap:Envelope")
//@NamespaceList(
   // Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
  //  Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"),
//    Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
//)
@Serializable
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class AccesoAlAlumnoEnvelope(
    @field:Element(name = "Body")
    @param:Element(name = "Body")
    var body: BodyAccesoAlAlumno? = null
)

@Serializable
@Root(name = "Body")
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/")
class BodyAccesoAlAlumno {
    @field:Element(name = "accesoLoginResponse")
    @Namespace(reference = "http://tempuri.org/")
    var response: AccesoAlAlumnoResponse? = null
}

@Serializable
@Root(name = "accesoLoginResponse")
@Namespace(reference = "http://tempuri.org/")
class AccesoAlAlumnoResponse {
    @field:Element(name = "accesoLoginResult")
    var result: String? = null
}