package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONObject;

import com.uandroides.aprende.modelos.Estadistica;
import com.uandroides.aprende.modelos.IModel;

public class ParserEstadistica implements IParser{

	private ArrayList<Estadistica> estadisticas;
	
	public ParserEstadistica(){
		this.estadisticas = new ArrayList<Estadistica>();
	}
	
	@Override
	public void setStringToParse(JSONObject json) {
		
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		
		return null;
	}
	
}
