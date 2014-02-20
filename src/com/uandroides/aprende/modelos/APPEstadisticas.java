package com.uandroides.aprende.modelos;

public class APPEstadisticas{
	
	private String pregunta;
	private String respuesta;
	private String retroalimentacion;
	
	
	public APPEstadisticas(String pregunta, String respuesta, String retroalimentacion){
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
