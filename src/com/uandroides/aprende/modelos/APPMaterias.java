package com.uandroides.aprende.modelos;

public class APPMaterias {
	private int id;
	private String nombre;
	
	
	public APPMaterias(String nombre){
		this.nombre = nombre;
	}
	
	public APPMaterias(){
		
	}

	public APPMaterias(int i, String string) {
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
