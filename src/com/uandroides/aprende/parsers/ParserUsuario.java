package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.modelos.Pregunta;
import com.uandroides.aprende.modelos.Usuario;

public class ParserUsuario implements IParser{

	private ArrayList<IModel> usuario;
	@Override
	public void setStringToParse(JSONArray json) {
		usuario = new ArrayList<IModel>();
		for (int i = 0; i < json.length(); i++) {
			try {
				JSONObject jobj = json.getJSONObject(i);
				Usuario  usr = new Usuario();
				//Todo parser
				usuario.add(usr);
			} catch (JSONException e) {	
				e.printStackTrace();
			}
		
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		
		return usuario;
	}
}