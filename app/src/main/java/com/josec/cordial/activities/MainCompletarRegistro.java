package com.josec.cordial.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.josec.cordial.R;
import com.josec.cordial.models.Alumno;
import com.josec.cordial.providers.AlumnoProvider;
import com.josec.cordial.providers.AuthProvider;

public class MainCompletarRegistro extends AppCompatActivity {

    ImageView logo, google;
    TextView marca, version;
    EditText nombre, apellidoPrimero, apellidoSegundo;
    Button entrar;
    AlumnoProvider mAlumnoProvider;
    AuthProvider mAuthProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_completar_registro);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        nombre = findViewById(R.id.txtNombre);
        apellidoPrimero = findViewById(R.id.txtPrimerApellido);
        apellidoSegundo = findViewById(R.id.txtSegundoApellido);
        entrar = findViewById(R.id.btnFinalizar);
        logo = findViewById(R.id.imgLogo);
        google = findViewById(R.id.imgProfesora);
        marca = findViewById(R.id.txtMarca);
        version = findViewById(R.id.txtVersion);

        mAlumnoProvider = new AlumnoProvider();
        mAuthProvider = new AuthProvider();

        entrar.setOnClickListener(v -> {
            updateAlumno(nombre.getText().toString(), apellidoPrimero.getText().toString(), apellidoSegundo.getText().toString());
        });
    }

    public void updateAlumno(String nombre, String apellidoPrimero, String apellidoSegundo){
        String id = mAuthProvider.getUid();
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setPrimerApellido(apellidoPrimero);
        alumno.setSegundoApellido(apellidoSegundo);
        alumno.setId(id);
        mAlumnoProvider.updateAlumno(alumno).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(MainCompletarRegistro.this,MainInicio.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}