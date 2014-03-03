package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uandroides.aprende.interfaces.IModel;
import com.uandroides.aprende.interfaces.IParser;
import com.uandroides.aprende.interfaces.IServiceDelegate;
import com.uandroides.aprende.modelos.Response;
import com.uandroides.aprende.modelos.Usuario;

public class ParserResponse implements IParser, IServiceDelegate{

	private ArrayList<IModel> response;
	@Override
	public void setStringToParse(JSONArray json) {
		response = new ArrayList<IModel>();
		for (int i = 0; i < json.length(); i++) {
			try {
				JSONObject jobj = json.getJSONObject(i);
				Response rsp = new Response();
				//Todo parser
				response.add(rsp);
			} catch (JSONException e) {	
				e.printStackTrace();
			}
		
		}
	}

	@Override
	public ArrayList<IModel> getDataParsed() {
		return response;
	}
}