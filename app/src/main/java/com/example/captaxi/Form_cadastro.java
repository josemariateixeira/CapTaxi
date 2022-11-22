package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Form_cadastro extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private ClienteDAO dao;
    private Cliente cliente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        senha = findViewById(R.id.editSenha);
        dao = new ClienteDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("cliente")){
            cliente = (Cliente) it.getSerializableExtra("cliente");
            nome.setText(cliente.getNome());
            cpf.setText(cliente.getCpf());
            telefone.setText(cliente.getTelefone());
            email.setText(cliente.getEmail());
            senha.setText(cliente.getSenha());
        }

        //getSupportActionBar().hide();
    }
    public void salvar (View view){
        if (cliente == null){

        cliente = new Cliente();
        cliente.setNome(nome.getText().toString());
        cliente.setCpf(cpf.getText().toString());
        cliente.setTelefone(telefone.getText().toString());
        cliente.setEmail(email.getText().toString());
        cliente.setSenha(senha.getText().toString());
        long id = dao.inserir(cliente);
        Toast.makeText(this,"Cliente iserido co id: "+ id, Toast.LENGTH_SHORT).show();
      }else {
            cliente.setNome(nome.getText().toString());
            cliente.setCpf(cpf.getText().toString());
            cliente.setTelefone(telefone.getText().toString());
            cliente.setEmail(email.getText().toString());
            cliente.setSenha(senha.getText().toString());
            dao.atualizar(cliente);
            Toast.makeText(this, "Dados do cliente foi atualizado", Toast.LENGTH_SHORT) .show();
        }
    }
}