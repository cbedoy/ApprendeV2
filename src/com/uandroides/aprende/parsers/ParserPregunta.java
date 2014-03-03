package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.interfaces.IServiceDelegate;
import com.uandroides.aprende.modelos.Pregunta;

public class ParserPregunta implements IParser, IServiceDelegate{

	private ArrayList<IModel> preguntas;
	@Override
	public void setStringToParse(JSONArray json) {
		preguntas = new ArrayList<IModel>();
		for (int i = 0; i < json.length(); i++) {
			JSONObject jobj;
			try {
				jobj = json.getJSONObject(i);
				Pregunta pregunta = new Pregunta();
				pregunta.setIdPregunta(jobj.getInt("id"));
				pregunta.setPregunta(jobj.getString("pregunta"));
				pregunta.setRespuesta1(jobj.getString("respuesta1"));
				pregunta.setRespuesta2(jobj.getString("respuesta2"));
				pregunta.setRespuesta3(jobj.getString("respuesta3"));
				pregunta.setRespuesta4(jobj.getString("respuesta4"));
				pregunta.setRespuestaCorrecta(jobj.getInt("correcta"));
				pregunta.setLink(jobj.getString("recurso"));
				pregunta.setRetroalimentacion(jobj.getString("retroalimentacion"));
				preguntas.add(pregunta);
			} catch (JSONException e) {	
				e.printStackTrace();
			}
		
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		
		return preguntas;
	}

}
