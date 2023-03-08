package com.josec.cordial.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.josec.cordial.R;
import com.josec.cordial.models.Alumno;
import com.josec.cordial.providers.AlumnoProvider;
import com.josec.cordial.providers.AuthProvider;

public class MainInicio extends AppCompatActivity {

    TextView nombre;
    Button salir;
    AlumnoProvider alumnoProvider;
    String id = MainActivity.id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        nombre = findViewById(R.id.txtFireNombre);
        alumnoProvider = new AlumnoProvider();
        salir = findViewById(R.id.btnSalir);

        alumnoProvider.getUser(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name = documentSnapshot.getString("nombre");
                nombre.setText("Nombre de usuario: " + name);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mGoogleSignInClient.signOut().addOnCompleteListener(task -> {
                    finish();
                });

            }
        });

    }
}