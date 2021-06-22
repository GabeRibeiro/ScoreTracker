package com.example.scoretracker.classes;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.scoretracker.R;

import java.util.Comparator;

public class Desporto {
    private int desporto_img;
    private String nome;
    private int fav_img;
    private int e_Fav;

    public Desporto(int img,String n) {
        desporto_img = img;
        nome = n;
        fav_img = R.drawable.ic_baseline_star_outline_24;
        e_Fav = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getDesporto_img(){
        return desporto_img;
    }

    public int getFav_img() {
        return fav_img;
    }

    public void setFav_img(int fav_img) {
            this.fav_img = fav_img;
    }

    public int getE_Fav() {
        return e_Fav;
    }

    public void setE_Fav(int e_Fav) {
        this.e_Fav = e_Fav;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Desporto: " + nome  ;
    }

}
