package moduloIndustrial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Usuarios2;
import view.GuiCadastroProdutosMP;
import view.GuiCadastroUsuarios2;

/**
 *
 * @author Ronaldo R. Godoi
 */
public class GuiMenuPrincipal extends JFrame {

    private Container contentPane;
    private JMenuBar mnBarra;
    private static JMenu mnArquivos, mnPedidos, mnConsultas, mnRelatorios;
    private JMenuItem miSair, miProdutosMP, miForProdutoMP, miUsuarios2;
    private JMenuItem miPedidoClienteMP, miPedidoFornecedorMP,
            miComposicaoProduto, miOrdemProducao;
    private JMenuItem miRelatorioProdutosMP;
    private JMenuItem miConsultaProdutosMP;

    Usuarios2 ausuario = new Usuarios2();

    public GuiMenuPrincipal(Usuarios2 ausuario) {
        this.ausuario = ausuario;
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Aplicativo Empresa");
        setBounds(0, 0, 1000, 800);
        contentPane = getContentPane();
        mnBarra = new JMenuBar();
        mnArquivos = new JMenu("Arquivos");
        mnArquivos.setMnemonic('A');
        mnPedidos = new JMenu("Pedidos");
        mnPedidos.setMnemonic('P');
        mnConsultas = new JMenu("Consultas");
        mnConsultas.setMnemonic('C');
        mnRelatorios = new JMenu("Relatórios");
        mnRelatorios.setMnemonic('R');

        // Arquivos ou Cadastros
        miProdutosMP = new JMenuItem("Cadastro de Produtos Matéria Prima");
        miForProdutoMP = new JMenuItem("Cadastro de vários Fornecedores de um Produto M.P.");
        miUsuarios2 = new JMenuItem("Cadastro de Usuários do Sistema P.C.P.");
        miSair = new JMenuItem("Sair", new ImageIcon("c:/icones/sair2.jpg"));
        miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

        // Pedidos
        miPedidoClienteMP = new JMenuItem("Pedidos de Clientes de Materia Prima");
        miPedidoFornecedorMP = new JMenuItem("Pedidos para Fornecedor de Materia Prima");
        miComposicaoProduto = new JMenuItem("Registro de composição de Produto");
        miOrdemProducao = new JMenuItem("Ordem de Produção");

        // Consultas
        miConsultaProdutosMP = new JMenuItem("Consulta Matéria Prima");

        // Relatórios
        miRelatorioProdutosMP = new JMenuItem("Relatório de Matéria Prima");

        //Menu Cadastros
        mnArquivos.add(miProdutosMP);
        mnArquivos.add(miForProdutoMP);
        mnArquivos.add(miUsuarios2);
        mnArquivos.add(miSair);

        //Menu Pedidos
        mnPedidos.add(miPedidoClienteMP);
        mnPedidos.add(miPedidoFornecedorMP);
        mnPedidos.add(miComposicaoProduto);
        mnPedidos.add(miOrdemProducao);

        //Menu Consultas
        mnConsultas.add(miConsultaProdutosMP);

        //Menu Relatórios
        mnRelatorios.add(miRelatorioProdutosMP);

        mnBarra.add(mnArquivos);
        mnBarra.add(mnPedidos);
        mnBarra.add(mnConsultas);
        mnBarra.add(mnRelatorios);
        setJMenuBar(mnBarra);
        liberaMenu();
        travaUsuario(ausuario);
    }

    private void definirEventos() {

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                if (mnArquivos.isEnabled()) {
                    System.out.println("Saindo do Aplicativo...");
                    //BD.getInstance().close();
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Clique no ícone/botão SAIR"
                            + " para voltar ao Menu principal. \nEntão"
                            + " clique no X para sair ou no menu Arquivo opção Sair");
                }
            }
        });

        miSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //BD.getInstance().close();
                System.exit(0);
            }
        });

        miProdutosMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bloqueiaMenu();
                GuiCadastroProdutosMP label = new GuiCadastroProdutosMP();
                contentPane.removeAll();
                contentPane.add(label);
                contentPane.validate();
            }
        });

        miUsuarios2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bloqueiaMenu();
                GuiCadastroUsuarios2 label = new GuiCadastroUsuarios2();
                contentPane.removeAll();
                contentPane.add(label);
                contentPane.validate();
            }
        });

        miPedidoClienteMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //bloqueiaMenu();
                //GuiPedidoClienteMP label = new GuiPedidoClienteMP();
                //contentPane.removeAll();
                //contentPane.add(label);
                //contentPane.validate();
            }
        });

        miPedidoFornecedorMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //bloqueiaMenu();
                //GuiPedidoFornecedorMP label = new GuiPedidoFornecedorMP();
                //contentPane.removeAll();
                //contentPane.add(label);
                //contentPane.validate();
            }
        });

        miRelatorioProdutosMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //bloqueiaMenu();
                //ImprimeProdutosMP label = new ImprimeProdutosMP();
                //contentPane.removeAll();
                //contentPane.add(label);
                //contentPane.validate();
            }
        });

        miConsultaProdutosMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //bloqueiaMenu();
                //ConsultaProdutos label = new ConsultaProdutos();
                //contentPane.removeAll();
                //contentPane.add(label);
                //contentPane.validate();
            }
        });

    }

    private void bloqueiaMenu() {
        mnArquivos.setEnabled(false);
        mnPedidos.setEnabled(false);
        mnConsultas.setEnabled(false);
        mnRelatorios.setEnabled(false);
    }

    public static void liberaMenu() {
        mnArquivos.setEnabled(true);
        mnPedidos.setEnabled(true);
        mnConsultas.setEnabled(true);
        mnRelatorios.setEnabled(true);
    }

    public static void abrir(Usuarios2 aUsuario) {
        GuiMenuPrincipal frame = new GuiMenuPrincipal(aUsuario);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((tela.width - frame.getSize().width) / 2,
                (tela.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }

    private void travaUsuario(Usuarios2 aUsuario) {
        miProdutosMP.setEnabled(aUsuario.isCadastroProdutosMP());
        miForProdutoMP.setEnabled(aUsuario.isCadastroForProdutoMP());
        if (aUsuario.getNome().equals("java")) {
            miUsuarios2.setEnabled(true);
        } else {
            miUsuarios2.setEnabled(false);
        }
        miPedidoClienteMP.setEnabled(aUsuario.isPedidoClienteMP());
        miPedidoFornecedorMP.setEnabled(aUsuario.isPedidoFornecedorMP());
        miOrdemProducao.setEnabled(aUsuario.isOrdemProducao());
        miComposicaoProduto.setEnabled(aUsuario.isComposicaoProduto());
        miConsultaProdutosMP.setEnabled(aUsuario.isConsultaProdutoMP());
        miRelatorioProdutosMP.setEnabled(aUsuario.isRelatorioProdutoMP());
    }

}
