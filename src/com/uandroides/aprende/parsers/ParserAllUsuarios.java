package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.interfaces.IServiceDelegate;
import com.uandroides.aprende.modelos.Pregunta;
import com.uandroides.aprende.modelos.Usuario;

public class ParserAllUsuarios implements IParser,IServiceDelegate{

	private ArrayList<IModel> usuarios;
	@Override
	public void setStringToParse(JSONArray json) {
		usuarios = new ArrayList<IModel>();
		for (int i = 0; i < json.length(); i++) {
			try {
				JSONObject jobj = json.getJSONObject(i);
				Usuario usuario = new Usuario();
				usuario.setId(jobj.getInt("id"));
				usuario.setCorreo(jobj.getString("correo"));
				usuario.setNombre(jobj.getString("nombre"));
				usuario.setEdad(jobj.getInt("edad"));
				usuario.setLink(jobj.getString("foto"));
				usuario.setPuntos(jobj.getInt("puntos"));
				usuario.setPruebas(jobj.getInt("pruebas"));
				usuarios.add(usuario);
			} catch (JSONException e) {	
				e.printStackTrace();
			}
		
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		return usuarios;
	}
}