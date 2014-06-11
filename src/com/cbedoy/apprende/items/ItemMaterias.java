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
package com.cbedoy.apprende.items;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbedoy.apprende.models.Materia;
import com.uandroides.aprende.R;

public class ItemMaterias extends BaseAdapter {
	
	private int llave;
	private String llaveHash;
	private ArrayList<Materia> data;
	private Activity contexto;
	public ItemMaterias(Activity contexto) {
		data = new ArrayList<Materia>();
		data.add(new Materia(1, "Parciales"));
		data.add(new Materia(2, "Matematicas"));
		data.add(new Materia(3, "Ingles"));
		data.add(new Materia(4, "Espa�ol"));
		data.add(new Materia(5, "Fisica"));
		data.add(new Materia(6, "Geografia"));
		
		data.add(new Materia(7, "Ciencias naturales"));
		data.add(new Materia(8, "Ciencias sociales"));
		data.add(new Materia(9, "Psicologia"));
		data.add(new Materia(10, "Quimica"));
		data.add(new Materia(11, "Biologia"));
		data.add(new Materia(12, "IT"));
		
		llave = 1;
		llaveHash = "Parciales";
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup viewGroup) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			LayoutInflater inflate = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View vistaView = inflate.inflate(R.layout.item_materia, null);

			convertView = vistaView;
		}

		if (convertView != null) {
			LinearLayout layout = (LinearLayout) convertView;
			TextView nombre = (TextView)layout.findViewById(R.id.bMateria);
			nombre.setText(data.get(position).getNombre());
			
			Typeface tf = Typeface.createFromAsset(contexto.getAssets(),"font/dudu.ttf");
			nombre.setTypeface(tf);
			
			layout.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					llave = data.get(position).getId();
					llaveHash = data.get(position).getNombre();
					
				}
			});
		}
		return convertView;

	}

	
}
