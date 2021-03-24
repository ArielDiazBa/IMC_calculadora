package com.example.fitnnes_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_aceptar;
    private EditText txt_nombre;
    private EditText txt_apellidos;
    private EditText txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_aceptar = findViewById(R.id.btn_aceptar);
        txt_nombre = findViewById(R.id.txt_nombre1);
        txt_apellidos = findViewById(R.id.txt_apellido1);
        txt_email = findViewById(R.id.txt_email);
        btn_aceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_aceptar){
            String name = txt_nombre.getText().toString();
            String apellido = txt_apellidos.getText().toString();
            String email = txt_email.getText().toString();
            if (name.isEmpty()){
                CustomToastView.makeErrorToast(this,"Error al validar el Nombre",R.layout.custom_toast ).show();
                return;
            }
            if (apellido.isEmpty()){
                CustomToastView.makeErrorToast(this,"Error al validar el Apellido",R.layout.custom_toast ).show();
                return;
            }
            if (!isvalidemail(email)){
                CustomToastView.makeErrorToast(this,"Error al validar el Email",R.layout.custom_toast ).show();
                return;
            }
            Intent myIntent = new Intent(this, Calculadora_IMC.class);
            myIntent.putExtra("namecalculator",name);
            myIntent.putExtra("apellidocalculator",apellido);
            myIntent.putExtra("emailcalculator",email);
            startActivity(myIntent);
        }
    }

    private Boolean isvalidemail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}