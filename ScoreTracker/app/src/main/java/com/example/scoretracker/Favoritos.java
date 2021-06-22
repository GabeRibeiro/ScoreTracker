package com.example.scoretracker;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.classes.Desporto;
import com.example.scoretracker.classes.FavoritoItem;
import com.example.scoretracker.classes.Liga;
import com.example.scoretracker.defaults.DefaultClassifActivity;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdapterFavoritos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FavoritoItem> favs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        favs.add(new FavoritoItem(R.drawable.ic_sporting_clube_de_portugal, "Sporting CP", "Futebol"));
        favs.add(new FavoritoItem(R.drawable.ic_porto, "FC Porto", "Futebol"));
        favs.add(new FavoritoItem(R.drawable.ic_vitoria_sport_club_logo, "Vitória Sport Clube", "Futebol"));
        favs.add(new FavoritoItem(R.drawable.ic_bucks, "Milwaukee Bucks", "Basketball"));
        favs.add(new FavoritoItem(R.drawable.ic_rafanadal, "Rafael Nadal", "Tenis"));


    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new AdapterFavoritos.SimpleDividerItemDecoration(this));
        mAdapter = new AdapterFavoritos(favs, new AdapterFavoritos.OnClickListener() {
            @Override
            public void OnItemListener(int position) {
                FavoritoItem d = favs.get(position);
                if (d.getClube().equals("Sporting CP")) {
                    Intent intent = new Intent(Favoritos.this, EquipaSporting.class);
                    startActivity(intent);
                }
            }

            @Override
            public void OnFavListener(int position) {
                FavoritoItem d = favs.get(position);
                if(d.getE_Fav() == 1){
                    favs.remove(position);
                    mAdapter.notifyDataSetChanged();

                    }

            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

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
