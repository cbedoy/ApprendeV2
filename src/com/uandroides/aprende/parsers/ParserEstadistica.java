package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.modelos.Estadistica;
import com.uandroides.aprende.modelos.IModel;


public class ParserEstadistica implements IParser{

	private ArrayList<IModel> estadisticas;
	
	public ParserEstadistica(){
		this.estadisticas = new ArrayList<IModel>();
	}
	
	@Override
	public void setStringToParse(JSONArray json) {
		for(int i=0; i<json.length(); i++){
			try {
				JSONObject jobj = json.getJSONObject(i);
				Estadistica modelo  = new Estadistica();
				modelo.setIdEstadistica(jobj.getInt("id"));
				modelo.setNivel(jobj.getInt("nivel"));
				modelo.setErrores(jobj.getInt("errores"));
				modelo.setPuntos((float)jobj.getDouble("puntos"));
				modelo.setFecha(jobj.getString("fecha"));
				modelo.setUsuario(jobj.getString("usuario"));
				modelo.setTema(jobj.getString("tema"));
				estadisticas.add(modelo);
				
			
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		return estadisticas;
	}
	
}
