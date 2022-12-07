package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClienteLoginActivity extends AppCompatActivity {

    TextView telaCadastro;
    EditText nomeCliente, senhaCliente;
    Button btEntrar;
    Conexao conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_login);

        telaCadastro = findViewById(R.id.tela_cadastro);
        nomeCliente = findViewById(R.id.edit_email);
        senhaCliente = findViewById(R.id.edit_senha);
        btEntrar = findViewById(R.id.bt_entrar);
        conexao = new Conexao(this);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cliente = nomeCliente.getText().toString();
                String senha = senhaCliente.getText().toString();

                if (cliente.equals("") || senha.equals(""))
                    Toast.makeText(ClienteLoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else {
                   Boolean consultar = conexao.consultaCliente_Senha(cliente, senha);
                        if (consultar){
                            Toast.makeText(ClienteLoginActivity.this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), TelaMenuPrincipalActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ClienteLoginActivity.this, "Dados inválidos", Toast.LENGTH_SHORT).show();
                        }

                }

            }
        });

        telaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteLoginActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });





    }

}