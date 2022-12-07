package com.example.captaxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {

    public final Conexao2 conexao2;
    private final SQLiteDatabase banco2;

    public MotoristaDAO(Context context) {
        conexao2 = new Conexao2(context);
        banco2 = conexao2.getWritableDatabase();
    }

    public long inserir(Motorista motorista) {
        ContentValues values = new ContentValues();
        values.put("nome", motorista.getNome());
        values.put("cpf", motorista.getCpf());
        values.put("telefone", motorista.getTelefone());
        values.put("email", motorista.getEmail());
        values.put("senha", motorista.getSenha());
        return banco2.insert("motorista", null, values);
    }

    public List<Motorista> obterTodos() {
        List<Motorista> motoristas = new ArrayList<>();
        try (Cursor cursor = banco2.query("motorista", new String[]{"id", "nome", "cpf", "telefone", "email"},
                null, null, null, null, null)) {
            while (cursor.moveToNext()) {
               Motorista m = new Motorista();
                m.setId(cursor.getInt(0));
                m.setNome(cursor.getString(1));
                m.setCpf(cursor.getString(2));
                m.setTelefone(cursor.getString(3));
                m.setEmail(cursor.getString(4));
                motoristas.add(m);
            }
        }
        return motoristas;
    }

    public void excluir(Motorista m) {
        banco2.delete("cliente", "id = ?", new String[]{m.getId().toString()});
    }

    public void atualizar(Motorista m) {
        ContentValues values = new ContentValues();
        values.put("nome",m.getNome());
        values.put("cpf", m.getCpf());
        values.put("telefone", m.getTelefone());
        values.put("email", m.getEmail());
        values.put("senha", m.getSenha());
        banco2.update("cliente", values,
                "id = ?", new String[]{m.getId().toString()});
    }


}
