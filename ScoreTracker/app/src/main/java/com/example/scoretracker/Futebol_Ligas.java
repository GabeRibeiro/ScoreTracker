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
import com.example.scoretracker.classes.Liga;
import com.example.scoretracker.defaults.DefaultClassifActivity;

import java.util.ArrayList;

public class Futebol_Ligas extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdapterRVLigas_Futebol mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Liga> ligas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        ligas.add(new Liga(R.drawable.ger, "Alemanha", "Bundesliga"));
        ligas.add(new Liga(R.drawable.ger, "Alemanha", "2. Bundesliga"));
        ligas.add(new Liga(R.drawable.esp, "Espanha", "La Liga Santander"));
        ligas.add(new Liga(R.drawable.esp, "Espanha", "La Liga SmartBank (2)"));
        ligas.add(new Liga(R.drawable.fr, "França", "Ligue 1 Uber Eats"));
        ligas.add(new Liga(R.drawable.fr, "França", "Ligue 2 BKT"));
        ligas.add(new Liga(R.drawable.eng, "Inglaterra", "Premier League"));
        ligas.add(new Liga(R.drawable.eng, "Inglaterra", "EFL Championship"));
        ligas.add(new Liga(R.drawable.it, "Itália", "Serie A"));
        ligas.add(new Liga(R.drawable.it, "Itália", "Serie B"));
        ligas.add(new Liga(R.drawable.pt, "Portugal", "Liga Nos"));
        ligas.add(new Liga(R.drawable.pt, "Portugal", "Liga Portugal 2 SabSeg"));

    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterRVLigas_Futebol(ligas, new AdapterRVLigas_Futebol.OnClickListener() {
            @Override
            public void OnItemListener(int position) {
                Liga d = ligas.get(position);
                if (d.getLiga_txt().equals("Liga Nos")) {
                    Intent intent = new Intent(Futebol_Ligas.this, LigaNos_Classific.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Futebol_Ligas.this, DefaultClassifActivity.class);
                    startActivity(intent);
                }
            }


            @Override
            public void OnFavListener(int position) {
                Liga d = ligas.get(position);
                if(d.getE_Fav() == 0){
                    ligas.remove(position);
                    ligas.add(0,d);
                    d.setFav_img(R.drawable.ic_fav);
                    d.setE_Fav(1);
                    mAdapter.notifyDataSetChanged();

                }
                else if(d.getE_Fav() == 1){
                    ligas.remove(position);
                    ligas.add(d);
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
