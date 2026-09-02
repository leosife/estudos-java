package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FigurinhaDAO {


    public void inicializarBanco() {
        try (Connection con = Conexao.getConexao();
             Statement stt = con.createStatement()) {
            System.out.println("Banco de dados criado com sucesso!");
            stt.execute("CREATE TABLE IF NOT EXISTS figurinhas(numero INT PRIMARY KEY, selecao VARCHAR(50), nome VARCHAR(100), quantidade INT)");


        } catch (Exception e) {
            System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
        }

    }

    public boolean salvar(Figurinha figurinha) {
        String sql = "INSERT INTO figurinhas (numero, selecao, nome, quantidade) VALUEs (?,?,?,?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ppt = con.prepareStatement(sql);) {

            ppt.setInt(1, figurinha.numero());
            ppt.setString(2, figurinha.selecao().name());
            ppt.setString(3, figurinha.nome());
            ppt.setInt(4, figurinha.quantidade());

            ppt.executeUpdate();
            System.out.println("Figurinha adicionada com sucesso");

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir figurinha: " + e);
            return false;
        }


    }

    public List<Figurinha> listarTodas() {
        List<Figurinha> listaFigurinha = new ArrayList<>();
        String busca = "SELECT * FROM figurinhas";
        try (Connection con = Conexao.getConexao();
             Statement stt = con.createStatement();
             ResultSet resultado = stt.executeQuery(busca)) {
            while (resultado.next()) {
                Figurinha fig = new Figurinha(
                        resultado.getInt("numero"),
                        Selecoes.daString(resultado.getString("selecao")),
                        resultado.getString("nome"),
                        resultado.getInt("quantidade"));
                listaFigurinha.add(fig);
            }
            System.out.println("lista");


        } catch (Exception e) {
            System.out.println("Erro ao lista figurinhas " + e.getMessage());
        }


        return listaFigurinha;
    }

    public void excluir(int numero) {
        String sql = "DELETE FROM figurinhas WHERE numero = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stt = con.prepareStatement(sql)) {
            stt.setInt(1, numero);
            stt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e);
        }
    }

    public boolean atualizar(Figurinha figurinha){
        String sql = "UPDATE figurinhas set selecao = ?, nome = ? , quantidade = ? WHERE numero = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stt = con.prepareStatement(sql)) {

            stt.setString(1, figurinha.selecao().name());
            stt.setString(2, figurinha.nome() );
            stt.setInt(3, figurinha.quantidade() );
            stt.setInt(4, figurinha.numero());
            stt.executeUpdate();

            return true;

        }catch (SQLException e){
            System.out.println("Erro ao atualizar: "+ e.getMessage());
            return false;

        }

    }
    public boolean seExiste(int numero) {
        String sql = "SELECT EXISTS(SELECT 1 FROM figurinhas WHERE numero = ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement stt = con.prepareStatement(sql)) {

            stt.setInt(1, numero);

            try (ResultSet rs = stt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1); // Retorna true se encontrou, false se não
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar figurinha: " + e.getMessage());
        }

        return false;
    }

}
