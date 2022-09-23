package view;

import dao.CadForProdutoMPDAO;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Fornecedores;
import model.ProdutosMP;
import moduloIndustrial.GuiMenuPrincipal;
import moduloIndustrial.Util;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class GuiCadForProdutoMP extends JFrame {

    ProdutosMP produtoMP;
    public ArrayList<Fornecedores> listaFornecedores = new ArrayList<>();
    Util util = new Util();
    Date nova_data = new Date();
    DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    JLabel lbAssociacaoFornecedores, lbId_cadastro, lbId_produto, lbId_fornecedor,
            lbDescricao, lbNome_razao, lbPreco, lbData_compra, lbData_cadastro,
            lbTabelaFornecedorProdutoMP;
    JTextField tfId_cadastro, tfId_produto, tfDescricao, tfId_fornecedor,
            tfNome_razao, tfPreco, tfData_compra, tfData_cadastro;
    JButton btNovo, btLocalizar, btSalvar, btAlterar, btExcluir, btCancelar, btSair;
    JTable tabelaFornecedores;
    JScrollPane scrollFornecedores;
    CadForProdutoMPDAO fornecedorProduto;

    public GuiCadForProdutoMP(ProdutosMP produtoMP) {

        this.produtoMP = produtoMP;
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {

        setBounds(0, 0, 1000, 800);
        setLayout(null);

        lbAssociacaoFornecedores = new JLabel("Cadastro de Fornecedores desta Matéria Prima");
        Font font = new Font("San Serif", Font.PLAIN, 24);
        lbAssociacaoFornecedores.setFont(font);
        lbAssociacaoFornecedores.setBounds(250, 25, 700, 50);
        add(lbAssociacaoFornecedores);

        lbId_cadastro = new JLabel("Id cadastro: ");
        lbId_produto = new JLabel("Id produto: ");
        lbDescricao = new JLabel("Descrição: ");
        lbId_fornecedor = new JLabel("Id fornecedor: ");
        lbNome_razao = new JLabel("Nome ou razão: ");
        lbPreco = new JLabel("Preço: ");
        lbData_compra = new JLabel("Data de compra: ");
        lbData_cadastro = new JLabel("Data de cadastro: ");

        tfId_cadastro = new JTextField(10);
        tfId_produto = new JTextField(10);
        tfDescricao = new JTextField(80);
        tfId_fornecedor = new JTextField(10);
        tfNome_razao = new JTextField(80);
        tfPreco = new JTextField(15);
        tfData_compra = new JTextField(20);
        tfData_cadastro = new JTextField(20);

        tfId_produto.setText(produtoMP.getId());
        tfDescricao.setText(produtoMP.getDescricao());

        lbId_cadastro.setBounds(35, 75, 200, 25);
        lbId_produto.setBounds(35, 150, 200, 25);
        lbDescricao.setBounds(35, 200, 400, 25);
        lbId_fornecedor.setBounds(400, 150, 200, 25);
        lbNome_razao.setBounds(400, 200, 200, 25);
        lbPreco.setBounds(35, 275, 200, 25);
        lbData_compra.setBounds(335, 275, 200, 25);
        lbData_cadastro.setBounds(635, 275, 200, 25);

        tfId_cadastro.setBounds(35, 100, 100, 25);
        tfId_produto.setBounds(35, 175, 100, 25);
        tfDescricao.setBounds(35, 225, 350, 25);
        tfId_fornecedor.setBounds(400, 175, 130, 25);
        tfNome_razao.setBounds(400, 225, 350, 25);
        tfPreco.setBounds(35, 300, 150, 25);
        tfData_compra.setBounds(335, 300, 200, 25);
        tfData_cadastro.setBounds(635, 300, 200, 25);

        tfId_cadastro.setEditable(false);
        tfId_produto.setEditable(false);
        tfDescricao.setEditable(false);
        tfNome_razao.setEditable(false);

        add(lbId_cadastro);
        add(lbId_produto);
        add(lbDescricao);
        add(lbId_fornecedor);
        add(lbNome_razao);
        add(lbPreco);
        add(lbData_compra);
        add(lbData_cadastro);

        add(tfId_cadastro);
        add(tfId_produto);
        add(tfDescricao);
        add(tfId_fornecedor);
        add(tfNome_razao);
        add(tfPreco);
        add(tfData_compra);
        add(tfData_cadastro);

        btNovo = new JButton("Novo", new ImageIcon("c:/icones/icon12/novo.gif"));
        btNovo.setToolTipText("Novo fornecedor da Matéria Prima");
        btNovo.setBounds(130, 700, 100, 30);
        add(btNovo);

        btLocalizar = new JButton("Localizar", new ImageIcon("c:/icones/icon12/localizar.png"));
        btLocalizar.setToolTipText("Localizar fornecedor referente ao Id de fornecedor");
        btLocalizar.setBounds(240, 700, 130, 30);
        add(btLocalizar);

        btSalvar = new JButton("Gravar", new ImageIcon("c:/icones/icon12/gravar.gif"));
        btSalvar.setToolTipText("Gravar novo registro de fornecedor de Matéria Prima");
        btSalvar.setBounds(380, 700, 100, 30);
        add(btSalvar);

        btAlterar = new JButton("Alterar", new ImageIcon("c:/icones/icon12/alterar.gif"));
        btAlterar.setToolTipText("Gravar alteração de fornecedor de Matéria Prima");
        btAlterar.setBounds(490, 700, 100, 30);
        add(btAlterar);

        btExcluir = new JButton("Excluir", new ImageIcon("c:/icones/icon12/excluir.gif"));
        btExcluir.setToolTipText("Excluir fornecedor da Matéria Prima");
        btExcluir.setBounds(600, 700, 100, 30);
        add(btExcluir);

        btCancelar = new JButton("Cancelar", new ImageIcon("c:/icones/icon12/cancelar.gif"));
        btCancelar.setToolTipText("Cancelar edição de registro");
        btCancelar.setBounds(710, 700, 130, 30);
        add(btCancelar);

        btSair = new JButton("Sair", new ImageIcon("c:/icones/icon12/sair.png"));
        btSair.setToolTipText("Sair");
        btSair.setBounds(850, 700, 80, 30);
        add(btSair);

        DefaultTableModel modeloTabela = new DefaultTableModel(
                new String[]{}, 0) {
        };
        modeloTabela.addColumn("CGC ou CPF");
        modeloTabela.addColumn("Nome ou Razão");
        modeloTabela.addColumn("Cidade");
        modeloTabela.addColumn("Telefone");

        tabelaFornecedores = new JTable(modeloTabela);
        tabelaFornecedores.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabelaFornecedores.getColumnModel().getColumn(1).setPreferredWidth(75);
        tabelaFornecedores.getColumnModel().getColumn(2).setPreferredWidth(25);
        tabelaFornecedores.getColumnModel().getColumn(3).setPreferredWidth(25);

        tabelaFornecedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fornecedorProduto = new CadForProdutoMPDAO();
        listarNaTabela();
        scrollFornecedores = new JScrollPane();
        scrollFornecedores.setBounds(35, 350, 900, 325);
        scrollFornecedores.setViewportView(tabelaFornecedores);
        add(scrollFornecedores);

        tfId_fornecedor.requestFocus();
        //novo, localizar, gravar, alterar, excluir, cancelar, sair
        setBotoes(true, true, false, false, false, true, true);
    }

    private void definirEventos() {

        tfId_fornecedor.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (!fornecedorProduto.localizaFornecedor(tfId_fornecedor.getText())) {
                    JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado! Não pode ser gravado.");
                    tfId_fornecedor.requestFocus();
                    return;
                } else {
                    tfNome_razao.setText(fornecedorProduto.fornecedor.getNome_razao());
                }
            }
        });

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiCadastroProdutosMP.btCadForProduto.setEnabled(true);
            }
        });

        btNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpaFormulario();
                setDatasAgora();
                tfPreco.setText(String.valueOf(produtoMP.getPreco_ultima_compra()));
                //novo, localizar, gravar, alterar, excluir, cancelar, sair
                setBotoes(false, false, true, false, false, true, true);
            }
        });
        
        btLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (tfId_fornecedor.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Id do fornecedor não pode estar em branco!");
                    return;
                } 
                
                if (fornecedorProduto.carregaAssociacao(tfId_produto.getText(),
                        tfId_fornecedor.getText())) {
                    carregaTextField();
                    //novo, localizar, gravar, alterar, excluir, cancelar, sair
                    setBotoes(true, true, false, true, true, true, true);
                }
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (tfId_fornecedor.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Id do fornecedor não pode estar em branco!");
                    tfId_fornecedor.requestFocus();
                    return;
                }

                if (fornecedorProduto.isFornecedor(produtoMP.getId(), tfId_fornecedor.getText())) {
                    JOptionPane.showMessageDialog(null, "Já é fornecedor deste produto!");
                    tfId_fornecedor.requestFocus();
                    return;
                }

                carregaFornecProdutoMP();

                if (fornecedorProduto.gravarFornecedor()) {
                    listarNaTabela();
                    limpaFormulario();
                    //novo, localizar, gravar, alterar, excluir, cancelar, sair
                    setBotoes(true, true, false, false, false, true, true);
                }
            }
        });
        
        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (tfId_fornecedor.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Id do fornecedor não pode estar em branco!");
                    tfId_fornecedor.requestFocus();
                    return;
                }
                
                if (fornecedorProduto.isOutroFornecedor(util.spaceToInt(tfId_cadastro.getText()),
                        tfId_produto.getText(), tfId_fornecedor.getText())) {
                    JOptionPane.showMessageDialog(null, "Fornecedor já associado, não pode alterar!");
                    return;
                }
                
                carregaFornecProdutoMP();
                fornecedorProduto.fornecProdutoMP.setId(util.spaceToInt(tfId_cadastro.getText()));
                
                if (fornecedorProduto.alterarFornecedor()) {
                    listarNaTabela();
                    limpaFormulario();
                    //novo, localizar, gravar, alterar, excluir, cancelar, sair
                    setBotoes(true, true, false, false, false, true, true);
                }
                
            }
        });
        
        btExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fornecedorProduto.excluirAssociacao(util.spaceToInt(tfId_cadastro.getText()))) {
                    JOptionPane.showMessageDialog(null, "Associação excluída com sucesso!");
                    listarNaTabela();
                    limpaFormulario();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema para excluír associação!");    
                }
                //novo, localizar, gravar, alterar, excluir, cancelar, sair
                setBotoes(true, true, false, false, false, true, true);
            }                    
        });

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpaFormulario();
                //novo, localizar, gravar, alterar, excluir, cancelar, sair
                setBotoes(true, true, false, false, false, true, true);
            }
        });

        tabelaFornecedores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int linha = tabelaFornecedores.getSelectedRow();
                if (linha < 0) {
                    return;
                }
                tfId_fornecedor.setText(listaFornecedores.get(linha).getId_cgc_cpf());
                if (fornecedorProduto.carregaAssociacao(tfId_produto.getText(),
                        tfId_fornecedor.getText())) {
                    carregaTextField();
                    //novo, localizar, gravar, alterar, excluir, cancelar, sair
                    setBotoes(true, true, false, true, true, true, true);
                }
            }
        });

    }

    public void listarNaTabela() {
        DefaultTableModel dtmFornecedores = (DefaultTableModel) tabelaFornecedores.getModel();
        listaFornecedores = new ArrayList<>();
        listaFornecedores = fornecedorProduto.listarFornecedoresProdutoMP(produtoMP);
        dtmFornecedores.setNumRows(0);
        int cont = listaFornecedores.size();
        for (int i = 0; i < cont; i++) {
            String dados[] = new String[4];
            dados[0] = listaFornecedores.get(i).getId_cgc_cpf();
            dados[1] = listaFornecedores.get(i).getNome_razao();
            dados[2] = listaFornecedores.get(i).getCidade();
            dados[3] = listaFornecedores.get(i).getTelefone();
            dtmFornecedores.addRow(dados);
        }
    }

    private void limpaFormulario() {
        tfId_fornecedor.setText("");
        tfNome_razao.setText("");
        tfPreco.setText("");
        tfData_compra.setText("");
        tfData_cadastro.setText("");
    }

    private void setDatasAgora() {
        nova_data = new Date();
        tfData_compra.setText(formatoData.format(nova_data));
        tfData_cadastro.setText(formatoData.format(nova_data));
    }

    //novo, localizar, gravar, alterar, excluir, cancelar, sair
    private void setBotoes(boolean novo, boolean localizar, boolean gravar,
            boolean alterar, boolean excluir, boolean cancelar, boolean sair) {
        btNovo.setEnabled(novo);
        btLocalizar.setEnabled(localizar);
        btSalvar.setEnabled(gravar);
        btAlterar.setEnabled(alterar);
        btExcluir.setEnabled(excluir);
        btCancelar.setEnabled(cancelar);
        btSair.setEnabled(sair);
    }

    private void carregaTextField() {
        tfId_cadastro.setText(String.valueOf(fornecedorProduto.fornecProdutoMP.getId()));
        tfId_fornecedor.setText(fornecedorProduto.fornecProdutoMP.getId_fornecedor());
        if (!fornecedorProduto.localizaFornecedor(tfId_fornecedor.getText())) {
            JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado!\n"
                    + " Chame o administrador do sistema.");
        } else {
            tfNome_razao.setText(fornecedorProduto.fornecedor.getNome_razao());
        }
        tfPreco.setText(String.valueOf(fornecedorProduto.fornecProdutoMP.getPreco()));
        tfData_compra.setText(formatoData.format(fornecedorProduto.fornecProdutoMP.getData_compra()));
        tfData_cadastro.setText(formatoData.format(fornecedorProduto.fornecProdutoMP.getData_cadastro()));
    }

    private void carregaFornecProdutoMP() {
        fornecedorProduto.fornecProdutoMP.setId_produtoMP(tfId_produto.getText());
        fornecedorProduto.fornecProdutoMP.setId_fornecedor(tfId_fornecedor.getText());
        fornecedorProduto.fornecProdutoMP.setPreco(util.spaceToDouble(tfPreco.getText()));
        try {
            nova_data = formatoData.parse(tfData_compra.getText());
            java.sql.Date sqlData = new java.sql.Date(nova_data.getTime());
            fornecedorProduto.fornecProdutoMP.setData_compra(sqlData);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data!\n" + erro);
            return;
        }
        try {
            nova_data = formatoData.parse(tfData_cadastro.getText());
            java.sql.Date sqlData = new java.sql.Date(nova_data.getTime());
            fornecedorProduto.fornecProdutoMP.setData_cadastro(sqlData);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data!\n" + erro);
            return;
        }
    }

}

/*
Table: fornec_produtomp
Columns:
id int AI PK 
id_produtoMP varchar(10) 
id_fornecedor varchar(16) 
preco double 
data_compra datetime 
data_cadastro datetime

Table: materia_prima
Columns:
id varchar(10) PK 
descricao varchar(60) 
categoria varchar(10) 
quantidade double 
unidade varchar(10) 
preco_venda double 
preco_ultima_compra double 
data_cadastro datetime
 */
