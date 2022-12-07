package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroMotoristaActivity extends AppCompatActivity {


    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private MotoristaDAO dao;
    private Motorista motorista = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_motorista);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        senha = findViewById(R.id.editSenha);
        dao = new MotoristaDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("motorista")){
            motorista = (Motorista) it.getSerializableExtra("motorista");
            nome.setText(motorista.getNome());
            cpf.setText(motorista.getCpf());
            telefone.setText(motorista.getTelefone());
            email.setText(motorista.getEmail());
            senha.setText(motorista.getSenha());
        }

        //getSupportActionBar().hide();
    }
    public void salvar (View view){
        if (motorista == null){

            motorista = new Motorista();
            motorista.setNome(nome.getText().toString());
            motorista.setCpf(cpf.getText().toString());
            motorista.setTelefone(telefone.getText().toString());
            motorista.setEmail(email.getText().toString());
            motorista.setSenha(senha.getText().toString());
            long id = dao.inserir(motorista);
            Toast.makeText(this,"Motorista iserido co id: "+ id, Toast.LENGTH_SHORT).show();
        }else {
            motorista.setNome(nome.getText().toString());
            motorista.setCpf(cpf.getText().toString());
            motorista.setTelefone(telefone.getText().toString());
            motorista.setEmail(email.getText().toString());
            motorista.setSenha(senha.getText().toString());
            dao.atualizar(motorista);
            Toast.makeText(this, "Dados do Motorista foi atualizado", Toast.LENGTH_SHORT) .show();
        }
    }
}