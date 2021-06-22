package com.example.scoretracker.classes;

import android.os.Parcelable;

import com.example.scoretracker.R;

public class Liga {
    private int pais_img;
    private String pais_txt;
    private String liga_txt;
    private int fav_img;
    private int e_Fav;

    public Liga(int i, String t1, String t2){
        pais_img = i;
        pais_txt = t1;
        liga_txt = t2;
        fav_img = R.drawable.ic_baseline_star_outline_24;
        e_Fav = 0;
    }

    public int getPais_img() {
        return pais_img;
    }

    public String getLiga_txt() {
        return liga_txt;
    }

    public String getPais_txt() {
        return pais_txt;
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
}
