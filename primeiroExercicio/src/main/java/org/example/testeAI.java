package org.example;

import com.formdev.flatlaf.FlatDarkLaf; // Importação do tema escuro do FlatLaf
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class testeAI {
    public static void main(String[] args) {

        // ATIVANDO O FLATLAF DARK AQUI (Deve ser a primeira coisa antes de criar janelas)
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar o FlatLaf");
        }

        // Configuração do Banco de Dados
        String url = "jdbc:h2:file:./banco_guilda";
        try (Connection conexao = DriverManager.getConnection(url, "sa", "");
             Statement stt = conexao.createStatement()) {
            System.out.println("✅ Banco de dados criado com sucesso! ");
            stt.execute("CREATE TABLE IF NOT EXISTS missoes (nome VARCHAR(100), id int AUTO_INCREMENT PRIMARY KEY)");
        } catch (Exception e) {
            System.out.println("Erro no banco de dados - " + e.getMessage());
        }

        // Janela Principal
        JFrame myFrame = new JFrame("Gerenciador de Missões da Guilda");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(550, 600);
        myFrame.setLayout(new BorderLayout(15, 15));
        myFrame.setLocationRelativeTo(null);

        // Painel Principal com margens limpas
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- 1. PAINEL DE CADASTRO ---
        JPanel painelCadastro = new JPanel(new BorderLayout(10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder(" Nova Missão "));

        JLabel rotulo = new JLabel("Nome da missão:");
        JTextField caixaNomeM = new JTextField(20);
        JButton botao = new JButton("Salvar missão");

        painelCadastro.add(rotulo, BorderLayout.NORTH);
        painelCadastro.add(caixaNomeM, BorderLayout.CENTER);
        painelCadastro.add(botao, BorderLayout.SOUTH);

        painelPrincipal.add(painelCadastro);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // --- 2. PAINEL MÉDIO (LISTAGEM) ---
        JPanel painelMedio = new JPanel(new BorderLayout(10, 10));
        painelMedio.setBorder(BorderFactory.createTitledBorder(" Buscar Missão "));

        JButton botaoLista = new JButton("Carregar missões");
        JTextArea areaTexto = new JTextArea(8, 40);
        areaTexto.setEditable(false);
        JScrollPane painelRolagem = new JScrollPane(areaTexto);

        painelMedio.add(botaoLista, BorderLayout.NORTH);
        painelMedio.add(painelRolagem, BorderLayout.CENTER);

        painelPrincipal.add(painelMedio);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // --- 3. PAINEL INFERIOR (EXCLUSÃO) ---
        JPanel painelBajo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelBajo.setBorder(BorderFactory.createTitledBorder(" Deletar Missão "));

        JLabel labelExcluir = new JLabel("ID:");
        JTextField caixaExcluir = new JTextField(5);
        JButton botaoExcluir = new JButton("Deletar");
        JLabel resposta = new JLabel("");

        painelBajo.add(labelExcluir);
        painelBajo.add(caixaExcluir);
        painelBajo.add(botaoExcluir);
        painelBajo.add(resposta);

        painelPrincipal.add(painelBajo);

        myFrame.add(painelPrincipal, BorderLayout.CENTER);

        // --- AÇÕES DOS BOTÕES ---
        GuildaDAO banco = new GuildaDAO();

        botao.addActionListener(acao -> {
            String textoDigitado = caixaNomeM.getText();
            boolean deuCerto = banco.salvarMissao(textoDigitado, 0);
            if (deuCerto) {
                resposta.setText("✅ Missão salva!");
                caixaNomeM.setText("");
            } else {
                resposta.setText("❌ Erro ao salvar!");
            }
        });

        botaoLista.addActionListener(acao -> {
            areaTexto.setText("");
            List<Missao> missoes = banco.lerBase();
            if (missoes.isEmpty()) {
                areaTexto.setText("Nenhuma missão cadastrada.");
            } else {
                missoes.forEach(missao -> areaTexto.append(missao.id() + " - " + missao.nomeMissao() + "\n"));
            }
        });

        botaoExcluir.addActionListener(acao -> {
            String textoId = caixaExcluir.getText().trim();
            if (textoId.isEmpty()) {
                resposta.setText("⚠️ Digite um ID!");
                return;
            }
            try {
                caixaExcluir.setText("");
                int idMissao = Integer.parseInt(textoId);
                boolean deletado = banco.excluirMissao(idMissao);
                if (deletado) {
                    resposta.setText("🗑️ Deletado com sucesso!");
                } else {
                    resposta.setText("⚠️ Não encontrado.");
                }
            } catch (NumberFormatException e) {
                resposta.setText("⚠️ ID inválido!");
            }
        });

        myFrame.setVisible(true);
    }
}