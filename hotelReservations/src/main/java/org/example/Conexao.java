package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URLReservas = "jdbc:h2:file:./reservas";
    private static final String URLQuartos = "jdbc:h2:file:./quartos";
    private static final String USUARIO = "sa";
    private static final String SENHA = "";

    // Construtor privado para evitar instanciação
    private Conexao() {}

    public static Connection getConexaoQuartos() throws SQLException {
        return DriverManager.getConnection(URLQuartos, USUARIO, SENHA);
    }

    public static Connection getConexaoReservas() throws SQLException {
        return DriverManager.getConnection(URLReservas, USUARIO, SENHA);
    }
}
