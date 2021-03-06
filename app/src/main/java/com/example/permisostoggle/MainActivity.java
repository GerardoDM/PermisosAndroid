package com.example.permisostoggle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final int CONTACTS_PERMISSION_CODE = 1;
    public static final int STORAGE_PERMISSION_CODE = 2;
    public static final int CALENDAR_PERMISSION_CODE = 3;
    public static final int SMS_PERMISSION_CODE = 4;
    public static final int VOICEMAIL_PERMISSION_CODE = 5;

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

        Button botonCambiar = (Button) findViewById(R.id.botonCambiar);
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Recicler.class));

            }
        });

        //Checked On Change Listener para Switch

        switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Ya concediste Contacts", Toast.LENGTH_LONG).show();


                }

                else {
                    requestStoragePermission();
                }
            }
        });

        switchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Ya concediste Storage", Toast.LENGTH_LONG).show();
                }

                else {
                    requestStoragePermission();
                }
            }
        });

        switchThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Ya concediste Calendar", Toast.LENGTH_LONG).show();
                }

                else {
                    requestStoragePermission();
                }
            }
        });

        switchFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Ya concediste SMS", Toast.LENGTH_LONG).show();
                }

                else {
                    requestStoragePermission();
                }
            }
        });

        switchFive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_VOICEMAIL) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Ya concediste Voicemail", Toast.LENGTH_LONG).show();
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
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                                    Manifest.permission.READ_CONTACTS,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_CALENDAR,
                                    Manifest.permission.READ_SMS,
                                    Manifest.permission.READ_VOICEMAIL},1);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_VOICEMAIL}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CONTACTS_PERMISSION_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_CONTACTS)) {
                    // check whether camera permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"Permiso Contactos concedido", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this, "Permiso Contactos negado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"Permiso Storage concedido", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(this, "Permiso Storage negado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case CALENDAR_PERMISSION_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_CALENDAR)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"Permiso Calendar concedido", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(this, "Permiso Calendar negado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case SMS_PERMISSION_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_SMS)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"Permiso SMS concedido", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(this, "Permiso SMS negado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case VOICEMAIL_PERMISSION_CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_VOICEMAIL)) {
                    // check whether storage permission granted or not.
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"Permiso Voicemail concedido", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(this, "Permiso Voicemail negado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }



}
