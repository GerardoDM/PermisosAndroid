package com.example.permisostoggle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        verificarPermisos(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE});

    }

    private void verificarPermisos(String[] permisos){
        ArrayList<String> PermisosNegados = new ArrayList<String>();

        for(int x = 0; x <= permisos.length; x++){

            if (ContextCompat.checkSelfPermission(this, permisos[x]) != PackageManager.PERMISSION_GRANTED){
                PermisosNegados.add(permisos[x]);
            }
        }

        if(PermisosNegados.size()>= 1){
            Intent ipp = new Intent(this,MainActivity.class);
            ipp.putExtra("Permisos", PermisosNegados);
            startActivity(ipp);
        }
    }


}
