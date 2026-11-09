package org.example;


import java.util.Random;

public class Main {
    public static void main(String[] args) {
    Personagem p1 = new Personagem("Leo");
    Personagem p2 = new Personagem("Jay");
    Arma arma = new Arma("Katana",35);
    Armadura armadadura = new Armadura("tita", 25);
    Armadura armadadura2 = new Armadura("deuses", 55);
    Random rd = new Random();


    do{
        rd.nextInt(1,2);

    } while (p1.getVida() > 0 || p2.getVida() >0);
        p1.atacar(p2);
    p1.equipar(arma);
    p1.atacar(p2);
    p2.equipar(armadadura);
    p1.atacar(p2);
        p2.equipar(armadadura2);
        p1.atacar(p2);


    }
}