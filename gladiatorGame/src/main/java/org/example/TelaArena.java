package org.example;

import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;
public class TelaArena {

    private boolean turnoPLayer1 = true;
    GeradorLoot geraloot = new GeradorLoot();



    public void iniciarTela(Personagem p1, Personagem p2) {
        // 1. Cria a janela e já define o título que aparece na barra superior
        JFrame janela = new JFrame("Arena RPG - Batalha");

        // 2. Define o tamanho da tela do jogo (Largura x Altura) em pixels
        janela.setSize(800, 600);

        // 3. REGRA DE OURO: Garante que o programa morra ao clicar no 'X'
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 4. Truque de UI: Centraliza a janela no meio do seu monitor
        janela.setLocationRelativeTo(null);




        JPanel painelJogo = new JPanel();

        painelJogo.setBackground(Color.DARK_GRAY);
        // Assim poderemos usar posições (X, Y) exatas para os bonecos depois.
        painelJogo.setLayout(null);
        janela.add(painelJogo);

        //BARRAS DE VIDA
        JProgressBar barraVida1 = new JProgressBar(0,100);
        barraVida1.setValue(100);
        barraVida1.setForeground(Color.GREEN);
        // (Opcional) Mostra os números no meio da barra!
        barraVida1.setStringPainted(true);
        barraVida1.setBounds(30,30,150,20);
        painelJogo.add(barraVida1);

        JProgressBar barraVida2 = new JProgressBar(0,100);
        barraVida2.setValue(100);
        barraVida2.setForeground(Color.GREEN);
        // (Opcional) Mostra os números no meio da barra!
        barraVida2.setStringPainted(true);
        barraVida2.setBounds(610,30,150,20);
        painelJogo.add(barraVida2);

        //IMAGENS
        ImageIcon iconeP1 = new ImageIcon("imagens/leo.png");
        JLabel imagemP1 = new JLabel(iconeP1);
        imagemP1.setBounds(20, 100, 150, 150);
        painelJogo.add(imagemP1);

        ImageIcon iconeP2 = new ImageIcon("imagens/jay.png");
        JLabel imagemP2 = new JLabel(iconeP2);
        imagemP2.setBounds(600, 100, 150, 150);
        painelJogo.add(imagemP2);

        //TEXTO
        JTextField turnoText = new JTextField(" - Jogador 1 é a sua vez - ");

        turnoText.setEditable(false);
        turnoText.setBounds(270,30,250,30);
        turnoText.setFont(new Font("Arial",Font.PLAIN , 20));
        turnoText.setBackground(Color.LIGHT_GRAY);

        painelJogo.add(turnoText);


        //CAIXA DE TEXTO
        JTextArea logBatalha = new JTextArea();
        logBatalha.setEditable(false);
        logBatalha.setBackground(Color.BLACK);
        logBatalha.setForeground(Color.WHITE);

        JScrollPane painelRolagem = new JScrollPane(logBatalha);

        painelRolagem.setBounds(250,400,300,80);

        painelJogo.add(painelRolagem);


        //CRIACAO DE BOTOES
        JButton botaoAtacar = new JButton("Atacar");
        botaoAtacar.setBounds(250, 500, 100, 40);
        painelJogo.add(botaoAtacar); // Adiciona o botão no painel

        JButton botaoCura = new JButton("Curar");
        botaoCura.setBounds(370,500,100,40);
        painelJogo.add(botaoCura);

        JButton botaoProcurar = new JButton("Procurar item");
        botaoProcurar.setBounds(500,500,130,40);
        painelJogo.add(botaoProcurar);

//ACAO AOS BOTOES
        botaoAtacar.addActionListener( e-> {
            System.out.println("BUM! O botão de ataque foi clicado!");
            if(turnoPLayer1){
                int dano = p1.atacar(p2);
                logBatalha.append(p1.getName() + " atacou "+ p2.getName() + " causando " + dano +" de dano\n");
                //System.out.println(p2.getName() +" vida: "+ p2.getVida());
                barraVida2.setValue((int) p2.getVida());
               // textoPlayer2.setText(p2.getName()+ " - HP: "+ p2.getVida());
                passarTurno(turnoText);


            } else{
                int dano = p2.atacar(p1);
                logBatalha.append(p2.getName() + " atacou "+ p1.getName() + " causando " + dano +" de dano\n");
                //System.out.println(p2.getName() +" vida: "+ p1.getVida());
                barraVida1.setValue((int) p1.getVida());
               // textoPlayer1.setText(p1.getName()+ " - HP: "+ p1.getVida());
                passarTurno(turnoText);
            }
            //Coloracao da vida
            atualizarCoresBarras(p1,p2,barraVida1,barraVida2);
            //checagem de derrota
            checarVitoria(p1,p2,botaoAtacar,botaoCura,botaoProcurar);



        });

        botaoCura.addActionListener(e->{
            if(turnoPLayer1){
                int cura = p1.curar();
                barraVida1.setValue((int) p1.getVida());
                logBatalha.append(p1.getName() +" curou "+cura+" de vida\n");
                passarTurno(turnoText);
            } else{
                int cura = p2.curar();
                barraVida2.setValue((int) p2.getVida());
                logBatalha.append(p2.getName() +" curou "+cura+" de vida\n");
                passarTurno(turnoText);
            }

            atualizarCoresBarras(p1,p2,barraVida1,barraVida2);
        });



        botaoProcurar.addActionListener(e->{
            Item itemEncontrado = geraloot.sortearItem();
            if(turnoPLayer1){
                //Arma?
                if (itemEncontrado instanceof Arma) {
                    Arma armaEncontrada = (Arma) itemEncontrado; // Casting!
                    p1.equipar(armaEncontrada);
                }
                //Ou será que ele é uma Armadura?
                else if (itemEncontrado instanceof Armadura) {
                    Armadura armaduraEncontrada = (Armadura) itemEncontrado; // Casting!
                    p1.equipar(armaduraEncontrada);

                }

                logBatalha.append(p1.getName() + " encontrou " + itemEncontrado.getNome() + "!\n");
                passarTurno(turnoText);

            } else{
                //Arma?
                if (itemEncontrado instanceof Arma) {
                    Arma armaEncontrada = (Arma) itemEncontrado; //Casting!
                    p2.equipar(armaEncontrada);
                }
                //Ou será que ele é uma Armadura?
                else if (itemEncontrado instanceof Armadura) {
                    Armadura armaduraEncontrada = (Armadura) itemEncontrado; //Casting!
                    p2.equipar(armaduraEncontrada);

                }

                logBatalha.append(p2.getName() + " encontrou " + itemEncontrado.getNome() + "!\n");
                passarTurno(turnoText);
            }





        });




        //---------------------------------------------------------------------------------------//


        janela.add(painelJogo);

        // 5. Faz a janela aparecer (deve ser sempre a última coisa a ser chamada!)
        janela.setVisible(true);

    }

    private void passarTurno(JTextField texto){
        if(turnoPLayer1){
            turnoPLayer1=false;
            texto.setText(" - Jogador 2 é a sua vez - ");
        }else{
            turnoPLayer1=true;
            texto.setText(" - Jogador 1 é a sua vez - ");
        }
    }

    private void checarVitoria(Personagem p1, Personagem p2, JButton botaoAtacar, JButton botaoCurar, JButton botaoProcurar){
        if(p2.getVida() <= 0){
            // textoPlayer2.setText(p2.getName() + " - HP: DERROTADO");
            botaoAtacar.setEnabled(false);
            botaoCurar.setEnabled(false);
            botaoProcurar.setEnabled(false);
            JOptionPane.showMessageDialog(null, p1.getName() + " venceu a batalha!");
        } else if (p1.getVida() <=0) {
            //  textoPlayer1.setText(p1.getName() + " - HP: DERROTADO");
            botaoAtacar.setEnabled(false);
            botaoCurar.setEnabled(false);
            botaoProcurar.setEnabled(false);
            JOptionPane.showMessageDialog(null, p2.getName() + " venceu a batalha!");
        }
    }

    private void atualizarCoresBarras(Personagem p1,Personagem p2, JProgressBar b1, JProgressBar b2){
        if(p1.getVida() < 20){
            b1.setForeground(Color.RED);
        } else if (p1.getVida() < 50) {
            b1.setForeground(Color.ORANGE);
        } else {
            b1.setForeground(Color.GREEN);
        }

        if(p2.getVida() < 20){
            b2.setForeground(Color.RED);
        } else if (p2.getVida() < 50) {
            b2.setForeground(Color.ORANGE);
        }else {
            b2.setForeground(Color.GREEN);
        }
    }

}
