package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Usuarios2;
import moduloIndustrial.BD;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class Usuarios2DAO {

    public Usuarios2 usuario;
    BD bd;
    PreparedStatement statement;
    ResultSet resultSet;
    String sql, men;

    public Usuarios2DAO() {
        usuario = new Usuarios2();
    }

    public boolean localizar() {
        bd = BD.getInstance();
        sql = "SELECT * FROM usuarios2 WHERE nome = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(usuario.getNome()));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario.setId(resultSet.getInt(1));
                usuario.setNome(resultSet.getString(2));
                usuario.setSenha(resultSet.getString(3));
                usuario.setCadastroProdutosMP(resultSet.getBoolean(4));
                usuario.setCadastroForProdutoMP(resultSet.getBoolean(5));
                usuario.setPedidoClienteMP(resultSet.getBoolean(6));
                usuario.setPedidoFornecedorMP(resultSet.getBoolean(7));
                usuario.setComposicaoProduto(resultSet.getBoolean(8));
                usuario.setOrdemProducao(resultSet.getBoolean(9));
                usuario.setConsultaProdutoMP(resultSet.getBoolean(10));
                usuario.setRelatorioProdutoMP(resultSet.getBoolean(11));
            } else {
                return false;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro no SELECT!!! \n" + erro.toString());
        } finally {
            BD.getInstance().close();
        }
        return true;
    }

    public String MPInclusao() {
        bd = BD.getInstance();
        men = "Inclusao de usuário efetuada com sucesso!";
        sql = "INSERT INTO usuarios2 (nome, "
                + " senha,"
                + " cadastroProdutosMP,"
                + " cadastroFornecedoresProdutoMP,"
                + " pedidoClienteMP,"
                + " pedidoFornecedorMP,"
                + " composicaoProduto,"
                + " ordemProducao,"
                + " consultaProdutoMP,"
                + " relatorioProdutoMP)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setBoolean(3, usuario.isCadastroProdutosMP());
            statement.setBoolean(4, usuario.isCadastroForProdutoMP());
            statement.setBoolean(5, usuario.isPedidoClienteMP());
            statement.setBoolean(6, usuario.isPedidoFornecedorMP());
            statement.setBoolean(7, usuario.isComposicaoProduto());
            statement.setBoolean(8, usuario.isOrdemProducao());
            statement.setBoolean(9, usuario.isConsultaProdutoMP());
            statement.setBoolean(10, usuario.isRelatorioProdutoMP());
            statement.executeUpdate();
        } catch (Exception erro) {
            men = "Erro no INSERT!!! \n" + erro.toString();
        } finally {
            BD.getInstance().close();
        }
        return men;
    }

    public String MPAlterar() {
        bd = BD.getInstance();
        men = "Alteração de usuário efetuada com sucesso!";
        sql = "UPDATE usuarios2 SET nome = ?,"
                    + " senha = ?,"
                    + " cadastroProdutosMP = ?,"
                    + " cadastroFornecedoresProdutoMP = ?,"
                    + " pedidoClienteMP = ?,"
                    + " pedidoFornecedorMP = ?,"
                    + " composicaoProduto = ?,"
                    + " ordemProducao = ?,"
                    + " consultaProdutoMP = ?,"
                    + " relatorioProdutoMP = ?"
                    + " where id = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setBoolean(3, usuario.isCadastroProdutosMP());
            statement.setBoolean(4, usuario.isCadastroForProdutoMP());
            statement.setBoolean(5, usuario.isPedidoClienteMP());
            statement.setBoolean(6, usuario.isPedidoFornecedorMP());
            statement.setBoolean(7, usuario.isComposicaoProduto());
            statement.setBoolean(8, usuario.isOrdemProducao());
            statement.setBoolean(9, usuario.isConsultaProdutoMP());
            statement.setBoolean(10, usuario.isRelatorioProdutoMP());
            statement.setInt(11, usuario.getId());
            statement.executeUpdate();
        } catch (Exception erro) {
            men = "Erro na alteração de usuário!!! \n" + erro.toString();
        } finally {
            BD.getInstance().close();
        }
        return men;        
    }
    
    public String MPExcluir() {
        men = "Exclusão de usuário efetuada com sucesso!";
        sql = "DELETE FROM usuarios2 WHERE id = ?";
        bd = BD.getInstance();
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.executeUpdate();
        } catch (Exception erro) {
            men = "Erro ao excluir usuário! \n" + erro.toString();
        } finally {
            BD.getInstance().close();
        }
        return men;
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
