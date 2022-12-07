package com.example.captaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class List_motorista_Activity extends AppCompatActivity {

    private ListView listView;
    private MotoristaDAO dao;
    private List<Motorista> motoristas;
    private final List<Motorista> motoristasFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_motorista);

        listView = findViewById(R.id.lista_motorista);
        dao = new MotoristaDAO(this);
        motoristas = dao.obterTodos();
        motoristasFiltrados.addAll(motoristas);
        ArrayAdapter<Motorista> adaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, motoristasFiltrados);
        listView.setAdapter(adaptor);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraCliente(s);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(android.view.ContextMenu menu, android.view.View v, android.view.ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraCliente (String nome){
        motoristasFiltrados.clear();
        for (Motorista m: motoristas){
            if (m.getNome() .toLowerCase() .contains(nome.toLowerCase())){
                motoristasFiltrados.add(m);
            }
        }
        listView.invalidateViews();
    }
    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Motorista motoristaExcluir = motoristasFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir realmente?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        motoristasFiltrados.remove(motoristaExcluir);
                        motoristas.remove(motoristaExcluir);
                        dao.excluir(motoristaExcluir);
                        listView.invalidateViews();

                    }
                }).create();
        dialog.show();
    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroMotoristaActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Motorista motoristaAtualizar = motoristasFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroMotoristaActivity.class);
        it.putExtra("motorista", motoristaAtualizar);
        startActivity(it);

    }

    public void onResume(){
        super.onResume();
        motoristas = dao.obterTodos();
        motoristasFiltrados.clear();
        motoristasFiltrados.addAll(motoristas);
        listView.invalidateViews();
    }
}