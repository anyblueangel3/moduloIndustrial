package view;

import dao.Usuarios2DAO;
import moduloIndustrial.BD;
import moduloIndustrial.GuiMenuPrincipal;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class GuiCadastroUsuarios2 extends JPanel {
    
    JLabel lbCadastroUsuarios2;
    
    boolean inclusao = false;
    
    JLabel lbId, lbNome, lbSenha;    
    
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    
    JTextField tfId, tfNome;
    JPasswordField pfSenha;
    
    JCheckBox cbCadastroProdutosMP, cbCadastroForProdutoMP, cbPedidoClienteMP, 
            cbPedidoFornecedorMP, cbComposicaoProduto, cbOrdemProducao, 
            cbConsultaProdutoMP, cbRelatorioProdutoMP;
          
    Date nova_data = new Date();
    DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    private Usuarios2DAO usuarios;
        
    public GuiCadastroUsuarios2() {
        
        inicializarComponentes();
        definirEventos();
        
    }
    
    public void inicializarComponentes() {
        setLayout(null);
        
        lbCadastroUsuarios2 = new JLabel("Cadastro de Usuários do Segundo Sistema");
        Font font = new Font("San Serif", Font.PLAIN, 24);
        lbCadastroUsuarios2.setFont(font);
        
        lbId = new JLabel("Identificação: ");
        lbNome = new JLabel("Nome: ");
        lbSenha = new JLabel("Senha: ");
        
        tfId = new JTextField(20);
        tfId.setEditable(false);
        tfNome = new JTextField(50);
        pfSenha = new JPasswordField(50);
        
        cbCadastroProdutosMP = new JCheckBox("Cadastro de Produtos");
        cbCadastroForProdutoMP = new JCheckBox("Cadastro de Ligação Produto-Fornecedores");
        cbPedidoClienteMP = new JCheckBox("Pedido de Cliente de M. P.");
        cbPedidoFornecedorMP = new JCheckBox("Pedido a Fornecedor de M. P.");
        cbComposicaoProduto = new JCheckBox("Associação Produto Acabado com M. P.");
        cbOrdemProducao = new JCheckBox("Ordem de Produção P.A.");
        cbConsultaProdutoMP = new JCheckBox("Consulta Produto de M. P.");
        cbRelatorioProdutoMP = new JCheckBox("Relatório de Produtos de M. P.");
        
        btGravar = new JButton(null, new ImageIcon("c:/icones/icon12/gravar.gif"));
        btGravar.setToolTipText("Gravar");
        btAlterar = new JButton(null, new ImageIcon("c:/icones/icon12/alterar.gif"));
        btAlterar.setToolTipText("Alterar");
        btExcluir = new JButton(null, new ImageIcon("c:/icones/icon12/excluir.gif"));
        btExcluir.setToolTipText("Excluir");
        btLocalizar = new JButton(null, new ImageIcon("c:/icones/icon12/localizar.png"));
        btLocalizar.setToolTipText("Localizar");
        btNovo = new JButton(null, new ImageIcon("c:/icones/icon12/novo.gif"));
        btNovo.setToolTipText("Novo");
        btCancelar = new JButton(null, new ImageIcon("c:/icones/icon12/cancelar.gif"));
        btCancelar.setToolTipText("Cancelar");
        btSair = new JButton(null, new ImageIcon("c:/icones/icon12/sair.png"));
        btSair.setToolTipText("Sair");
        
        lbCadastroUsuarios2.setBounds(300, 50, 500, 50);
        lbId.setBounds(35, 125, 100, 25);
        tfId.setBounds(130, 125, 150, 25);
        lbNome.setBounds(480, 125, 170, 25);
        tfNome.setBounds(620, 125, 270, 25);
        lbSenha.setBounds(35, 175, 100, 25);
        pfSenha.setBounds(120, 175, 300, 25);
        
        // (35coluna,75linha, 645coluna, 650linha) quadrado de colocação de campos
        
        cbCadastroProdutosMP.setBounds(35, 225, 200, 25);
        cbCadastroForProdutoMP.setBounds(335, 225, 280, 25);
        cbPedidoClienteMP.setBounds(635, 225, 200, 25);
        
        cbPedidoFornecedorMP.setBounds(35, 275, 200, 25);
        cbOrdemProducao.setBounds(335, 275, 200, 25);
        cbComposicaoProduto.setBounds(635, 275, 280, 25);
        
        cbConsultaProdutoMP.setBounds(35, 325, 200, 25);
        cbRelatorioProdutoMP.setBounds(335, 325, 200, 25);
        
        btNovo.setBounds     (45, 600, 75, 75);
        btLocalizar.setBounds(145, 600, 75, 75);
        btGravar.setBounds   (245, 600, 75, 75);
        btAlterar.setBounds  (345, 600, 75, 75);
        btExcluir.setBounds  (445, 600, 75, 75);
        btCancelar.setBounds (545, 600, 75, 75);
        btSair.setBounds     (645, 600, 75, 75);
               
        add(lbCadastroUsuarios2);
        add(lbId);
        add(tfId);
        add(lbNome);
        add(tfNome);
        add(lbSenha);
        add(pfSenha);
        
        add(cbCadastroProdutosMP);
        add(cbCadastroForProdutoMP);
        add(cbPedidoClienteMP);
        add(cbPedidoFornecedorMP);
        add(cbOrdemProducao);
        add(cbComposicaoProduto);
        add(cbConsultaProdutoMP);
        add(cbRelatorioProdutoMP);
        
        add(btNovo);
        add(btLocalizar);
        add(btGravar);
        add(btAlterar);
        add(btExcluir);
        add(btCancelar);
        add(btSair);
        
        ativaDesativaTF(false);
        setBotoes(true, true, false, false, false, false);
        usuarios = new Usuarios2DAO();
    }
    
    public void definirEventos() {
        
        tfNome.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if(inclusao == true) {
                    usuarios.usuario.setNome(tfNome.getText());
                    if(usuarios.localizar()) {
                        JOptionPane.showMessageDialog(null, "Usuário já cadastrado!");
                        tfNome.requestFocus();
                    }
                }
                return;
            }
        });
        
        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiMenuPrincipal.liberaMenu();
                BD.getInstance().close();
                setVisible(false);
            }
        });
        
        btNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ativaDesativaTF(true);
                inclusao = true;
                limparCampos();
                setBotoes(false, false, true, false, false, true);
                tfNome.requestFocus();
            }
        });
        
        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ativaDesativaTF(false);
                inclusao = false;
                limparCampos();
                tfNome.requestFocus();
            }
        });
        
                
        btGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(tfNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O nome não podem ser vazio!");
                    tfNome.requestFocus();
                    return;
                }
                
                if(pfSenha.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "A senha não pode ser vazia!");
                    pfSenha.requestFocus();
                    return;
                }
                
                usuarios.usuario.setNome(tfNome.getText());
                usuarios.usuario.setSenha(pfSenha.getText());
                usuarios.usuario.setCadastroProdutosMP(cbCadastroProdutosMP.isSelected());
                usuarios.usuario.setCadastroForProdutoMP(cbCadastroForProdutoMP.isSelected());
                usuarios.usuario.setPedidoClienteMP(cbPedidoClienteMP.isSelected());
                usuarios.usuario.setPedidoFornecedorMP(cbPedidoFornecedorMP.isSelected());
                usuarios.usuario.setConsultaProdutoMP(cbConsultaProdutoMP.isSelected());
                usuarios.usuario.setRelatorioProdutoMP(cbRelatorioProdutoMP.isSelected());
                usuarios.usuario.setComposicaoProduto(cbComposicaoProduto.isSelected());
                usuarios.usuario.setOrdemProducao(cbOrdemProducao.isSelected());
                
                JOptionPane.showMessageDialog(null, usuarios.MPInclusao());
                limparCampos();
                ativaDesativaTF(false);
                inclusao = false;
            }
        });
        
        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usuarios.usuario.setId(Integer.valueOf(tfId.getText()));
                usuarios.usuario.setNome(tfNome.getText());
                usuarios.usuario.setCadastroProdutosMP(cbCadastroProdutosMP.isSelected());
                usuarios.usuario.setCadastroForProdutoMP(cbCadastroForProdutoMP.isSelected());
                usuarios.usuario.setPedidoClienteMP(cbPedidoClienteMP.isSelected());
                usuarios.usuario.setPedidoFornecedorMP(cbPedidoFornecedorMP.isSelected());
                usuarios.usuario.setConsultaProdutoMP(cbConsultaProdutoMP.isSelected());
                usuarios.usuario.setRelatorioProdutoMP(cbRelatorioProdutoMP.isSelected());
                usuarios.usuario.setComposicaoProduto(cbComposicaoProduto.isSelected());
                usuarios.usuario.setOrdemProducao(cbOrdemProducao.isSelected());
                JOptionPane.showMessageDialog(null, usuarios.MPAlterar());
                limparCampos();
                ativaDesativaTF(false);
            }
        });
        
        btExcluir.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e) {
                usuarios.usuario.setId(Integer.valueOf(tfId.getText()));
                usuarios.localizar();
                int n = JOptionPane.showConfirmDialog(null, usuarios.usuario.getNome(),
                        " Excluir o usuário? ", JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, usuarios.MPExcluir());
                    limparCampos();
                    ativaDesativaTF(false);
                }
            }
        });
        
        btLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarCampos();
            }
        });
        
    }
    
    public void limparCampos() {
        
        tfId.setText("");
        tfNome.setText("");
        pfSenha.setText("");
        
        cbCadastroProdutosMP.setSelected(false);
        cbCadastroForProdutoMP.setSelected(false);
        cbPedidoClienteMP.setSelected(false);
        cbPedidoFornecedorMP.setSelected(false);
        cbOrdemProducao.setSelected(false);
        cbComposicaoProduto.setSelected(false);
        cbConsultaProdutoMP.setSelected(false);
        cbRelatorioProdutoMP.setSelected(false);
        setBotoes(true, true, false, false, false, false);
    }
    
    public void atualizarCampos() {
        usuarios.usuario.setNome(tfNome.getText());
        if(usuarios.localizar()) {
            ativaDesativaTF(true);
            tfId.setText(String.valueOf(usuarios.usuario.getId()));
            tfNome.setText(usuarios.usuario.getNome());
            pfSenha.setText(usuarios.usuario.getSenha());        
            cbCadastroProdutosMP.setSelected(usuarios.usuario.isCadastroProdutosMP());
            cbCadastroForProdutoMP.setSelected(usuarios.usuario.isCadastroForProdutoMP());
            cbPedidoClienteMP.setSelected(usuarios.usuario.isPedidoClienteMP());
            cbPedidoFornecedorMP.setSelected(usuarios.usuario.isPedidoFornecedorMP());
            cbOrdemProducao.setSelected(usuarios.usuario.isOrdemProducao());
            cbComposicaoProduto.setSelected(usuarios.usuario.isComposicaoProduto());
            cbConsultaProdutoMP.setSelected(usuarios.usuario.isConsultaProdutoMP());
            cbRelatorioProdutoMP.setSelected(usuarios.usuario.isRelatorioProdutoMP());
            setBotoes(true, true, false, true, true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado! " + usuarios.usuario.getNome());
            limparCampos();
            ativaDesativaTF(false);
        }
    }
    
    public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar,
            boolean bAlterar, boolean bExcluir, boolean bCancelar) {
        btNovo.setEnabled(bNovo);
        btLocalizar.setEnabled(bLocalizar);
        btGravar.setEnabled(bGravar);
        btAlterar.setEnabled(bAlterar);
        btExcluir.setEnabled(bExcluir);
        btCancelar.setEnabled(bCancelar);
    }
    
    private void ativaDesativaTF(boolean ativa) {
        pfSenha.setEnabled(ativa);
        cbCadastroProdutosMP.setEnabled(ativa);
        cbCadastroForProdutoMP.setEnabled(ativa);
        cbPedidoClienteMP.setEnabled(ativa);
        cbOrdemProducao.setEnabled(ativa);
        cbComposicaoProduto.setEnabled(ativa);
        cbPedidoFornecedorMP.setEnabled(ativa);
        cbConsultaProdutoMP.setEnabled(ativa);
        cbRelatorioProdutoMP.setEnabled(ativa);
    }    
    
}
/*
Table: usuarios2
Columns:
id int AI PK 
nome varchar(50) 
senha varchar(50) 
cadastroProdutosMP tinyint(1) 
cadastroFornecedoresProdutoMP tinyint(1) 
pedidoClienteMP tinyint(1) 
pedidoFornecedorMP tinyint(1) 
composicaoProduto tinyint(1) 
ordemProducao tinyint(1) 
consultaProdutoMP tinyint(1) 
relatorioProdutoMP tinyint(1)
*/