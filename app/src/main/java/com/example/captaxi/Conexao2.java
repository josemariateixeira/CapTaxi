package com.example.captaxi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao2 extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao2 (Context context){
        super (context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dbM){
        dbM.execSQL("create table motorista (id integer primary key autoincrement, "+
                "nome varchar(50), cpf varchar (50), telefone varchar (50), email varchar (50), senha varchar (50))");

    }


    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1){
    }

}
