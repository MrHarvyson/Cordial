package com.josec.cordial.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.josec.cordial.R;
import com.josec.cordial.databinding.ActivityMainHomeBinding;

public class MainHome extends AppCompatActivity {

    ActivityMainHomeBinding binding;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para ocultar barra con el titulo
        getSupportActionBar().hide();


        binding = ActivityMainHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = navHostFragment.getNavController();


        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navegation_home:
                    navController.navigate(R.id.homeFragment);
                    break;
                case R.id.navegation_incidencias:
                    navController.navigate(R.id.incidenciaFragment);
                    break;
                case R.id.navegation_notas:
                    navController.navigate(R.id.notaFragment);
                    break;
            }

            return true;
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainHome.this);

        LayoutInflater inflater= getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog,null);

        builder.setView(view);


        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        Button si = view.findViewById(R.id.btnSi);
        Button no = view.findViewById(R.id.btnNo);

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mGoogleSignInClient.signOut().addOnCompleteListener(task -> {
                });
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}