package com.uandroides.aprende.modelos;

import java.util.ArrayList;

import com.uandroides.aprende.IServiceDelegate;

public class APPCuestionario implements IServiceDelegate{
	private ArrayList<APPregunta> preguntas;
	private int cantidad;
	private float puntuacion;
	private int resueltas;
	private int errores;
	private ArrayList<APPregunta> retroalimentacion;
	
	public APPCuestionario(ArrayList<APPregunta> preguntas){
		this.preguntas = preguntas;
		validarPreguntas();
	}
	
	
	private void validarPreguntas(){
		resueltas = 0;
		errores = 0;
		cantidad = preguntas.size();
		APPregunta retro = new APPregunta();
		for(APPregunta pregunta : preguntas){
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
	
	
	public ArrayList<APPregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<APPregunta> preguntas) {
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
	public ArrayList<APPregunta> getRetroalimentacion() {
		return retroalimentacion;
	}
	public void setRetroalimentacion(ArrayList<APPregunta> retroalimentacion) {
		this.retroalimentacion = retroalimentacion;
	}
	public int getErrores() {
		return errores;
	}
	public void setErrores(int errores) {
		this.errores = errores;
	}
	
	
	
}
