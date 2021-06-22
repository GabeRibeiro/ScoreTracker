package com.example.scoretracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;


import com.example.scoretracker.classes.Desporto;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private AdapterRVDesportos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Desporto> desportos = new ArrayList<>();
    private static Bundle mBundleRecyclerViewState;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fillList();
        setUpRecyclerView();
        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.main3);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_image2vector__2___3_);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }



    @Override
    protected void onPause()
    {

        super.onPause();

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable("key2", listState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable("key2");
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }




    private void fillList() {
        desportos.add(new Desporto(R.drawable.hb, "Andebol"));
        desportos.add(new Desporto(R.drawable.basket, "Basketball"));
        desportos.add(new Desporto(R.drawable.fut, "Futebol"));
        desportos.add(new Desporto(R.drawable.fut, "Futsal"));
        desportos.add(new Desporto(R.drawable.ic_golf, "Golf"));
        desportos.add(new Desporto(R.drawable.hq, "Hóquei"));
        desportos.add(new Desporto(R.drawable.pa, "Polo Aquático"));
        desportos.add(new Desporto(R.drawable.rb, "Rugby"));
        desportos.add(new Desporto(R.drawable.tenis, "Ténis"));
        desportos.add(new Desporto(R.drawable.vb, "Voleibol"));



    }

    private void setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterRVDesportos(desportos, new AdapterRVDesportos.OnClickListener() {
            @Override
            public void OnItemListener(int position) {
                Desporto d = desportos.get(position);
                if (d.getNome().equals("Futebol")) {
                    Intent intent = new Intent(MainActivity.this, Futebol_Ligas.class);
                    startActivity(intent);
                }
            }


            @Override
            public void OnFavListener(int position) {
                Desporto d = desportos.get(position);
                if(d.getE_Fav() == 0){
                    desportos.remove(position);
                    desportos.add(0,d);
                    d.setFav_img(R.drawable.ic_fav);
                    d.setE_Fav(1);
                    mAdapter.notifyDataSetChanged();

                }
                else if(d.getE_Fav() == 1){
                    desportos.remove(position);
                    desportos.add(d);
                    d.setFav_img(R.drawable.ic_baseline_star_outline_24);
                    d.setE_Fav(0);
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
        inflater.inflate(R.menu.search_menu,menu);

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.lista_fav:
                Intent intent = new Intent(MainActivity.this, Favoritos.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}