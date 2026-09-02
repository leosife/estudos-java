package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:h2:file:./banco_album";
    private static final String USUARIO = "sa";
    private static final String SENHA = "";

    // Construtor privado para evitar instanciação
    private Conexao() {}

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}