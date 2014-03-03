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

import com.uandroides.aprende.interfaces.IModel;

public class Materia implements IModel{
	private int id;
	private String nombre;
	
	
	public Materia(String nombre){
		this.nombre = nombre;
	}
	
	public Materia(){
		
	}

	public Materia(int i, String string) {
		// TODO Auto-generated constructor stub
		this.id = i;
		this.nombre = string;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
