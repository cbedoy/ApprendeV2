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

import java.util.ArrayList;



public class APPCuestionario {
	private ArrayList<Pregunta> preguntas;
	private int cantidad;
	private float puntuacion;
	private int resueltas;
	private int errores;
	private ArrayList<Pregunta> retroalimentacion;
	
	public APPCuestionario(ArrayList<Pregunta> preguntas){
		this.preguntas = preguntas;
		validarPreguntas();
	}
	
	
	private void validarPreguntas(){
		resueltas = 0;
		errores = 0;
		cantidad = preguntas.size();
		Pregunta retro = new Pregunta();
		for(Pregunta pregunta : preguntas){
			if(pregunta.getRespuestaUsuario() == pregunta.getRespuestaCorrecta()){
				resueltas++;
				
			}else{
				errores++;
			}
			retro.setPregunta(pregunta.getPregunta());
			retro.setSeleccionUsuario(pregunta.getSeleccionUsuario());
			retro.setRetroalimentacion(pregunta.getRetroalimentacion());
			this.retroalimentacion.add(retro);
		}
		puntuacion = (float)((float)(cantidad-errores))/((float)cantidad);
	}
	
	
	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getResueltas() {
		return resueltas;
	}
	public void setResueltas(int resueltas) {
		this.resueltas = resueltas;
	}
	public ArrayList<Pregunta> getRetroalimentacion() {
		return retroalimentacion;
	}
	public void setRetroalimentacion(ArrayList<Pregunta> retroalimentacion) {
		this.retroalimentacion = retroalimentacion;
	}
	public int getErrores() {
		return errores;
	}
	public void setErrores(int errores) {
		this.errores = errores;
	}
	
	
	
}
