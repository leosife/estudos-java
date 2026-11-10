package org.example;

public abstract class Item {
    private double poder;
    private String nome;


    public Item(String nome, double poder){
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
