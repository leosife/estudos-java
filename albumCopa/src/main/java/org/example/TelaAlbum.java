package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;


public class TelaAlbum extends JFrame {
    private JTextField txtNumero;
    private JTextField txtNome;
    private JComboBox<Selecoes> comboSelecoes;
    private JTextField txtQuantidade;
    private JButton btnSalvar;
    private JButton btnListar;
    private JButton btnDeletar;

    // botoes para filtrar

    private JButton btnTodos;
    private  JButton btnSelecao;
    private JButton btnNumero;
    private JButton btnNome;

    private JTable tabelaFigurinhas;
    private DefaultTableModel modeloTabela;


    private TableRowSorter<DefaultTableModel> sorter;

    private FigurinhaDAO base = new FigurinhaDAO();


    public TelaAlbum() {
        setTitle("Album da copa");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout());

        inicializarComponentes();
    }

    private void atualizarTabela(){
        modeloTabela.setRowCount(0);
        List<Figurinha> listaDoBanco = base.listarTodas();
        for (Figurinha fig : listaDoBanco) {
            Object[] linha = {fig.numero(), fig.selecao(), fig.nome(), fig.quantidade()};
            modeloTabela.addRow(linha);
        }

    }

    private void inicializarComponentes() {
        // Painel para organizar o formulário no topo
        JPanel painelFormulario = new JPanel(new FlowLayout());
        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        // Instanciação dos componentes com rótulos (Labels)
        txtNumero = new JTextField(5);
        txtNome = new JTextField(15);
        comboSelecoes = new JComboBox<>(Selecoes.values()); // Popula com as seleções do Enum
        txtQuantidade = new JTextField(5);
        btnSalvar = new JButton("Salvar");
        btnListar = new JButton("Listar");
        btnDeletar = new JButton("Deletar");

        painelFormulario.add(new JLabel("Numero: "));
        painelFormulario.add(txtNumero);

        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Seleção:"));
        painelFormulario.add(comboSelecoes);

        painelFormulario.add(new JLabel("Qtd:"));
        painelFormulario.add(txtQuantidade);

        painelFormulario.add(btnSalvar);
        painelFormulario.add(btnDeletar);

//-------------------------------------------------------------------------------------------------------------------------------------
        JPanel painelAcoes = new JPanel(new BorderLayout());
        painelAcoes.add(btnListar, BorderLayout.WEST);
        painelAcoes.add(painelFiltros,BorderLayout.EAST);

        btnTodos = new JButton("Todas");
        btnSelecao = new JButton("Seleçao");
        btnNome = new JButton("Nome");
        btnNumero = new JButton("Numero");

        painelFiltros.add(new JLabel("Filtrar por:"));
        painelFiltros.add(btnTodos);
        painelFiltros.add(btnNumero);
        painelFiltros.add(btnNome);
        painelFiltros.add(btnSelecao);


//-------------------------------------------------------------------------------------------------------------------------------------




        // Passo A: Definimos os nomes das colunas em um array de String
        String[] colunas = {"Número", "Seleção", "Nome", "Quantidade"};
        // Passo B: Instanciamos o Modelo passando as colunas e o número inicial de linhas (0)
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloqueia edição direta na célula da tabela
            }
        };
        // Passo C: Criamos a JTable CONECTANDO ela ao modelo!
        tabelaFigurinhas = new JTable(modeloTabela);

        // 2. Instanciamos e vinculamos o TableRowSorter à JTable
        sorter = new TableRowSorter<>(modeloTabela);
        tabelaFigurinhas.setRowSorter(sorter);

        // Passo D: OBRIGATÓRIO! Colocamos a JTable dentro de um JScrollPane para aparecer o cabeçalho e ter barra de rolagem
        JScrollPane painelRolagem = new JScrollPane(tabelaFigurinhas);

        // Adicionamos no centro da janela
        add(painelRolagem, BorderLayout.CENTER);

        // Agrupando Formulário e Filtros no topo (Container)
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(painelFormulario, BorderLayout.NORTH);
        painelTopo.add(painelAcoes, BorderLayout.SOUTH);

        add(painelTopo, BorderLayout.NORTH);



        //--------------------------------------UPDATE-------------------------------------------------------------

        // 1. Pega o gerenciador de seleções da tabela e adiciona um ouvinte (listener)
        tabelaFigurinhas.getSelectionModel().addListSelectionListener(e -> {

            // 2. Filtra eventos "fantasma" do clique
            if (!e.getValueIsAdjusting()) {

                // 3. Captura o índice visual da linha selecionada na tela
                int linhaView = tabelaFigurinhas.getSelectedRow();

                // 4. Checa se existe realmente uma linha selecionada (evita erro quando nada está selecionado)
                if (linhaView != -1) {

                    int linhaModel = tabelaFigurinhas.convertRowIndexToModel(linhaView);

                    Object numero = modeloTabela.getValueAt(linhaModel, 0);
                    Object selecao = modeloTabela.getValueAt(linhaModel, 1);
                    Object nome = modeloTabela.getValueAt(linhaModel, 2);
                    Object quantidade = modeloTabela.getValueAt(linhaModel,3);

                    txtNumero.setText(numero.toString());
                    txtNome.setText(nome.toString());
                    txtQuantidade.setText(quantidade.toString());
                    comboSelecoes.setSelectedItem(selecao);



                }

            }








        });






        // --------------------------botoes para filtrar---------------
        btnTodos.addActionListener(e -> sorter.setRowFilter(null));


        // O ^ e $ garantem que a coluna 0 seja EXATAMENTE o número digitado
        btnNumero.addActionListener(acao -> sorter.setRowFilter(RowFilter.regexFilter("^" + txtNumero.getText() + "$",0)));

        btnNome.addActionListener(acao -> sorter.setRowFilter(RowFilter.regexFilter("(?i)" +txtNome.getText(),2)));

        btnSelecao.addActionListener(acao -> {
            Selecoes selecao = (Selecoes) comboSelecoes.getSelectedItem();
            sorter.setRowFilter(RowFilter.regexFilter(selecao.getNomeFormatado(),1));});




        // ----------------------------------------------------------botoes para Salvar ----------------------------------------------------------
        btnSalvar.addActionListener(acao -> {
                    if (txtNome.getText().isEmpty() || txtNumero.getText().isEmpty() || txtQuantidade.getText().isEmpty() || comboSelecoes.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(this, "Erro - Preencha todos os campos");
                        return;
                    }

                    try {
                        String nome = txtNome.getText();
                        int numero = Integer.parseInt(txtNumero.getText());
                        Selecoes selecao = (Selecoes) comboSelecoes.getSelectedItem();
                        int quantidade = Integer.parseInt(txtQuantidade.getText());
                        Figurinha fd = new Figurinha(numero, selecao, nome, quantidade);
                         if (base.seExiste(numero)){
                             boolean atualizou = base.atualizar(fd);
                             if (atualizou){
                                 JOptionPane.showMessageDialog(this,"Figurinha atualizada com sucesso");
                             } else {
                                 JOptionPane.showMessageDialog(this,"ERRO ao atualizar");
                             }
                        } else {
                             base.salvar(fd);
                             JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                         }


                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Digite as informações corretamente");
                    }


                    txtNumero.setText("");
                    txtNome.setText("");
                    txtQuantidade.setText("");
                    comboSelecoes.setSelectedIndex(0);
                    atualizarTabela();


                });

        btnListar.addActionListener(acao -> {
            atualizarTabela();

        });

        btnDeletar.addActionListener(acao -> {
            int linha = tabelaFigurinhas.getSelectedRow();
            if (linha < 0){
                JOptionPane.showMessageDialog(this,"Selecione qual jogador quer excluir");
                return;
            }

            int linhaModel = tabelaFigurinhas.convertRowIndexToModel(linha);
            int idDeletar = (int) modeloTabela.getValueAt(linhaModel,0);

            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta figurinha?","Confirme Exclusao",JOptionPane.YES_NO_OPTION);

            if(resposta == JOptionPane.YES_OPTION){
                base.excluir(idDeletar);

            }
            atualizarTabela();



        });

    }


}
