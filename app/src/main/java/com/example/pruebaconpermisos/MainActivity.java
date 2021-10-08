package com.example.pruebaconpermisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] permisos = {Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            requestPermissions(permisos, 100);
        }
    }


    public void comprobarPermisos(View view){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Ya se poseen todos los permisos necesarios", Toast.LENGTH_SHORT).show();
            } else {
                solicitarPermisos();
            }

        }
    }

    private void solicitarPermisos(){
        new AlertDialog.Builder(this)
                .setTitle("Se requieren permisos")
                .setMessage("Se necesitan permisos de cámara y acceso telefónico para que la aplicación pueda funcionar" +
                        "sin problmeas")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions(permisos, 100);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(!(grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                Toast.makeText(this,"Se requiere autorización de la cámara", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Acceso a cámara concedido", Toast.LENGTH_SHORT).show();
            }

            if(!(grantResults[1] == PackageManager.PERMISSION_GRANTED)){
                Toast.makeText(this, "Se requiere acceso al teléfono para funcionar", Toast.LENGTH_SHORT).show();
            }
        }

    }
}