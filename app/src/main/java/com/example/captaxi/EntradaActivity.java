package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntradaActivity extends AppCompatActivity {
    private Button mCliente, mMotorista, telaPrincial,mapa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        mapa = findViewById(R.id.btn_mapa);
        mCliente = findViewById(R.id.btn_cliente);
        mMotorista = findViewById(R.id.btn_motorista);
        telaPrincial = findViewById(R.id.btn_telaPrincipal);


        mCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntradaActivity.this, ClienteLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }

        });

        mMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntradaActivity.this, MotoristaLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }

        });

        telaPrincial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntradaActivity.this, TelaMenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return;
            }

        });

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntradaActivity.this, MapaActivity.class);
                startActivity(intent);
                finish();
                return;
            }

        });


    }
}