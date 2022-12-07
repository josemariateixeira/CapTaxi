package com.example.captaxi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;


    public Conexao (Context context){
        super (context, name, null, version);
    }


@Override
public void onCreate(SQLiteDatabase dbC){
        dbC.execSQL("create table cliente (id integer primary key autoincrement, "+
                "nome varchar(50), cpf varchar (50), telefone varchar (50), email varchar (50), senha varchar (50))");

}

    public Boolean consultarCliente(String nomeCliente){
        SQLiteDatabase dbC = this.getReadableDatabase();
        Cursor cursor = dbC.rawQuery("Select * from clientes where nomeCliente = ?", new String[]{nomeCliente});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean consultaCliente_Senha(String nomeCliente, String senhaCliente){
        SQLiteDatabase dbC = this.getReadableDatabase();
        Cursor cursor = dbC.rawQuery("Select * from cliente where nomeCliente = ?", new String[]{nomeCliente, senhaCliente});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


@Override
    public void onUpgrade (SQLiteDatabase dbC, int i, int i1){
        dbC.execSQL("drop Table if exists cliente");

}

}


