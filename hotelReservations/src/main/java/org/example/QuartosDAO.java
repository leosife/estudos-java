package org.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuartosDAO {

    public void inicializarQuarto(){
        try(Connection con = Conexao.getConexaoQuartos();
        Statement stt = con.createStatement()){

            stt.execute("CREATE TABLE IF NOT EXISTS quartos(numero INT PRIMARY KEY,tipo VARCHAR(50), valor DECIMAL, status VARCHAR(50))");
            System.out.println("Tabela criada com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao criar o banco de dados: "+ e.getMessage());
        }
    }


    public void cadastrarQuarto(Quarto room){
        int numero = room.getNumeroQuarto();
        String tipo = room.getTipo();
        BigDecimal valor = room.getValor();
        String status = room.getStatus().name();
        String sql = "INSERT INTO quartos (numero, tipo, valor, status) VALUES(?,?,?,?)";
        try(Connection con = Conexao.getConexaoQuartos();
            PreparedStatement ppt = con.prepareStatement(sql)){
            ppt.setInt(1, numero);
            ppt.setString(2,tipo);
            ppt.setBigDecimal(3, valor);
            ppt.setString(4, status);
            ppt.executeUpdate();

            System.out.println("Quarto adicionado com sucesso");


        } catch (Exception e) {
            System.out.println("Erro ao inserir quarto: "+ e.getMessage());
        }

    }

    public List<Quarto> buscarQuartos(){
        List<Quarto> listaQuartos = new ArrayList<>();
        String sql = "SELECT * FROM quartos";

        try (Connection con = Conexao.getConexaoQuartos();
        PreparedStatement ppt = con.prepareStatement(sql);
             ResultSet rs = ppt.executeQuery()){

            while (rs.next()){
                int numero = rs.getInt("numero");
                String tipo = rs.getString("tipo");
                BigDecimal valor = new BigDecimal("valor");
                Status  status = Status.valueOf(rs.getString("status").toUpperCase());

                Quarto quarto = new Quarto(numero,tipo,valor,status);
                listaQuartos.add(quarto);

            }


        } catch (Exception e) {
            System.out.println("Erro: " +e.getMessage());
        }

        return listaQuartos;
    }
}

