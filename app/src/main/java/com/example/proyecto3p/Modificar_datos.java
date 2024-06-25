package com.example.proyecto3p;

import static java.util.Calendar.DAY_OF_MONTH;

import static Global.info.lista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;

import Global.info;
import pojo.pojo;


public class Modificar_datos extends AppCompatActivity {
    Toolbar toolbar;
    int Posicion = 0;
    EditText nombre, edad, sexo, horas,escuela,fn, tel;
    TextView materia_e_m;
    Spinner materias_m;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        nombre = findViewById(R.id.nombre_m);
        edad = findViewById(R.id.edad_m);
        sexo = findViewById(R.id.sexo_m);
        horas = findViewById(R.id.time_m);
        escuela = findViewById(R.id.MF_m);
        fn = findViewById(R.id.FN_m);
        materia_e_m = findViewById(R.id.materia_elegida_m);
        tel = findViewById(R.id.telefono_m);
        materias_m = findViewById(R.id.materias_m);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Tpos_materias, android.R.layout.simple_spinner_item);
        materias_m.setAdapter(adapter);
        materias_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
                materia_e_m.setText(parent.getItemAtPosition(posicion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickfecha();
            }
        });
        Mostrar();
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
                fn.setText(cadena);

            }
        },año,mes,dia);
        dat.show();
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
                Toast.makeText(this, "esta en modificar", Toast.LENGTH_SHORT).show();
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

    public void Mostrar()
    {
        if(!lista.isEmpty())
        {
            nombre.setText(info.lista.get(Posicion).getNombre());
            a = nombre.getText().toString();
            edad.setText(info.lista.get(Posicion).getEdad());
            tel.setText(info.lista.get(Posicion).getTelefono());
            sexo.setText(info.lista.get(Posicion).getSexo());
            materia_e_m.setText(info.lista.get(Posicion).getMeteria());
            fn.setText(info.lista.get(Posicion).getFN());
            horas.setText(info.lista.get(Posicion).getTime());
        }else
        {
            Toast.makeText(this,"lista vacia", Toast.LENGTH_SHORT).show();
        }
    }

    public void Atras(View view)
    {
        if(!lista.isEmpty())
        {
            if(Posicion == 0)
            {
                if(lista.size() == 1){
                    Toast.makeText(this, "Solo un dato", Toast.LENGTH_SHORT).show();
                }
                else{
                    Posicion = lista.size() - 1;
                    Mostrar();
                }
            }else
            {
                Posicion = Posicion -1;
                Mostrar();
            }

        }else{
            Toast.makeText(this, "No existen datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Siguiente(View view)
    {
        if (!lista.isEmpty())
        {

            if(Posicion == lista.size() -1)
            {
                if (lista.size() == 1)
                {
                    Toast.makeText(this, "Solo hay un dato", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Posicion = 0;
                    Mostrar();
                }
            }
            else
            {
                Posicion = Posicion +1;
                Mostrar();
            }
        }
        else
        {
            Toast.makeText(this, "Lista vacia", Toast.LENGTH_SHORT).show();
        }
    }

    public void Guardar(View view)
    {
        if(!lista.isEmpty())
        {
            Guardar();
        }
        else
        {
            Toast.makeText(this, "Lista Completamente vacia", Toast.LENGTH_SHORT).show();
        }

    }

    private void Guardar() {
        lista.get(Posicion).setNombre(nombre.getText().toString());
        lista.get(Posicion).setEdad(edad.getText().toString());
        lista.get(Posicion).setSexo(sexo.getText().toString());
        lista.get(Posicion).setMeteria(materia_e_m.getText().toString());
        lista.get(Posicion).setMF(escuela.getText().toString());
        lista.get(Posicion).setTime(horas.getText().toString());
        lista.get(Posicion).setTelefono(tel.getText().toString());
        lista.get(Posicion).setFN(fn.getText().toString());
        Guardar_BD();
        Toast.makeText(this, "los datos se han cambiado con exito", Toast.LENGTH_SHORT).show();
    }

    public void Guardar_BD()
    {
        String url = "http://192.168.56.1/autodidatta/modificar.php?Aux=";
        url += a;
        url += "&Nombre=";
        url = url + nombre.getText().toString();
        url = url + "&fecha_Na=";
        url = url + fn.getText().toString();
        url = url + "&escuela=";
        url = url + escuela.getText().toString();
        url = url + "&edad=";
        url = url + edad.getText().toString();
        url = url + "&Sexo=";
        url = url + sexo.getText().toString();
        url = url + "&Telefono=";
        url = url + tel.getText().toString();
        url = url + "&Materia_fa=";
        url = url + materia_e_m.getText().toString();
        url = url + "&horas=";
        url = url + horas.getText().toString();

        a = nombre.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Modificar_datos.this, "Se actualizaron los datos", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Modificar_datos.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}