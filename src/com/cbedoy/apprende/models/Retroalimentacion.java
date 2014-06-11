//--------------------------------------------
//
//	Apprende
//  Develop by Carlos Alfredo Cervantes Bedoy
//
//	Android Developer
//
//	Independent project:	carlos.bedoy@gmail.com
//
//	Aguascalientes | Mexico
//-------------------------------------------------------
package com.cbedoy.apprende.models;

public class Retroalimentacion {
	
	private String pregunta;
	private String respuesta;
	private String retroalimentacion;
	
	
	public Retroalimentacion(String pregunta, String respuesta, String retroalimentacion){
		this.setPregunta(pregunta);
		this.setRespuesta(respuesta);
		this.setRetroalimentacion(retroalimentacion);
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public String getRetroalimentacion() {
		return retroalimentacion;
	}


	public void setRetroalimentacion(String retroalimentacion) {
		this.retroalimentacion = retroalimentacion;
	}
	
	
	
}
