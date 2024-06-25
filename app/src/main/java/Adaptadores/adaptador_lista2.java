package Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3p.R;
import com.example.proyecto3p.user;

import org.w3c.dom.Text;

import Global.info;

public class adaptador_lista2 extends RecyclerView.Adapter<adaptador_lista2.MiActivity2> {
   public Context context;
    @NonNull
    @Override
    public MiActivity2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v2 = View.inflate(context, R.layout.vista2, null);
        MiActivity2 obj = new MiActivity2(v2);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull adaptador_lista2.MiActivity2 MiActividad2, int position) {
    final int pos=position;
    MiActividad2.nombre.setText(info.lista.get(position).getNombre());
    MiActividad2.edad.setText(info.lista.get(position).getEdad());
    MiActividad2.nombre.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent card = new Intent(context, user.class);
            card.putExtra("posi", pos);
            context.startActivity(card);
        }
    });
    }

    @Override
    public int getItemCount() {
        return info.lista.size();
    }

    public class MiActivity2 extends RecyclerView.ViewHolder {
        TextView nombre, edad;
        public MiActivity2(@NonNull View itemView) {
            super(itemView);
            nombre =itemView.findViewById(R.id.nombre_v2);
            edad = itemView.findViewById(R.id.edad_v2);
        }
    }
}
