package com.example.proyecto3p;

import static com.example.proyecto3p.login.archivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import Adaptadores.adaptador_lista1;


public class functions extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.functions);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        rv=findViewById(R.id.muestra_m);
        adaptador_lista1 alst = new adaptador_lista1();
        alst.context=this;
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setAdapter(alst);
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
                Toast.makeText(this, "Esta en casa", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.registro:
                Intent res= new Intent(this,registro.class);
                startActivity(res);
                return true;
            case R.id.contacto:
                Intent con= new Intent(this,contacto.class);
                startActivity(con);
                return true;
            case R.id.modificar:
                Intent md = new Intent(this, Modificar_datos.class);
                startActivity(md);
                return true;
            case R.id.user:
                Intent user = new Intent(this, user_lista.class);
                startActivity(user);
                return true;
            case R.id.eliminar_cuenta:
                Intent elm_u = new Intent(this,eliminar_usuario.class);
                startActivity(elm_u);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Salir(View view)
    {
        Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show();
        Intent Cierre_Sesion = new Intent(functions.this, login.class);
        SharedPreferences.Editor editor=archivo.edit();
        editor.remove("Usuario");
        editor.remove("llave");
        editor.remove("valido");
        editor.commit();
        startActivity(Cierre_Sesion);
        finish();
    }

}