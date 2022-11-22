package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Forme_login extends AppCompatActivity {

    private TextView text_tela_cadastro;
    Button btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forme_login);

      //  getSupportActionBar().hide();
        IniciarConmponentes();

        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(getApplicationContext(),TelaMenuPrincipalActivity.class);
                startActivity(menu);
            }
        });


        text_tela_cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forme_login.this,Form_cadastro.class);
                startActivity(intent);

            }
        });
    }

    private void IniciarConmponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
    }


}