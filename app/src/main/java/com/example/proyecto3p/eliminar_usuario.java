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
import android.view.View;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import Adaptadores.adaptador_lista3;
import Global.info;

public class eliminar_usuario extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView eliminar_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        eliminar_usuario = findViewById(R.id.recyclerView_eliminar_us);
        adaptador_lista3 adpt_lst3 = new adaptador_lista3();
        adpt_lst3.contexto_user_eliminar = this;

        LinearLayoutManager llm3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        eliminar_usuario.setLayoutManager(llm3);
        eliminar_usuario.setAdapter(adpt_lst3);
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
                Intent main= new Intent(this,functions.class);
                startActivity(main);
                return true;
            case R.id.registro:
                Intent res= new Intent(this,registro.class);
                startActivity(res);
                return true;
            case R.id.contacto:
                Intent con= new Intent(this,contacto.class);
                startActivity(con);
                return true;
            case R.id.user:
                Intent us= new Intent(this,user.class);
                startActivity(us);
                return true;
            case R.id.eliminar_cuenta:
                Toast.makeText(this, "estas en eliminar", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Eliminar_BD(int i)
    {
        String url = "http://192.168.56.1/autodidatta/elimina.php?Nombre=";
        url += info.Lista_e.get(i).getNombre();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(eliminar_usuario.this, "Se elimino", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(eliminar_usuario.this, "error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
   public void Eliminar(View view)
   {
       for(int i = 0; i < info.Lista_e.size(); i++)
       {
           info.lista.remove(info.Lista_e.get(i));
           Eliminar_BD(i);
       }
       info.Lista_e.clear();
       eliminar_usuario.getAdapter().notifyDataSetChanged();
   }
}