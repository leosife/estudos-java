package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Personagem p1 = new Personagem("Leo");
        Personagem p2 = new Personagem("Jay");
        Arma arma = new Arma("Katana", 35);
        Arma arma2 = new Arma("Espada Negra", 31);
        Armadura armadura = new Armadura("tita", 25);
        Armadura armadura2 = new Armadura("deuses", 30);
        List<Item> lista = new ArrayList<>();

        lista.add(arma);
        lista.add(arma2);
        lista.add(armadura);
        lista.add(armadura2);


        Random rd = new Random();
        int turno = rd.nextInt(1, 3);
        int escolha;

        Scanner sc = new Scanner(System.in);

        Personagem atacante = p1;
        Personagem defensor = p2;



//        do {
//
//            switch (turno) {
//                case 1:
//                    System.out.println("Player 1 é o seu turno");
//                    atacante = p1;
//                    defensor = p2;
//
//                    break;
//                case 2:
//                    System.out.println("Player 2 é o seu turno");
//                    atacante = p2;
//                    defensor = p1;
//                    break;
//            }
//
//            System.out.println("Escolha:\n" +
//                    "1 - Atacar\n" +
//                    "2 - Buscar Equipamento\n");
//            escolha = sc.nextInt();
//
//            switch (escolha){
//                case 1:
//                    atacante.atacar(defensor);
//                    break;
//                case 2:
//                    int sorte = rd.nextInt(lista.size()+5);
//                    if (sorte >= lista.size()){
//                        System.out.println("Você vasculhou a arena, mas não encontrou nenhum item...");
//                        break;
//                    }
//                    // 1. Pegamos o item genérico da lista
//                    Equipamento itemSorteado = lista.remove(sorte);
//
//                    // 2. Perguntamos ao Java: esse item é, na verdade, uma Arma?
//                    if (itemSorteado instanceof Arma) {
//                        Arma armaEncontrada = (Arma) itemSorteado; // Isso é o Casting!
//                        atacante.equipar(armaEncontrada);
//                    }
//                    // 3. Ou será que ele é uma Armadura?
//                    else if (itemSorteado instanceof Armadura) {
//                        Armadura armaduraEncontrada = (Armadura) itemSorteado; // Casting!
//                        atacante.equipar(armaduraEncontrada);
//                    }
//
//                    break;
//            }
//
//
//
//            if (turno==1){
//                turno = 2;
//            } else {
//                turno=1;
//            }
//
//            if (p1.getVida() <= 0){
//                System.out.println(p2.getName() + " vence a batalha");
//            } else if (p2.getVida() <= 0) {
//                System.out.println(p1.getName() + " vence a batalha");
//            }
//
//
//        } while (p1.getVida() > 0 && p2.getVida() > 0);

    TelaArena tela = new TelaArena();
    tela.iniciarTela(p1,p2);

    }
}