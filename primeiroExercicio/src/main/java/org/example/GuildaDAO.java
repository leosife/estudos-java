package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuildaDAO {
    String url = "jdbc:h2:file:./banco_guilda";
    public boolean salvarMissao(String nomeDaMissao, int id){

        String sql = "INSERT INTO missoes (nome) Values (?)";


        try(Connection con = DriverManager.getConnection(url,"sa","");
            PreparedStatement pst = con.prepareStatement(sql) ) {

            pst.setString(1, nomeDaMissao);
            pst.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Erro na cozinha (banco): " + e.getMessage());
            return false;
        }


    }

    public List<Missao> lerBase(){
        List<Missao> missoes = new ArrayList<>();
        String busca = "SELECT * FROM missoes";
        try(Connection con = DriverManager.getConnection(url,"sa","");
            Statement megafone = con.createStatement();
            ResultSet resultado = megafone.executeQuery(busca) ){
            while(resultado.next()){
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                missoes.add(new Missao(id,nome));


            }



        } catch (Exception e) {
            System.out.println("Erro ao ler a base de dados "+ e.getMessage());
        }


        return missoes;
    }

    public boolean excluirMissao(int id){
        String sql = "DELETE FROM missoes Where id = ?";
        try(Connection con = DriverManager.getConnection(url,"sa","");
            PreparedStatement pst = con.prepareStatement(sql)
        ){

            pst.setString(1, String.valueOf(id));
            pst.execute();

            return true;


        } catch (Exception e) {
            System.out.println("Erro ao deletar arquivo - "+ e.getMessage());
            return false;
        }


    }



}


