package org.example;

public abstract class Equipamento {
    private double poder;
    private String nome;


    public Equipamento(String nome, double poder){
        this.poder = poder;
        this.nome = nome;
    }

    public double getPoder() {
        return poder;
    }
    public String getNome(){
        return nome;
    }
}
