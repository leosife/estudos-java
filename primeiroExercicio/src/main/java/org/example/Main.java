package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        String url = "jdbc:h2:file:./banco_guilda";


        try(Connection conexao = DriverManager.getConnection(url,"sa","");
            Statement stt = conexao.createStatement() )
        {
            System.out.println("✅ Banco de dados criado com sucesso! ");
            stt.execute("CREATE TABLE IF NOT EXISTS missoes (nome VARCHAR(100), id int AUTO_INCREMENT PRIMARY KEY)");

        }catch (Exception e){
            System.out.println("Erro no banco de dados - "+e.getMessage());
        }

        JFrame myFrame = new JFrame("Bando dados guilda");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);


        myFrame.setLayout(new FlowLayout());



        JLabel rotulo = new JLabel("Nome da missão: ");
        JTextField caixaNomeM = new JTextField(20);
        JButton botao = new JButton("Salvar missão");
        JLabel resposta = new JLabel("");



        JButton botaoLista = new JButton("Carregar missoes");
        JTextArea areaTexto= new JTextArea(10,35);
        areaTexto.setEditable(false);
        JScrollPane painelRolagem = new JScrollPane(areaTexto); // Adiciona barra de rolagem

        JButton botaoExcluir = new JButton("Deletar");
        JTextField caixaExcluir = new JTextField(5);
        JLabel labelExcluir = new JLabel("Digite o ID para excluir");

        myFrame.add(rotulo);
        myFrame.add(caixaNomeM);
        myFrame.add(botao);

        myFrame.add(painelRolagem);
        myFrame.add(botaoLista);

        myFrame.add(labelExcluir);
        myFrame.add(caixaExcluir);
        myFrame.add(botaoExcluir);

        myFrame.add(resposta);



        GuildaDAO banco = new GuildaDAO();
        botao.addActionListener(acao ->
        {
            String textoDigitado = caixaNomeM.getText();
            int id = 0;

            boolean deuCerto = banco.salvarMissao(textoDigitado, id);

            if(deuCerto){
                resposta.setText("✅ Missão salva com sucesso!");
                caixaNomeM.setText("");
            } else {
                resposta.setText("❌ Erro ao salvar!");

            }




        });

        botaoLista.addActionListener(acao -> {
            areaTexto.setText("");
            List<Missao> missoes = banco.lerBase();

            if(missoes.isEmpty()){
                areaTexto.setText(("Nenhuma missão cadastrada"));
            } else {
                missoes.forEach(missao -> areaTexto.append(missao.id() + " - " + missao.nomeMissao() + "\n"));
            }
        });

        botaoExcluir.addActionListener(acao -> {
            String textoId = caixaExcluir.getText().trim();
            if (textoId.isEmpty()){
                resposta.setText("⚠️ Digite um ID para excluir!");
                return;

            }

            try {
                caixaExcluir.setText("");
                int idMissao = Integer.parseInt(textoId);
                boolean deletado = banco.excluirMissao(idMissao);

                if (deletado) {
                    resposta.setText("Missão " + idMissao + " deletada com sucesso!!");
                } else {
                    resposta.setText("Nenhum registo encontrado");
                }
            }catch (NumberFormatException e){
                resposta.setText("⚠️ ID precisa ser um número inteiro válido!");
            }







        });

        myFrame.setVisible(true);



    }
}