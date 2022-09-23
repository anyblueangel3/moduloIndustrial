package view;

import dao.PedidosClienteMPDAO;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import moduloIndustrial.GuiMenuPrincipal;
import moduloIndustrial.Util;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class GuiPedidoClienteMP extends JPanel {

    JTextField tfId_pedido, tfId_cliente, tfId_endereco_entrega, tfCondicao_pag, tfData_pedido;
    JLabel lbId_pedido, lbId_cliente, lbId_endereco_entrega, lbCondicao_pag, lbData_pedido;

    JTextField tfId_item, tfId_pedido_cli, tfId_produto, tfQuantidade, tfPreco, tfData_entrega;
    JLabel lbId_item, lbId_pedido_cli, lbId_produto, lbQuantidade, lbPreco, lbData_entrega;

    JLabel lbTituloTela;

    JButton btSair, btNovo1, btGravar1, btAlterar1, btExcluir1, btImprimir, btNovo2,
            btGravar2, btAlterar2, btExcluir2, btBaixarEstoque, btLocalizar1,
            btLimpar1, btLimpar2;

    JTable tabelaItens;
    DefaultTableModel tableModel = new DefaultTableModel(new String[]{}, 0) {
    };
    JScrollPane spItens;

    String[] colunas = {"Id item", "Id produto", "Descrição do produto",
        "Quantidade", "Preco", "Total"};

    PedidosClienteMPDAO pedidosDAO;
    Util util = new Util();
    
    Date nova_data = new Date();
    DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public GuiPedidoClienteMP() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setLayout(null);

        btSair = new JButton(" SAIR ");
        btNovo1 = new JButton(" Novo Pedido ");
        btGravar1 = new JButton(" Gravar Pedido");
        btAlterar1 = new JButton(" Gravar Alteração ");
        btExcluir1 = new JButton(" Excluir Pedido ");
        btLimpar1 = new JButton(" Limpar Dados ");
        btLocalizar1 = new JButton(" Localizar ");

        btNovo2 = new JButton(" Novo Item ");
        btGravar2 = new JButton(" Gravar Item ");
        btAlterar2 = new JButton(" Alterar Item ");
        btExcluir2 = new JButton(" Excluir Item ");
        btLimpar2 = new JButton(" Limpar Item ");

        btBaixarEstoque = new JButton(" Baixar Estoque ");
        btImprimir = new JButton(" Imprimir Pedido ");

        lbTituloTela = new JLabel("Pedido de Cliente de Matéria Prima");
        Font font = new Font("San Serif", Font.PLAIN, 24);
        lbTituloTela.setFont(font);

        lbId_pedido = new JLabel("Id do Pedido: ");
        lbId_cliente = new JLabel("Id do Cliente: ");
        lbId_endereco_entrega = new JLabel("Id do Endereço de entrega: ");
        lbCondicao_pag = new JLabel("Id da Condição de pagamento: ");
        lbData_pedido = new JLabel("Data do pedido: ");

        lbId_item = new JLabel("Id do Item: ");
        lbId_produto = new JLabel("Id do Produto: ");
        lbQuantidade = new JLabel("Quantidade: ");
        lbPreco = new JLabel("Preço: ");
        lbData_entrega = new JLabel("Data de entrega: ");

        tfId_pedido = new JTextField(10);
        tfId_cliente = new JTextField(10);
        tfId_endereco_entrega = new JTextField(10);
        tfCondicao_pag = new JTextField(10);
        tfData_pedido = new JTextField(20);

        tfId_item = new JTextField(10);
        tfId_produto = new JTextField(10);
        tfQuantidade = new JTextField(10);
        tfPreco = new JTextField(10);
        tfData_entrega = new JTextField(20);

        lbTituloTela.setBounds(35, 25, 400, 50);
        lbId_pedido.setBounds(35, 80, 100, 25);
        lbId_cliente.setBounds(140, 80, 100, 25);
        lbId_endereco_entrega.setBounds(250, 80, 200, 25);
        lbCondicao_pag.setBounds(35, 140, 200, 25);
        lbData_pedido.setBounds(240, 140, 100, 25);

        lbId_item.setBounds(35, 250, 100, 25);
        lbId_produto.setBounds(140, 250, 100, 25);
        lbQuantidade.setBounds(245, 250, 110, 25);
        lbPreco.setBounds(360, 250, 50, 25);

        tfId_pedido.setBounds(35, 110, 100, 25);
        tfId_cliente.setBounds(140, 110, 100, 25);
        tfId_endereco_entrega.setBounds(250, 110, 100, 25);
        tfCondicao_pag.setBounds(35, 170, 100, 25);
        tfData_pedido.setBounds(240, 170, 150, 25);

        tfId_item.setBounds(35, 280, 100, 25);
        tfId_produto.setBounds(140, 280, 100, 25);
        tfQuantidade.setBounds(245, 280, 110, 25);
        tfPreco.setBounds(360, 280, 110, 25);

        btSair.setBounds(600, 25, 150, 25);
        btNovo1.setBounds(600, 100, 150, 25);
        btGravar1.setBounds(600, 130, 150, 25);
        btAlterar1.setBounds(600, 160, 150, 25);
        btExcluir1.setBounds(600, 190, 150, 25);
        btLocalizar1.setBounds(790, 100, 150, 25);
        btLimpar1.setBounds(790, 130, 150, 25);
        btImprimir.setBounds(790, 160, 150, 25);

        btNovo2.setBounds(500, 280, 130, 25);
        btGravar2.setBounds(660, 280, 130, 25);
        btAlterar2.setBounds(500, 310, 130, 25);
        btExcluir2.setBounds(660, 310, 130, 25);
        btLimpar2.setBounds(820, 280, 130, 25);

        btBaixarEstoque.setBounds(790, 25, 150, 25);

        spItens = new JScrollPane();
        spItens.setBounds(35, 350, 800, 380);
        for (int i = 0; i < 6; i++) {
            tableModel.addColumn(colunas[i]);
        }

        tabelaItens = new JTable(tableModel);
        tabelaItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        spItens.setViewportView(tabelaItens);

        add(btSair);
        add(btNovo1);
        add(btGravar1);
        add(btAlterar1);
        add(btExcluir1);
        add(btBaixarEstoque);
        add(btLocalizar1);
        add(btLimpar1);
        add(btImprimir);

        add(btNovo2);
        add(btGravar2);
        add(btAlterar2);
        add(btExcluir2);
        add(btLimpar2);

        add(lbTituloTela);
        add(lbTituloTela);
        add(lbId_pedido);
        add(lbId_cliente);
        add(lbId_endereco_entrega);
        add(lbCondicao_pag);
        add(lbData_pedido);
        add(lbId_item);
        add(lbId_produto);
        add(lbQuantidade);
        add(lbPreco);
        add(lbData_entrega);

        add(tfId_pedido);
        add(tfId_cliente);
        add(tfId_endereco_entrega);
        add(tfCondicao_pag);
        add(tfData_pedido);

        add(tfId_item);
        add(tfId_produto);
        add(tfQuantidade);
        add(tfPreco);
        add(tfData_entrega);

        add(spItens);

        pedidosDAO = new PedidosClienteMPDAO();
        setBotoesPedido(true, false, false, false, true, true, false);
        setBotoesItem(false);
        setTFPedido(false);
        setTFItem(false, false);
        btImprimir.setEnabled(false);
    }

    private void definirEventos() {
        
        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiMenuPrincipal.liberaMenu();
                setVisible(false);
            }
        });

    }

    private void setBotoesPedido(boolean novo1, boolean gravar1, boolean alterar1,
            boolean excluir1, boolean localizar1, boolean limpar1, boolean baixarEstoque) {
        btNovo1.setEnabled(novo1);
        btGravar1.setEnabled(gravar1);
        btAlterar1.setEnabled(alterar1);
        btExcluir1.setEnabled(excluir1);
        btLocalizar1.setEnabled(localizar1);
        btLimpar1.setEnabled(limpar1);
        btBaixarEstoque.setEnabled(baixarEstoque);
    }

    // novo2, gravar2, alterar2, excluir2, limpar2
    private void setBotoesItem(boolean novo2, boolean gravar2, boolean alterar2,
            boolean excluir2, boolean limpar2) {
        btNovo2.setEnabled(novo2);
        btGravar2.setEnabled(gravar2);
        btAlterar2.setEnabled(alterar2);
        btExcluir2.setEnabled(excluir2);
        btLimpar2.setEnabled(limpar2);
    }

    // todas os botões
    private void setBotoesItem(boolean portas) {
        btNovo2.setEnabled(portas);
        btGravar2.setEnabled(portas);
        btAlterar2.setEnabled(portas);
        btExcluir2.setEnabled(portas);
        btLimpar2.setEnabled(portas);
    }

    private void setTFPedido(boolean portas) {
        //tfId_pedido.setEditable(porta);
        tfId_cliente.setEditable(portas);
        tfId_endereco_entrega.setEditable(portas);
        tfCondicao_pag.setEditable(portas);
        tfData_pedido.setEditable(portas);
    }

    private void setTFItem(boolean portaId, boolean portaOutros) {
        tfId_item.setEditable(portaId);
        tfId_produto.setEditable(portaOutros);
        tfQuantidade.setEditable(portaOutros);
        tfPreco.setEditable(portaOutros);
    }

    public void limparCamposPedido() {
        tfId_pedido.setText("");
        tfId_cliente.setText("");
        tfId_endereco_entrega.setText("");
        tfCondicao_pag.setText("");
        nova_data = new Date();
        tfData_pedido.setText(formatoData.format(nova_data.getTime()));
        // novo1, gravar1, alterar1, excluir1, localizar, limpar, baixarEstoque
        setBotoesPedido(false, true, false, false, false, true, false);
        // todos botões.
        setBotoesItem(false);
    }

    public void limparCamposItem() {
        tfId_item.setText("");
        tfId_produto.setText("");
        tfQuantidade.setText("");
        tfPreco.setText("");
        //setTFItem(false, true);
        //setBotoesItem(true, true, false, false, false);
    }
}

/*
Table: pedidos_climp
Columns:
id int AI PK 
id_cliente varchar(16) 
id_endereco_entrega varchar(10) 
condicao_pag varchar(10) 
data_pedido datetime

Table: item_pedido_climp
Columns:
id int AI PK 
id_pedido_cli int 
id_produto varchar(10) 
quantidade double 
preco double 
data_entrega datetime
 */
