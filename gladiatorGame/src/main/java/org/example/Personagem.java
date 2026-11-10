package org.example;

import java.util.Random;

public class Personagem {
    private double vida = 100;
    private double escudo = 0;
    private double ataque = 5;
    private double poderCura = 3;

    private double defesa;
    private String name;

    private Arma armaEquipada;
    private Armadura armaduraEquipada;
    Random rd = new Random();

    public Personagem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getEscudo() {
        return escudo;
    }

    public void setEscudo(double escudo) {
        this.escudo = escudo;
    }

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public double getDefesa() {
        return defesa;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    public double calcularDano() {

        if (armaEquipada == null) {
            return ataque;
        }
        return ataque + armaEquipada.getPoder();
    }

    public double calcularDefesa() {
        if (armaduraEquipada == null) {
            return defesa;
        }
        return defesa + armaduraEquipada.getPoder();
    }

    public int receberDano(double dano) {
        dano -= calcularDefesa();
        if (dano <= 0) {
            System.out.println("Ataque não causou dano");
            return 0;

        }
        double escudoAnterior = this.escudo;
        this.escudo -= dano;


        if (this.escudo < 0) {
            if (escudoAnterior > 0){
                System.out.println("Escudo absorveu " + escudoAnterior + " e quebrou!");
            }
            dano = -this.escudo;

            this.vida -= dano;
            this.escudo = 0;
            System.out.println("Ataque causou " + dano + " de dano, vida de " + this.name + " baixou a " + this.vida);
        } else {
            System.out.println("O escudo absorveu o impacto! Escudo baixou de : "+escudoAnterior +" para "+ this.escudo);
        }
        return (int) dano;

    }


    public int atacar(Personagem atacado) {
        return atacado.receberDano(calcularDano());
    }


    public void equipar(Armadura armadura) {
        this.armaduraEquipada = armadura;
        System.out.println(this.getName() + " vestiu a armadura " + armadura.getNome());


    }

    public void equipar(Arma arma) {
        this.armaEquipada = arma;
        System.out.println(this.getName() + " equipou a arma " + arma.getNome());


    }

    public int curar(){
        double vidaAntiga = getVida();
        double novaVida = getVida() + poderCura;
        if (novaVida > 100){
            setVida(100);
        } else{
            setVida(novaVida);
        }
        int curaReal = (int) (vidaAntiga-novaVida);

        return curaReal;
    }
}
