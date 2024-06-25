package com.example.proyecto3p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Global.info;

public class user extends AppCompatActivity {
    Toolbar toolbar;
    TextView nom;
    TextView edad;
    TextView sexo;
    TextView materia;
    TextView escuela;
    TextView fechaNa;
    TextView horas;
    TextView telefono;
    Button llamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        nom = findViewById(R.id.m_nombre);
        edad = findViewById(R.id.m_edad);
        telefono = findViewById(R.id.m_tel);
        sexo = findViewById(R.id.m_sexo);
        materia = findViewById(R.id.m_mateF);
        escuela = findViewById(R.id.m_escuela);
        fechaNa = findViewById(R.id.m_nacimiento);
        horas = findViewById(R.id.m_horas);
        llamar=findViewById(R.id.telefono_u);

        int posicion;
        posicion = getIntent().getIntExtra("posi", -1);
        nom.setText(info.lista.get(posicion).getNombre());
        edad.setText(info.lista.get(posicion).getEdad());
        telefono.setText(info.lista.get(posicion).getTelefono());
        sexo.setText(info.lista.get(posicion).getSexo());
        materia.setText(info.lista.get(posicion).getMeteria());
        fechaNa.setText(info.lista.get(posicion).getFN());
        horas.setText(info.lista.get(posicion).getTime());
        escuela.setText(info.lista.get(posicion).getMF());

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent llamar = new Intent(Intent.ACTION_CALL);
                llamar.setData(Uri.parse("tel:"+telefono.getText().toString()));
                if(ActivityCompat.checkSelfPermission(user.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(
                            user.this, new String[]{Manifest.permission.CALL_PHONE},10
                    );
                    return;
                }
                startActivity(llamar);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent fun = new Intent(this,functions.class);
                startActivity(fun);
            case R.id.registro:
                Intent res= new Intent(this,registro.class);
                startActivity(res);
                return true;
            case R.id.modificar:
                Intent md = new Intent(this, Modificar_datos.class);
                startActivity(md);
                return true;
            case R.id.user:
                Intent lista = new Intent(this,user_lista.class);
                startActivity(lista);
                return true;
            case R.id.eliminar_cuenta:
                Intent elm_u = new Intent(this,eliminar_usuario.class);
                startActivity(elm_u);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}