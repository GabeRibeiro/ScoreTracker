package com.example.scoretracker.classes;

public class EquipaClass {
    private int pos;
    private int img;
    private String nome;
    private int pontos;
    private int m_p;
    private int m_w;
    private int m_d;
    private int m_l;
    private String sport;

    public EquipaClass(int pos, int img, String nome, int pontos, int m_p, int m_w, int m_d, int m_l, String sport){
        this.pos = pos;
        this.img = img;
        this.nome = nome;
        this.pontos = pontos;
        this.m_p = m_p;
        this.m_w = m_w;
        this.m_d = m_d;
        this.m_l = m_l;
        this.sport = sport;

    }

    public int getPos() {
        return pos;
    }

    public int getImg() {
        return img;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public int getM_p() {
        return m_p;
    }

    public int getM_w() {
        return m_w;
    }

    public int getM_d() {
        return m_d;
    }

    public int getM_l() {
        return m_l;
    }

    public String getSport() {
        return sport;
    }
}
