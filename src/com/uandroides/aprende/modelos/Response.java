package com.uandroides.aprende.modelos;

import com.uandroides.aprende.interfaces.IModel;

public class Response implements IModel {
	private int id;
	private String mensaje;

	
	public Response(){
		
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
