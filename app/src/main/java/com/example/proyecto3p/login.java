package com.example.proyecto3p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    EditText User, password;
    TextView msg;
    public static SharedPreferences archivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User = findViewById(R.id.usuario1);
        password = findViewById(R.id.contraseña1);
        msg = findViewById(R.id.msg);
        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);


    }

    public void Ingresar(View view)
    {
        String url = "http://192.168.56.1/autodidatta/ingreso.php?User=";
        url += User.getText().toString();
        url += "&password=";
        url += password.getText().toString();
        JsonObjectRequest peticion = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("User") != -1) {
                        Intent i = new Intent(login.this, registro.class);
                        SharedPreferences.Editor editor = archivo.edit();
                        editor.putInt("ID", response.getInt("User"));
                        editor.commit();
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(login.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
                Toast.makeText(login.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("yo", error.getMessage());
                Toast.makeText(login.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                msg.setText(error.getMessage());
            }
        });
        RequestQueue mandarPeticion = Volley.newRequestQueue(this);
        mandarPeticion.add(peticion);
    }
}