package com.example.scoretracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.classes.EquipaClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class LigaNos_Classific extends AppCompatActivity implements AdapterClassific.OnClickListener {
    private RecyclerView mRecyclerView;
    private AdapterClassific mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<EquipaClass> equipas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classif_futebol);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titulo = (TextView) findViewById(R.id.toolbar_title);
        titulo.setText("Liga Nos");
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.classificacao);
        fillList();
        setUpRecyclerView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            NavUtils.navigateUpTo(this, intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillList() {
        equipas.add(new EquipaClass(1, R.drawable.ic_sporting_clube_de_portugal, "Sporting CP", 85, 34, 26, 7, 1,"fut"));
        equipas.add(new EquipaClass(2, R.drawable.ic_porto, "FC Porto", 80, 34, 24, 8, 2,"fut"));
        equipas.add(new EquipaClass(3, R.drawable.ic_benfica, "SL Benfica", 76, 34, 23, 7, 4,"fut"));
        equipas.add(new EquipaClass(4, R.drawable.ic_sporting_clube_de_braga, "SC Braga", 64, 34, 19, 7, 8,"fut"));
        equipas.add(new EquipaClass(5, R.drawable.ic_pacos_de_ferreira_logo_1, "P. Ferreira", 53, 34, 15, 8, 11,"fut"));
        equipas.add(new EquipaClass(6, R.drawable.ic_santa_clara_fc_logo, "Santa Clara", 46, 34, 13, 7, 14,"fut"));
        equipas.add(new EquipaClass(7, R.drawable.ic_vitoria_sport_club_logo, "Vitória SC", 43, 34, 12, 7, 15,"fut"));
        equipas.add(new EquipaClass(8, R.drawable.ic_moreirense_fc_logo, "Moreirense", 43, 34, 10, 13, 11,"fut"));
        equipas.add(new EquipaClass(9, R.drawable.ic_fc_famalicao, "Famalicão", 40, 34, 10, 10, 14,"fut"));
        equipas.add(new EquipaClass(10, R.drawable.ic_bsad, "B-SAD", 40, 34, 9, 13, 12,"fut"));
        equipas.add(new EquipaClass(11, R.drawable.ic_gil_vicente_fc_logo, "Gil Vicente", 39, 34, 11, 6, 17,"fut"));
        equipas.add(new EquipaClass(12, R.drawable.ic_cd_tondela_logo, "Tondela", 36, 34, 10, 6, 18,"fut"));
        equipas.add(new EquipaClass(13, R.drawable.ic_boavista, "Boavista", 36, 34, 8, 12, 14,"fut"));
        equipas.add(new EquipaClass(14, R.drawable.ic_portimonense, "Portimonense", 35, 34, 9, 8, 17,"fut"));
        equipas.add(new EquipaClass(15, R.drawable.ic_cs_maritimo_logo, "Marítimo", 35, 34, 10, 5, 19,"fut"));
        equipas.add(new EquipaClass(16, R.drawable.ic_rio_ave_fc_logo_2, "Rio Ave", 34, 34, 7, 13, 14,"fut"));
        equipas.add(new EquipaClass(17, R.drawable.ic__18499, "Farense", 31, 34, 7, 10, 17,"fut"));
        equipas.add(new EquipaClass(18, R.drawable.ic_nacional, "Nacional", 25, 34, 6, 7, 21,"fut"));

    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterClassific(equipas, this::OnClickListener);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new AdapterClassific.SimpleDividerItemDecoration(this));


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.jogos:
                            Intent intent = new Intent(LigaNos_Classific.this, LigaNos_Jogos.class);
                            startActivity(intent);
                            return true;
                        case R.id.marcadores:
                            Intent intent2 = new Intent(LigaNos_Classific.this, LigaNos_Marcadores.class);
                            startActivity(intent2);
                    }
                    return true;
                }
            };



    @Override
    public void OnClickListener (int position){
        EquipaClass d = equipas.get(position);
        if(d.getNome().equals("Sporting CP")) {
            Intent intent = new Intent(this, EquipaSporting.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchitem = menu.findItem(R.id.action_search);
        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchitem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
