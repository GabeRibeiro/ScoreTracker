package com.example.scoretracker.classes;

import com.example.scoretracker.R;

public class FavoritoItem {
        private int club_img;
        private String clube;
        private String desporto;
        private int fav_img;
        private int e_Fav;

    public FavoritoItem(int i, String t1, String t2){
        club_img = i;
        clube = t1;
        desporto = t2;
        fav_img = R.drawable.ic_fav;
        e_Fav = 1;
    }

    public String getClube() {
        return clube;
    }

    public int getClub_img () {
        return club_img;
    }

    public String getDesporto () {
        return desporto;
    }

    public int getFav_img () {
        return fav_img;
    }

    public void setFav_img ( int fav_img){
        this.fav_img = fav_img;
    }

    public int getE_Fav () {
        return e_Fav;
    }

    public void setE_Fav ( int e_Fav){
        this.e_Fav = e_Fav;
    }

}

