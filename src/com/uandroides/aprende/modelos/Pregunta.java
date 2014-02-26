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

import android.os.Parcel;
import android.os.Parcelable;

public class Pregunta implements Parcelable{

	private String pregunta;
	private String respuesta1;
	private String respuesta2;
	private String respuesta3;
	private String respuesta4;
	private String link;
	private int respuestaUsuario;
	private int respuestaCorrecta;
	private String respuestaCorrectaString;
	private String retroalimentacion;
	private int idPregunta;
	private int idTema;
	private String seleccionUsuario;
	
	public Pregunta(){
		
	}
	

	public Pregunta(String pregunta, String r1, String r2, String r3, String r4, int correcta, String link){
		this.pregunta = pregunta;
		this.respuesta1 = r1;
		this.respuesta2 = r2;
		this.respuesta3 = r3;
		this.respuesta4 = r4;
		this.respuestaCorrecta = correcta;
		this.setRespuestaCorrecta(correcta);
		
		this.link = link;
		
	}
	
	
	public Pregunta(Parcel in) {
		// TODO Auto-generated constructor stub
		readFromParcel(in);
	}


	/**
     * @return the respuesta3
     */
    public String getRespuesta3() {
        return respuesta3;
    }

    /**
     * @param respuesta3 the respuesta3 to set
     */
    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    /**
     * @return the respuesta4
     */
    public String getRespuesta4() {
        return respuesta4;
    }

    /**
     * @param respuesta4 the respuesta4 to set
     */
    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the respuestaCorrecta
     */
    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    /**
     * @param respuestaCorrecta the respuestaCorrecta to set
     */
    public void setRespuestaCorrecta(int respuestaCorrecta) {
    	
        this.respuestaCorrecta = respuestaCorrecta;
        switch(respuestaCorrecta){
        	case 1:
        		this.retroalimentacion = respuesta1;
        		break;
        	case 2:
        		this.retroalimentacion = respuesta2;
        		break;
        	case 3:
        		this.retroalimentacion = respuesta3;
        		break;
        	case 4:
        		this.retroalimentacion = respuesta4;
        		break;
        }
    }

    /**
     * @return the retroalimentacion
     */
    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    /**
     * @param retroalimentacion the retroalimentacion to set
     */
    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    /**
     * @return the idPregunta
     */
    public int getIdPregunta() {
        return idPregunta;
    }

    /**
     * @param idPregunta the idPregunta to set
     */
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    /**
     * @return the idTema
     */
    public int getIdTema() {
        return idTema;
    }

    /**
     * @param idTema the idTema to set
     */
    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }
	
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta1() {
		return respuesta1;
	}
	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}
	
	public String getRespuesta2() {
		return respuesta2;
	}
	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}




	public int getRespuestaUsuario() {
		return respuestaUsuario;
	}




	public void setRespuestaUsuario(int respuestaUsuario) {
		this.respuestaUsuario = respuestaUsuario;
		switch(respuestaUsuario){
		case 1:
			seleccionUsuario = respuesta1;
			break;
		case 2:
			seleccionUsuario = respuesta2;
			break;
		case 3:
			seleccionUsuario = respuesta3;
			break;
		case 4:
			seleccionUsuario = respuesta4;
			break;
		}
	}




	public String getSeleccionUsuario() {
		return seleccionUsuario;
	}




	public void setSeleccionUsuario(String seleccionUsuario) {
		this.seleccionUsuario = seleccionUsuario;
	}





	public String getRespuestaCorrectaString() {
		return respuestaCorrectaString;
	}





	public void setRespuestaCorrectaString(String respuestaCorrectaString) {
		this.respuestaCorrectaString = respuestaCorrectaString;
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(pregunta);
		dest.writeString(respuesta1);
		dest.writeString(respuesta2);
		dest.writeString(respuesta3);
		dest.writeString(respuesta4);
		dest.writeString(link);
		dest.writeString(respuestaCorrectaString);
		dest.writeString(retroalimentacion);
		dest.writeString(seleccionUsuario);
		dest.writeInt(respuestaUsuario);
		dest.writeInt(respuestaCorrecta);
		dest.writeInt(idPregunta);
		dest.writeInt(idTema);
		
	}
	
	private void readFromParcel(Parcel in) {
		  pregunta = in.readString();
		  respuesta1 = in.readString();
		  respuesta2 = in.readString();
		  respuesta3 = in.readString();
		  respuesta4 = in.readString();
		  link = in.readString();
		  respuestaCorrectaString = in.readString();
		  retroalimentacion = in.readString();
		  seleccionUsuario = in.readString();
		  respuestaUsuario = in.readInt();
		  respuestaCorrecta = in.readInt();
		  idTema = in.readInt();
		  idPregunta = in.readInt();

		}
	
	public static final Parcelable.Creator<Pregunta> CREATOR = new Parcelable.Creator<Pregunta>() {
	    @Override
	    public Pregunta createFromParcel(Parcel in) {
	        return new Pregunta(in);
	    }

	    @Override
	    public Pregunta[] newArray(int size) {
	        return new Pregunta[size];
	    }
	};
}
