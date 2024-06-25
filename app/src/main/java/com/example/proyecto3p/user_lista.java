package com.example.proyecto3p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import Adaptadores.adaptador_lista2;

public class user_lista extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lista);
        toolbar = findViewById(R.id.toolbar);
        rv2=findViewById(R.id.vlistas);
        adaptador_lista2 alst2 = new adaptador_lista2();
        alst2.context=this;

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(llm);
        rv2.setAdapter(alst2);
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
                Toast.makeText(this, "Esta en usuario", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.eliminar_cuenta:
                Intent elm_u = new Intent(this,eliminar_usuario.class);
                startActivity(elm_u);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}