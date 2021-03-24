package com.example.fitnnes_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Calculadora_IMC extends AppCompatActivity implements View.OnClickListener {

    private TextView tvinformacion;
    private EditText txtpeso;
    private EditText txtaltura;
    private Button btncalcular;
    private ImageView imgestado;
    private  TextView tvresultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora__i_m_c);

        Intent intent = getIntent();
        String name = intent.getStringExtra("namecalculator");
        String apellido = intent.getStringExtra("apellidocalculator");
        String email = intent.getStringExtra("emailcalculator");
        String mensaje = "Hola  "+name+"  "+apellido+"  es un gusto tenerte aca su correo para el informe es: "+email;

        tvinformacion = findViewById(R.id.tvinformacion);
        tvresultado = findViewById(R.id.tvresultado);
        txtpeso = findViewById(R.id.txtpeso);
        txtaltura = findViewById(R.id.txtaltura);
        btncalcular = findViewById(R.id.btncalcular);
        imgestado = findViewById(R.id.imgestado);
        tvinformacion.setText(mensaje);
        btncalcular.setOnClickListener(this);


    }

    public double CalcularIMC(){
        double res;
        double peso = Double.parseDouble(txtpeso.getText().toString());
        double altura = Double.parseDouble(txtaltura.getText().toString());

        res = peso/(Math.pow((altura/100),2));

        return res;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btncalcular){
            double resIMC = CalcularIMC();
            String MensajeFinal = "";
            Intent intent = getIntent();
            String Nombre = intent.getStringExtra("namecalculator");
            String Apellido = intent.getStringExtra("apellidocalculator");
            String Email = intent.getStringExtra("emailcalculator");

            if (resIMC < 18.5){
                MensajeFinal =  Nombre + " " + Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de BAJO PESO";
                imgestado.setImageResource(R.drawable.bajopeso);
            }
            if (resIMC >= 18.5 && resIMC <= 24.9){
                MensajeFinal =  Nombre + " " +  Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de PESO NORMAL O SALUDABLE";
                imgestado.setImageResource(R.drawable.normal);
            }
            if (resIMC >= 25 && resIMC <= 29.9){
                MensajeFinal =  Nombre + " " +  Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de SOBREPESO";
                imgestado.setImageResource(R.drawable.sobrepeso);
            }
            if (resIMC > 30){
                MensajeFinal =  Nombre + " " +  Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de OBECIDAD";
                imgestado.setImageResource(R.drawable.obeso);
            }

            tvresultado.setText(MensajeFinal);
        }
    }
}