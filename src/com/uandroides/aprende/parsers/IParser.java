package com.uandroides.aprende.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.uandroides.aprende.modelos.IModel;

public interface IParser {
	public void setStringToParse(JSONArray json);
	public ArrayList<IModel> getDataParsed();
}
