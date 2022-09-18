package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Fornecedores;
import model.ProdutosMP;
import moduloIndustrial.BD;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class CadForProdutoMPDAO {
    Fornecedores fornecedor;
    ArrayList<Fornecedores> listaFornecedores;
    String sql;
    BD bd;
    PreparedStatement statement;
    ResultSet resultSet;

    public CadForProdutoMPDAO() {
        ProdutosMP produto;
    }
    
    public ArrayList<Fornecedores> listarFornecedoresProdutoMP(ProdutosMP produtoMP) {
        listaFornecedores = new ArrayList<>();
        sql = "SELECT * FROM fornecedores AS f JOIN fornec_produtomp AS fp "
                + "ON f.id_cgc_cpf = ?";
        try {
            bd = BD.getInstance();
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, produtoMP.getId());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                fornecedor = new Fornecedores();
                fornecedor.setId_cgc_cpf(resultSet.getString(1));
                fornecedor.setNome_razao(resultSet.getString(3));
                fornecedor.setTelefone(resultSet.getString(5));
                fornecedor.setCidade(resultSet.getString(10));
                listaFornecedores.add(fornecedor);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar fornecedores! \n" +  erro);
        } finally {
            BD.getInstance().close();
        }
        return listaFornecedores;
    }
        
    
}
