package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorLoot {
    Arma espadaFogo = new Arma("Espada de Fogo", 20);
    Arma espadaRaio = new Arma("Espada de Raio", 20);
    Arma espadaNegra = new Arma("Espada de  ", 20);
    Arma espadaAcida = new Arma("Espada de Fogo", 20);
    Arma espadaCosmica = new Arma("Espada de Fogo", 20);

    List<Item> lista = new ArrayList<>();


    public Item sortearItem() {
        lista.add(espadaFogo);
        lista.add(espadaRaio);
        lista.add(espadaNegra);
        lista.add(espadaAcida);
        lista.add(espadaCosmica);

        Random rd = new Random();
        Item itemSorteado;
        int sorte = rd.nextInt(lista.size() + lista.size());

        if (sorte >= lista.size()) {
            itemSorteado = null;

        } else{
            itemSorteado = lista.remove(sorte);
        }

        return itemSorteado;


    }

}
