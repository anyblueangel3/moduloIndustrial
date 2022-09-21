package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.FornecedorProdutoMP;
import model.Fornecedores;
import model.ProdutosMP;
import moduloIndustrial.BD;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class CadForProdutoMPDAO {

    public Fornecedores fornecedor;
    public FornecedorProdutoMP fornecProdutoMP;
    public ProdutosMP produto;
    ArrayList<Fornecedores> listaFornecedores;
    String sql;
    BD bd;
    PreparedStatement statement;
    ResultSet resultSet;

    public CadForProdutoMPDAO() {
        fornecedor = new Fornecedores();
        fornecProdutoMP = new FornecedorProdutoMP();
        produto = new ProdutosMP();
    }

    public ArrayList<Fornecedores> listarFornecedoresProdutoMP(ProdutosMP produtoMP) {
        listaFornecedores = new ArrayList<>();
        sql = "SELECT * FROM fornecedores AS f JOIN fornec_produtomp AS fp "
                + "ON f.id_cgc_cpf = fp.id_fornecedor WHERE fp.id_produtoMP = ?;";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, produtoMP.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fornecedor = new Fornecedores();
                fornecedor.setId_cgc_cpf(resultSet.getString(1));
                fornecedor.setNome_razao(resultSet.getString(3));
                fornecedor.setTelefone(resultSet.getString(5));
                fornecedor.setCidade(resultSet.getString(10));
                listaFornecedores.add(fornecedor);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar fornecedores! \n" + erro);
        } finally {
            BD.getInstance().close();
        }
        return listaFornecedores;
    }
    
    public boolean localizaFornecedor(String id_fornecedor) {
        sql = "SELECT * FROM fornecedores WHERE id_cgc_cpf = ?;";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, id_fornecedor);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                fornecedor.setNome_razao(resultSet.getString(3));
                return true;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao localizar fornecedor! \n" + erro);
        } finally {
            BD.getInstance().close();
        }
        return false;
    }
    
    public boolean isFornecedor(String id_produtoMP, String id_fornecedor) {
        sql = "SELECT * FROM fornec_produtomp WHERE id_produtoMP = ? "
                + "AND id_fornecedor = ?;";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, id_produtoMP);
            statement.setString(2, id_fornecedor);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao localizar fornecedor!\n" + erro);
            return true;
        } finally {
            BD.getInstance().close();
        }
        return false;
    }

    public boolean gravarFornecedor() {
        sql = "INSERT INTO fornec_produtomp (id_produtoMP, id_fornecedor, preco,"
                + " data_compra, data_cadastro) VALUES (?, ?, ?, ?, ?);";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, fornecProdutoMP.getId_produtoMP());
            statement.setString(2, fornecProdutoMP.getId_fornecedor());
            statement.setDouble(3, fornecProdutoMP.getPreco());
            statement.setDate(4, fornecProdutoMP.getData_compra());
            statement.setDate(5, fornecProdutoMP.getData_cadastro());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor associado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir associação!\n" + erro);
            return false;
        } finally {
            BD.getInstance().close();
        }
        return true;
    }
    
    public boolean alterarFornecedor() {
        sql = "UPDATE fornec_produtomp SET "
                + "id_fornecedor = ?, "
                + "preco = ?, "
                + "data_compra = ?, "
                + "data_cadastro = ? "
                + "WHERE id = ?";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, fornecProdutoMP.getId_fornecedor());
            statement.setDouble(2, fornecProdutoMP.getPreco());
            statement.setDate(3, fornecProdutoMP.getData_compra());
            statement.setDate(4, fornecProdutoMP.getData_cadastro());
            statement.setInt(5, fornecProdutoMP.getId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor associado com sucesso!");            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar associação!\n" + erro);
            return false;
        } finally {
            BD.getInstance().close();
        }
        return true;
    }
    
    public boolean carregaAssociacao(String id_produtoMP, String id_fornecedor) {
        sql = "SELECT * FROM fornec_produtomp WHERE id_produtoMP = ? "
                + "AND id_fornecedor = ?";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, id_produtoMP);
            statement.setString(2, id_fornecedor);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                fornecProdutoMP.setId(resultSet.getInt(1));
                fornecProdutoMP.setId_produtoMP(resultSet.getString(2));
                fornecProdutoMP.setId_fornecedor(resultSet.getString(3));
                fornecProdutoMP.setPreco(resultSet.getDouble(4));
                fornecProdutoMP.setData_compra(resultSet.getDate(5));
                fornecProdutoMP.setData_cadastro(resultSet.getDate(6));
                return true;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao procurar associação!\n" + erro);
        } finally {
            BD.getInstance().close();
        }
        return false;
    }
    
    public boolean isOutroFornecedor(int id_cadastro, String id_produto, String id_fornecedor) {
        sql = "SELECT id_fornecedor FROM fornec_produtomp "
                + "WHERE id_produtoMP = ? "
                + "AND id_fornecedor = ? "
                + "AND id != ?";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, id_produto);
            statement.setString(2, id_fornecedor);
            statement.setInt(3, id_cadastro);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao procurar associação!\n" + erro);
        } finally {
            BD.getInstance().close();
        }
        return false;
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
*/
