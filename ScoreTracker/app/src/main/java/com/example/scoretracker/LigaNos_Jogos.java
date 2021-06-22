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
import android.widget.TextView;

import com.example.scoretracker.classes.EquipaClass;
import com.example.scoretracker.classes.Jogo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class LigaNos_Jogos extends AppCompatActivity implements AdapterJogos.OnClickListener{
    private RecyclerView mRecyclerView;
    private AdapterJogos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Jogo> jogos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos_futebol);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titulo = (TextView) findViewById(R.id.toolbar_title);
        titulo.setText("Liga Nos");
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.jogos);
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
    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.listajogos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterJogos(jogos, this::OnClickListener);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new AdapterJogos.SimpleDividerItemDecoration(this));
    }

    private void fillList() {
        jogos.add(new Jogo("18/05", "Tondela", 2,"Paços Ferreira",3));
        jogos.add(new Jogo("19/05", "Sporting", 5,"Marítimo",1));
        jogos.add(new Jogo("19/05", "Moreirense", 3,"Famalicão",0));
        jogos.add(new Jogo("19/05", "Vitória SC", 1,"Benfica",3));
        jogos.add(new Jogo("19/05", "Gil Vicente", 1,"Boavista",2));
        jogos.add(new Jogo("19/05", "Portimonense", 0,"Braga",0));
        jogos.add(new Jogo("19/05", "Santa Clara", 4,"Farense",0));
        jogos.add(new Jogo("19/05", "Nacional", 1,"Rio Ave",2));
        jogos.add(new Jogo("19/05", "FC Porto", 4,"Belenenses SAD",0));


    }

    @Override
    public void OnClickListener (int position){
        Jogo d = jogos.get(position);
        if(d.getTeam1().equals("Tondela")) {
            Intent intent = new Intent(this, Game1.class);
            startActivity(intent);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.classificacao:
                            Intent intent = new Intent(LigaNos_Jogos.this, LigaNos_Classific.class);
                            startActivity(intent);
                            return true;
                        case R.id.marcadores:
                            Intent intent2 = new Intent(LigaNos_Jogos.this, LigaNos_Marcadores.class);
                            startActivity(intent2);
                    }
                    return true;
                }
            };
}