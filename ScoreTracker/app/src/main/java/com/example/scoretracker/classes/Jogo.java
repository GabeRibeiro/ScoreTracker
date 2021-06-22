package com.example.scoretracker.classes;

public class Jogo {
    private String date;
    private String team1;
    private int goal1;
    private String team2;
    private int goal2;

    public Jogo(String date, String team1, int goal1, String team2, int goal2){
        this.date = date;
        this.team1 = team1;
        this.goal1 = goal1;
        this.team2 = team2;
        this.goal2 = goal2;
    }

    public int getGoal1() {
        return goal1;
    }

    public int getGoal2() {
        return goal2;
    }

    public String getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }
}
