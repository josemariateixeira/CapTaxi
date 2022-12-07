package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MotoristaLoginActivity extends AppCompatActivity {

    private TextView tela_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista_login);

        //  getSupportActionBar().hide();
        IniciarConmponentes();


        tela_cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotoristaLoginActivity.this, CadastroMotoristaActivity.class);
                startActivity(intent);

            }
        });
    }

    private void IniciarConmponentes(){
        tela_cadastro = findViewById(R.id.tela_cadastro);
    }


}