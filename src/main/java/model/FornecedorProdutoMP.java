package model;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class FornecedorProdutoMP {
    int id;
    String id_produtoMP, id_fornecedor;
    double preco;
    Date data_compra, data_cadastro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_produtoMP() {
        return id_produtoMP;
    }

    public void setId_produtoMP(String id_produtoMP) {
        this.id_produtoMP = id_produtoMP;
    }

    public String getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(String id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
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
