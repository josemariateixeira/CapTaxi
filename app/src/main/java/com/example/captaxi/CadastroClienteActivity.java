package com.example.captaxi;

import static android.app.PendingIntent.getActivity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CadastroClienteActivity extends AppCompatActivity {
    private CadastroClienteActivity binding;
    private static ImageView fotoperfil;

    static final int REQUEST_IMAGE_CAPTURE = 1;





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extra = data.getExtras();
            Bitmap img = (Bitmap) extra.get("data");
            fotoperfil = findViewById(R.id.fotoperfil);
            fotoperfil.setImageBitmap(img);
        }
    }

    private Activity getActivity() {
        return null;
    }


    private void StartActivityForResult(Intent takePictureIntent, int requestImageCapture) {
    }


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

    public void onViewCreated(View view, Bundle savedInstanceState){
        onViewCreated(view, savedInstanceState);


    fotoperfil = getActivity().findViewById(R.id.fotoperfil);




  }



  public void takePicture(View view) {
      Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
}

