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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbedoy.apprende.models.Temario;
import com.cbedoy.apprende.viewcontrollers.ExamActivity;
import com.uandroides.aprende.R;

public class ThemeView extends BaseAdapter {
	public TextView materia;
	public TextView tema, descripcion;
	public Button seleccion;
	public ArrayList<Temario> temario;
	Activity contexto;
	private String[] niveles = new String[] { "FACIL", "INTERMEDIO",
	"DIFICIL"};
	public ThemeView(Activity contexto) {
		temario = new ArrayList<Temario>();
		temario.add(new Temario(99, "Examen Demo", "Este es un examen que contiene multiples temas sobre diferentes materias en este comprobaras tus conocimeintos genelares", "Demo"));
		//for(int i=0, k=100; i<data.size(); i++, k++){
		//temario.add(new APPTemario(k, "Tema #1", "Temas selectos del area de "+data.get(i).getNombre(), data.get(i).getNombre()));
		//temario.add(new APPTemario(k, "Tema #2", "Temas selectos del area de "+data.get(i).getNombre(), data.get(i).getNombre()));
		//temario.add(new APPTemario(k, "Tema #3", "Temas selectos del area de "+data.get(i).getNombre(), data.get(i).getNombre()));
		//}
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return temario.size();
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

			View vistaView = inflate.inflate(R.layout.item_tema, null);

			convertView = vistaView;
		}

		if (convertView != null) {
			LinearLayout layout = (LinearLayout) convertView;

			tema = (TextView) layout.findViewById(R.id.nombre_tema);
			tema.setText(temario.get(position).getNombre());
			seleccion = (Button) layout.findViewById(R.id.boton_tema);
			
			descripcion = (TextView) layout
					.findViewById(R.id.descripcion_tema);
			descripcion.setText((temario.get(position).getDescripcion()
					.equals("null") ? "" : temario.get(position)
					.getDescripcion()));
			
			Typeface tf = Typeface.createFromAsset(contexto.getAssets(),"font/dudu.ttf");
			
			tema.setTypeface(tf);
			seleccion.setTypeface(tf);
			descripcion.setTypeface(tf);
			
			
			seleccion.setVisibility(View.VISIBLE);
			seleccion.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						showMensaje(position, tema, descripcion);

					}
				});


			

		}
		return convertView;

	}

	private void showMensaje(final int position, final TextView tema,
			TextView descripcion) {
		AlertDialog.Builder mensaje = new AlertDialog.Builder(contexto);
		mensaje.setTitle("Tema #" + temario.get(position).getIdTema());
		mensaje.setMessage("Usted selecciono el tema: "
				+ tema.getText().toString().toUpperCase()
				+ ", los detalles son los " + "siguientes: "
				+ descripcion.getText().toString().toUpperCase());
		mensaje.setPositiveButton("Ok",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showSelectNivel(position);

					}
				});
		mensaje.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		mensaje.show();
	}

	private void showSelectNivel(int position) {
		AlertDialog.Builder mensaje = new AlertDialog.Builder(contexto);
		mensaje.setTitle("Seleccione la dificultad");
		mensaje.setCancelable(false);

		mensaje.setSingleChoiceItems(niveles , 0, null);

		mensaje.setPositiveButton("Seleccionar",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						int index = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
			            Log.i("depu", ""+index);  
						
						
							Intent i = new Intent(contexto,
									ExamActivity.class);
							i.putExtra("dificultad", index);
							i.putExtra("nombre_tema", tema.getText());
							contexto.startActivity(i);
							contexto.finish();
							
					}
						
		
					
				});

		mensaje.show();
	}
}