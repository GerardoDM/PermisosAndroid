package com.example.permisostoggle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        // initiate a Switch
        Switch switchOne = (Switch) findViewById(R.id.switchOne);
        Switch switchTwo = (Switch) findViewById(R.id.switchTwo);
        Switch switchThree = (Switch) findViewById(R.id.switchThree);
        Switch switchFour = (Switch) findViewById(R.id.switchFour);
        Switch switchFive = (Switch) findViewById(R.id.switchFive);


        // check current state of a Switch (true or false).
        Boolean switchOneChecked = switchOne.isChecked();

        Boolean switchTwoChecked = switchTwo.isChecked();
        Boolean switchThreeChecked = switchThree.isChecked();
        Boolean switchFourChecked = switchFour.isChecked();
        Boolean switchFiveChecked = switchFive.isChecked();


        //Checked On Change Listener para Switch
        switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Ya concediste este permiso", Toast.LENGTH_LONG).show();
                }

                else {
                    requestStoragePermission();
                }
            }
        });


    }

    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permiso requerido")
                    .setMessage("Este permiso es requerido")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS}, STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("Canceñar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }

        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permiso concedido", Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(this, "Permiso negado", Toast.LENGTH_LONG).show();
            }
        }
    }
}
