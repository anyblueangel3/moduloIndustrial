package moduloIndustrial;

import dao.Usuarios2DAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Usuarios2;

/**
 *
 * @author Ronaldo R. Godoi
 */
public class ModuloIndustrial extends JFrame {
    
    private JTextField tfLogin;
    private JLabel lbSenha;
    private JLabel lbLogin;
    private JButton btLogar;
    private JButton btCancelar;
    private JPasswordField pfSenha;
    private static ModuloIndustrial frame;
    Usuarios2 aUsuario = new Usuarios2();
    private Usuarios2DAO usuarios;

    public ModuloIndustrial() {
        
        inicializarComponentes();
        definirEventos();
        
    }
    
    private void inicializarComponentes() {
        
        setTitle("Login no Sistema");
        setBounds(0, 0, 400, 200);
        setLayout(null);
        tfLogin = new JTextField(5);
        pfSenha = new JPasswordField(5);
        lbSenha = new JLabel("Senha: ");
        lbLogin = new JLabel("Login: ");
        btLogar = new JButton("Logar");
        btCancelar = new JButton("Cancelar");
        tfLogin.setBounds(100, 30, 120, 25);
        lbLogin.setBounds(30, 30, 80, 25);
        lbSenha.setBounds(30, 75, 80, 25);
        pfSenha.setBounds(100, 75, 120, 25);
        btLogar.setBounds(20, 120, 100, 25);
        btCancelar.setBounds(125, 120, 100, 25);
        add(tfLogin);
        add(lbSenha);
        add(lbLogin);
        add(btLogar);
        add(btCancelar);
        add(pfSenha);
        usuarios = new Usuarios2DAO();
    }
    
    private void definirEventos() {
        
        btLogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tfLogin.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Login não pode estar em branco!");
                    return;
                }
                String senha = String.valueOf(pfSenha.getPassword());
                if(tfLogin.getText().equals("java") && senha.equals("java")) {
                    declaraUsuario();
                    BD.getInstance().close();
                    frame.setVisible(false);
                    GuiMenuPrincipal.abrir(aUsuario); // Aqui vai o código para chamar o exemplo 8.3
                } else {
                    usuarios.usuario.setNome(tfLogin.getText());
                    usuarios.localizar();
                    if(usuarios.usuario.getNome().equals(tfLogin.getText()) && usuarios.usuario.getSenha().equals(senha)) {
                            declaraUsuario(usuarios.usuario);
                            BD.getInstance().close();
                            frame.setVisible(false);
                            GuiMenuPrincipal.abrir(aUsuario);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login ou senha incorretos!");
                    }
                }
            }
        });
        
        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }
    
    private void declaraUsuario() {
        aUsuario.setId(0);
        aUsuario.setNome("java");
        aUsuario.setSenha("java");
        aUsuario.setCadastroProdutosMP(false);
        aUsuario.setCadastroForProdutoMP(false);
        aUsuario.setPedidoClienteMP(false);
        aUsuario.setPedidoFornecedorMP(false);
        aUsuario.setComposicaoProduto(false);
        aUsuario.setOrdemProducao(false);
        aUsuario.setConsultaProdutoMP(false);
        aUsuario.setRelatorioProdutoMP(false);
    }
    
    private void declaraUsuario(Usuarios2 aUsuario) {
        this.aUsuario.setId(aUsuario.getId());
        this.aUsuario.setNome(aUsuario.getNome());
        this.aUsuario.setSenha(aUsuario.getSenha());
        this.aUsuario.setCadastroProdutosMP(aUsuario.isCadastroProdutosMP());
        this.aUsuario.setCadastroForProdutoMP(aUsuario.isCadastroForProdutoMP());
        this.aUsuario.setPedidoClienteMP(aUsuario.isPedidoClienteMP());
        this.aUsuario.setPedidoFornecedorMP(aUsuario.isPedidoFornecedorMP());
        this.aUsuario.setComposicaoProduto(aUsuario.isComposicaoProduto());
        this.aUsuario.setOrdemProducao(aUsuario.isOrdemProducao());
        this.aUsuario.setConsultaProdutoMP(aUsuario.isConsultaProdutoMP());
        this.aUsuario.setRelatorioProdutoMP(aUsuario.isRelatorioProdutoMP());
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() { 
            public void run() {
                frame = new ModuloIndustrial();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((tela.width - frame.getSize().width) / 2,
                        (tela.height - frame.getSize().height) / 2);
                frame.setVisible(true);
            }
        });
        
    }
    
}
