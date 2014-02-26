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

public class Temario {
	private int idTema;
	private String nombre;
	private String descripcion;
	private int idMateria;
	private String nombreMateria;
	
	public Temario(String nombre, String descripcion, String nombreMateria){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombreMateria = nombreMateria;
		
	}
	
	
	public Temario(){}
	
	public Temario(int k, String string, String string2, String nombre2) {
		// TODO Auto-generated constructor stub
		this.idTema = k;
		this.nombre = string;
		this.descripcion = string2;
		this.nombreMateria = nombre2;
	}


	public String getNombreMateria() {
		return nombreMateria;
	}
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
}
