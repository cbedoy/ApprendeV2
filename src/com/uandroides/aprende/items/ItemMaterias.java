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
package com.uandroides.aprende.items;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uandroides.aprende.R;
import com.uandroides.aprende.modelos.APPMaterias;

public class ItemMaterias extends BaseAdapter {
	
	private int llave;
	private String llaveHash;
	private ArrayList<APPMaterias> data;
	private Activity contexto;
	public ItemMaterias(Activity contexto) {
		data = new ArrayList<APPMaterias>();
		data.add(new APPMaterias(1, "Parciales"));
		data.add(new APPMaterias(2, "Matematicas"));
		data.add(new APPMaterias(3, "Ingles"));
		data.add(new APPMaterias(4, "Español"));
		data.add(new APPMaterias(5, "Fisica"));
		data.add(new APPMaterias(6, "Geografia"));
		
		data.add(new APPMaterias(7, "Ciencias naturales"));
		data.add(new APPMaterias(8, "Ciencias sociales"));
		data.add(new APPMaterias(9, "Psicologia"));
		data.add(new APPMaterias(10, "Quimica"));
		data.add(new APPMaterias(11, "Biologia"));
		data.add(new APPMaterias(12, "IT"));
		
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
