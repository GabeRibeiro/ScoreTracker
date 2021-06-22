package com.example.scoretracker.classes;

import java.util.Comparator;

public class Jogador_stats {
    private String nome;
    private int pais;
    private String clube;
    private int golos;
    private int assists;

    public Jogador_stats( int pais, String nome, String clube, int Golos, int Assists){
        this.pais = pais;
        this.nome = nome;
        this.clube = clube;
        this.golos = Golos;
        this.assists = Assists;
    }

    public static Comparator<Jogador_stats> Goals = new Comparator<Jogador_stats>() {
        @Override
        public int compare(Jogador_stats j1, Jogador_stats j2) {
            return j2.getGolos() - j1.getGolos();
        }
    };

    public static Comparator<Jogador_stats> Assists = new Comparator<Jogador_stats>() {
        @Override
        public int compare(Jogador_stats j1, Jogador_stats j2) {
            return j2.getAssists() - j1.getAssists();
        }
    };
    public String getNome() {
        return nome;
    }

    public int getPais() {
        return pais;
    }

    public int getAssists() {
        return assists;
    }

    public int getGolos() {
        return golos;
    }

    public String getClube() {
        return clube;
    }

}
