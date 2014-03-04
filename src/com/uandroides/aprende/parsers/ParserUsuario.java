package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.interfaces.IServiceDelegate;
import com.uandroides.aprende.modelos.Pregunta;
import com.uandroides.aprende.modelos.Usuario;

public class ParserUsuario implements IParser, IServiceDelegate{

	private ArrayList<IModel> usuario;
	@Override
	public void setStringToParse(JSONArray json) {
		usuario = new ArrayList<IModel>();
		for (int i = 0; i < json.length(); i++) {
			try {
				JSONObject jobj = json.getJSONObject(i);
				Usuario  usr = new Usuario();
				usr.setId(jobj.getInt("id"));
				usr.setCorreo(jobj.getString("correo"));
				usuario.add(usr);
			} catch (JSONException e) {	
				e.printStackTrace();
			}
		
		}
		for(IModel mo : usuario){
			Usuario u = (Usuario) mo;
			Log.i("user", u.getId()+"");
			Log.i("user", u.getCorreo());
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		
		return usuario;
	}
}