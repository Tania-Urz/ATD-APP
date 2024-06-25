package Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3p.R;

import Global.info;

//muestra materia
public class adaptador_lista1 extends RecyclerView.Adapter<adaptador_lista1.MiActivity> {
    public Context context;
    @NonNull
    @Override
    public adaptador_lista1.MiActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.vista1,null);
        MiActivity objeto = new MiActivity(v);
        return objeto;
    }

    @Override
    public void onBindViewHolder(@NonNull adaptador_lista1.MiActivity MiActividad, int position) {
        final int posicion = position;
        MiActividad.materia.setText(info.lista.get(posicion).getMeteria());
        MiActividad.horas.setText(info.lista.get(posicion).getTime());
        MiActividad.horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return info.lista.size();
    }

    public class MiActivity extends RecyclerView.ViewHolder {
        TextView materia, horas;

        public MiActivity(@NonNull View itemView) {
            super(itemView);
            materia = itemView.findViewById(R.id.materia);
            horas = itemView.findViewById(R.id.horas);
        }
    }
}
