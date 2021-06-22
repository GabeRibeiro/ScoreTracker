package com.example.scoretracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.scoretracker.Fragments.Estatisticas;
import com.example.scoretracker.Fragments.JogadoresSporting;
import com.example.scoretracker.Fragments.ResultadosSporting;
import com.example.scoretracker.Fragments.Resumo;

public class EquipaSporting extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int is_fav = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sporting);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titulo = (TextView) findViewById(R.id.toolbar_title);
        titulo.setText("Sporting");
        createButtons();
        createFavorito();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createFavorito() {
        ImageView fav = findViewById(R.id.favorito);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_fav == 0){
                    fav.setImageResource(R.drawable.ic_favorito);
                    is_fav = 1;
                    Toast.makeText(getApplicationContext(),
                            "Adicionado aos favoritos",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    fav.setImageResource(R.drawable.ic_nonfav);
                    Toast.makeText(getApplicationContext(),
                            "Removido dos favoritos",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
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

    private void createButtons() {
        Button equipa = findViewById(R.id.equipa);
        equipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new JogadoresSporting();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout1, selectedFragment);
                fragmentTransaction.commit();

            }
        });
        //res.performClick();
        Button resultados = findViewById(R.id.resultados);
        resultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new ResultadosSporting();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout1, selectedFragment);
                fragmentTransaction.commit();
            }
        });
    }
}
