package com.example.proyecto3p;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.YEAR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;

import Global.info;
import pojo.pojo;
import java.util.Calendar;

public class registro extends AppCompatActivity {

    EditText FN;
    EditText Nombre;
    EditText edad;
    EditText Sexo;
    EditText escuela;
    EditText Telefono;
    EditText horas;
    TextView materia_elegida;
    RequestQueue request;
    Toolbar toolbar;
    JsonObjectRequest jsonObjectRequest;
    Spinner Spinner_materia_fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);
        edad = findViewById(R.id.edad);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        Sexo = findViewById(R.id.sexo);
        escuela = findViewById(R.id.MF);
        Telefono = findViewById(R.id.telefono);
        horas = findViewById(R.id.time);
        Nombre = findViewById(R.id.nombre);
        FN = findViewById(R.id.FN);
        materia_elegida = findViewById(R.id.materia_elegida);
        Spinner_materia_fa = findViewById(R.id.materias);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Tpos_materias, android.R.layout.simple_spinner_item);
        Spinner_materia_fa.setAdapter(adapter);
        Spinner_materia_fa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
                materia_elegida.setText(parent.getItemAtPosition(posicion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickfecha();
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
                Intent main= new Intent(this,functions.class);
                startActivity(main);
                return true;
            case R.id.registro:
                Toast.makeText(this, "esta en registro", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contacto:
                addFraggment(new contacto(), false ,"one");
                Toast.makeText(this, "Informacion mia :3", Toast.LENGTH_SHORT).show();
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


    private void onclickfecha() {
        int dia, mes, año;
        Calendar actual = Calendar.getInstance();
        dia = actual.get(DAY_OF_MONTH);
        mes = actual.get(Calendar.MONTH);
        año = actual.get(Calendar.YEAR);
        DatePickerDialog dat = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                pojo.m = month;
                pojo.a = year;
                pojo.d = day;
                String cadena;
                cadena = "" + pojo.d + "/" + pojo.m + "/" + pojo.a;
                FN.setText(cadena);

            }
        },año,mes,dia);
        dat.show();
    }

    public void Guardar(View view) {
        pojo Alumno = new pojo();
        Alumno.setNombre(Nombre.getText().toString());
        Alumno.setEdad(edad.getText().toString());
        Alumno.setFN(FN.getText().toString());
        Alumno.setTelefono(Telefono.getText().toString());
        Alumno.setMF(escuela.getText().toString());
        Alumno.setSexo(Sexo.getText().toString());
        Alumno.setTime(horas.getText().toString());
        Alumno.setMeteria(materia_elegida.getText().toString());
        info.lista.add(Alumno);
        Guardar_BD();
        Toast.makeText(registro.this, "los datos han sido guardados", Toast.LENGTH_SHORT).show();
    }


    public void Guardar_BD()
    {
        String url = "http://192.168.56.1/autodidatta/registro.php?Nombre=";
        url = url + Nombre.getText().toString();
        url = url + "&fecha_Na=";
        url = url + FN.getText().toString();
        url = url + "&escuela=";
        url = url + escuela.getText().toString();
        url = url + "&edad=";
        url = url + edad.getText().toString();
        url = url + "&Sexo=";
        url = url + Sexo.getText().toString();
        url = url + "&Telefono=";
        url = url + Telefono.getText().toString();
        url = url + "&Materia_fa=";
        url = url + materia_elegida.getText().toString();
        url = url + "&horas=";
        url = url + horas.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(registro.this, "Se han subido a la base de datos", Toast.LENGTH_SHORT).show();
                if(!response.isEmpty()) {
                    Intent intent = new Intent(registro.this, functions.class);
                    startActivity(intent);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(registro.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
    private void addFraggment(Fragment fragment, boolean addToBackStack, String tag)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction frg_tr = manager.beginTransaction();
        if(addToBackStack)
        {
            frg_tr.addToBackStack(tag);
        }
        frg_tr.replace(R.id.contenedor_fragmento,fragment,tag);
        frg_tr.commitNowAllowingStateLoss();
    }

}