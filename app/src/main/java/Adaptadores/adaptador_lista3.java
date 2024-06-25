package Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3p.R;

import Global.info;

public class adaptador_lista3 extends RecyclerView.Adapter<adaptador_lista3.MiActividad3>
{
    public Context contexto_user_eliminar;
    @NonNull
    @Override
    public adaptador_lista3.MiActividad3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(contexto_user_eliminar, R.layout.vista3, null);
        MiActividad3 obj = new MiActividad3(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull adaptador_lista3.MiActividad3 Mi_Actividad, int position) {
    final int posicion = position;
    Mi_Actividad.nombre.setText(info.lista.get(posicion).getNombre());
    Mi_Actividad.edad.setText(info.lista.get(posicion).getEdad());
    Mi_Actividad.eliminar_usuario.setChecked(false);
    Mi_Actividad.eliminar_usuario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(((CheckBox)view).isChecked())
            {
                info.Lista_e.add(info.lista.get(posicion));
            }
            else
            {
                info.Lista_e.remove(info.lista.get(posicion));
            }
        }
    });
    }

    @Override
    public int getItemCount() {return info.lista.size();}

    public class MiActividad3 extends RecyclerView.ViewHolder {
        TextView    nombre,edad;
        CheckBox eliminar_usuario;
        public MiActividad3(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.Nombre_Vista3);
            edad = itemView.findViewById(R.id.edad_Vista3);
            eliminar_usuario = itemView.findViewById(R.id.checkbox_vista3);
        }
    }
}
