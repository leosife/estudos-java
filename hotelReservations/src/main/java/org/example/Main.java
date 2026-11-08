package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // Criamos a instância da sua classe DAO
        QuartosDAO dao = new QuartosDAO();

        // Chamamos o método para criar a tabela no banco
        dao.inicializarQuarto();
        Quarto primeiro = new Quarto(1,"Suite",new BigDecimal("199.99"),Status.LIBRE);
        dao.cadastrarQuarto(primeiro);
    }
}