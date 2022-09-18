package dao;

import model.Categorias;
import moduloIndustrial.BD;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Categorias;

/**
 *
 * @author Ronaldo R. Godoi
 */
public class CatDAO {

    public Categorias categoria;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public CatDAO() {
        bd = BD.getInstance();
        categoria = new Categorias();
    }

    public boolean localizar() {
        sql = "select * from categorias where id = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, categoria.getId());
            resultSet = statement.executeQuery();
            resultSet.next();
            categoria.setId(resultSet.getString(1));
            categoria.setDescricao(resultSet.getString(2));
            categoria.setData_cadastro(resultSet.getString(3));
            return true;
        } catch (SQLException erro) {
            System.out.println("erro: " + erro.toString() + sql + categoria.getId());
            return false;
        }
    }

    public String atualizar(int operacao) {
        men = "Operação realizada com sucesso!";
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into categorias values (?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, categoria.getId());
                statement.setString(2, categoria.getDescricao());
                statement.setString(3, categoria.getData_cadastro());
            } else if (operacao == ALTERACAO) {
                sql = "update categorias set descricao = ?,"
                        + " data_cadastro = ? where id = ?";

                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, categoria.getDescricao());
                statement.setString(2, categoria.getData_cadastro());
                statement.setString(3, categoria.getId());
            } else if (operacao == EXCLUSAO) {
                sql = "delete from categorias where id = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, categoria.getId());
            }

            if (statement.executeUpdate() == 0) {
                men = "Falha na operação!";
            }

        } catch (SQLException erro) {
            men = "Falha na operação! " + erro.toString() + " " + sql;
        }

        return men;
    }

    public ArrayList<Categorias> listarCategorias() {
        ArrayList<Categorias> listaCategorias = new ArrayList<>();
        sql = "SELECT * FROM categorias ORDER BY descricao;";
        try {
            statement = bd.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                this.categoria = new Categorias();
                this.categoria.setId(resultSet.getString(1));
                this.categoria.setDescricao(resultSet.getString(2));
                this.categoria.setData_cadastro(resultSet.getString(3));
                listaCategorias.add(categoria);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o arquivo!\n" + e);
        }
        return listaCategorias;
    }

}

/*
Table: categorias
Columns:
id varchar(10) 
descricao varchar(60) 
data_cadastro datetime
 */
