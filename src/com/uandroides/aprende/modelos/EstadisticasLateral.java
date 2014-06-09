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
package com.uandroides.aprende.modelos;

public class EstadisticasLateral {
	private String fecha;
	private String preguntas;
	private String aciertos;
	private String errores;
	private String titulo;
	private String puntos;
	
	
	public EstadisticasLateral(String fecha, String preguntas, String aciertos, String errores, String titulo, String puntos){
		this.fecha = fecha;
		this.preguntas = preguntas;
		this.aciertos = aciertos;
		this.errores = errores;
		this.titulo = titulo;
		this.puntos = puntos;
	}
	
	public EstadisticasLateral(){
		
	}
	public String getPuntos() {
		return puntos;
	}
	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getErrores() {
		return errores;
	}
	public void setErrores(String errores) {
		this.errores = errores;
	}
	public String getAciertos() {
		return aciertos;
	}
	public void setAciertos(String aciertos) {
		this.aciertos = aciertos;
	}
	public String getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(String preguntas) {
		this.preguntas = preguntas;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
