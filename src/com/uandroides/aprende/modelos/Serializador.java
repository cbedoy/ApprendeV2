//--------------------------------------------
//
//	Apprende
//  Develop by Carlos Alfredo Cervantes Bedoy
//
//	Android Developer
//
//	Independent project:	carlos.bedoy@gmail.com
//
//	Aguascalientes | Mexico
//-------------------------------------------------------
package com.uandroides.aprende.modelos;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Serializador implements Parcelable{

	private ArrayList<APPregunta> preguntas;
	
	public Serializador(ArrayList<APPregunta> preguntas){
		this.preguntas = preguntas;
	}
	public ArrayList<APPregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<APPregunta> preguntas) {
		this.preguntas = preguntas;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
