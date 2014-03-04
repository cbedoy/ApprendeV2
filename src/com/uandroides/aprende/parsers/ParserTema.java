package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.interfaces.IServiceDelegate;
import com.uandroides.aprende.modelos.Tema;

public class ParserTema implements IParser, IServiceDelegate{

	private ArrayList<IModel> temas;
	@Override
	public void setStringToParse(JSONArray json) {
		temas = new ArrayList<IModel>();
		for (int i = 0; i < json.length(); i++) {
			try {
				JSONObject jobj = json.getJSONObject(i);
				Tema tema = new Tema();
				tema.setId(jobj.getInt("id"));
				tema.setNombre(jobj.getString("nombre"));
				tema.setDificultad(jobj.getString("dificultad"));
				tema.setDescripcion(jobj.getString("descripcion"));
				temas.add(tema);
			} catch (JSONException e) {	
				e.printStackTrace();
			}
		
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		
		return temas;
	}
}