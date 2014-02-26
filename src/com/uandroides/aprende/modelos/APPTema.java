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

public class APPTema {
	private int id;
	private String nombre;
	private String descripcion;
	private String dificultad;
	private int numeroPreguntas;
	public APPTema(int id, String nombre, String descripcion){
		this.setId(id);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		
	}
	
	public APPTema(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
		if(dificultad.toUpperCase().equals("FACIL"))
			numeroPreguntas = 20;
		else
			if(dificultad.toUpperCase().equals("INTERMEDIO"))
				numeroPreguntas = 10;
			else
				numeroPreguntas = 5;
					
		
	}

	public int getNumeroPreguntas() {
		return numeroPreguntas;
	}


	
}
