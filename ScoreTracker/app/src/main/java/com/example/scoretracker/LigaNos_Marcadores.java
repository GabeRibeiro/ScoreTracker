package com.example.scoretracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scoretracker.classes.EquipaClass;
import com.example.scoretracker.classes.Jogador_stats;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class LigaNos_Marcadores extends AppCompatActivity implements AdapterMarcadores.OnClickListener {
    private RecyclerView mRecyclerView;
    private AdapterMarcadores mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Jogador_stats> jogadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcadores_futebol);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        TextView titulo = (TextView) findViewById(R.id.toolbar_title);
        titulo.setText("Liga Nos");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.marcadores);
        fillList();
        setUpRecyclerView();
        createButton1();
        createButton2();

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

    private void createButton1() {
        Button Goals = findViewById(R.id.goals_button);
        Goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(jogadores, Jogador_stats.Goals);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
    private void createButton2() {
        Button Assists = findViewById(R.id.assists_button);
        Assists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(jogadores, Jogador_stats.Assists);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
    private void fillList() {
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Pedro Gonçalves", "SCP",23, 3));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Haris Seferovic", "SLB",22, 7));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Mehdi Taremi", "FCP",16, 11));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Mario Gonzalez", "TON",15, 2));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Carlos Carvalho", "SAN",14, 4));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Sérgio Oliveira", "FCP",13, 5));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Beto", "POR",11, 2));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Mateo Cassierra", "BEL",10, 1));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Ryan Gauld", "FAR",9, 7));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Ricardo Horta", "BRA",9, 3));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Joel Tagueu", "MAR",9, 2));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Douglas Tanque", "PFE",9, 2));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"Samuel Lino", "GIL",9, 0));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"R.Pinho", "MAR",9, 0));
        jogadores.add(new Jogador_stats(R.drawable.default_badge,"D.Nuñez", "SLB",6, 10));

    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.marcadores_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterMarcadores(jogadores, this::OnClickListener);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new AdapterMarcadores.SimpleDividerItemDecoration(this));


    }

    @Override
    public void OnClickListener (int position){

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.classificacao:
                            Intent intent3 = new Intent(LigaNos_Marcadores.this, LigaNos_Classific.class);
                            startActivity(intent3);
                            return true;
                        case R.id.jogos:
                            Intent intent4 = new Intent(LigaNos_Marcadores.this, LigaNos_Jogos.class);
                            startActivity(intent4);
                    }
                    return true;
                }
            };


}