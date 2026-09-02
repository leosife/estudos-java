package org.example;

import java.util.List;

public class AlbumService {


    public List<Figurinha> buscarRepetidas(List<Figurinha> todas) {
        return todas.stream()
                .filter(figurinha -> figurinha.quantidade() > 1)
                .toList();


    }

    public  List<Figurinha> buscarFaltantes(List<Figurinha> todas){
        return todas.stream()
                .filter(figurinha -> figurinha.quantidade() == 0)
                .toList();

    }

    public  List<Figurinha> buscarPorSelecao(List<Figurinha> todas, Selecoes selecao){
        return todas.stream()
                .filter(figurinha -> figurinha.selecao() == selecao)
                .toList();
    }

    public double calcularProgresso(List<Figurinha> todas, int totalFigurinhaAlbum){
        if(todas.isEmpty() || totalFigurinhaAlbum == 0){
            return 0.0;
        }
        long adquiridas = todas.stream()
                .filter( figurinha -> figurinha.quantidade() > 0)
                .count();

        return ((double) adquiridas / totalFigurinhaAlbum) *100;
    }

}
