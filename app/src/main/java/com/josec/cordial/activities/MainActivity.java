package com.josec.cordial.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.josec.cordial.Animacion;
import com.josec.cordial.R;
import com.josec.cordial.models.Alumno;
import com.josec.cordial.providers.AlumnoProvider;
import com.josec.cordial.providers.AuthProvider;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView logo, google;
    TextView marca, version;
    public static String id;
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;
    public static GoogleSignInClient mGoogleSignInClient;
    AuthProvider mAuthProvider;
    AlumnoProvider mAlumnoProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();

        logo = findViewById(R.id.imgLogo);
        marca = findViewById(R.id.imgMarca);
        google = findViewById(R.id.imgGoogle);
        version = findViewById(R.id.txtVersion);

        mAuthProvider = new AuthProvider();
        mAlumnoProvider = new AlumnoProvider();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google.setOnClickListener(v ->{
            signIn();
        });
    }

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuthProvider.googleLogin(idToken).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            id = mAuthProvider.getUid();
                            usuarioExistente(id);
                            Log.d(TAG, "signInWithCredential:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
    // [END auth_with_google]

    private void usuarioExistente(String id){
        mAlumnoProvider.getUser(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    Animacion anim = new Animacion(logo,marca,google,version);
                    Intent intent = new Intent(MainActivity.this,MainInicio.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, anim.animacion());
                    startActivity(intent, options.toBundle());
                }else{
                    String email = mAuthProvider.getEmail();
                    Alumno alumno = new Alumno();
                    alumno.setId(id);
                    alumno.setEmail(email);
                    mAlumnoProvider.creacionAlumno(alumno).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Animacion anim = new Animacion(logo,marca,google,version);
                                Intent intent = new Intent(MainActivity.this,MainCompletarRegistro.class);
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, anim.animacion());
                                startActivity(intent, options.toBundle());
                            }else{
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}