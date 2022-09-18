package model;

/**
 *
 * @author Ronaldo Rodrigues Godoi
 */
public class Usuarios2 {
    
    private int id;
    
    private String nome, senha;
    
    private boolean cadastroProdutosMP, cadastroForProdutoMP, pedidoClienteMP,
            pedidoFornecedorMP, composicaoProduto, ordemProducao, consultaProdutoMP,
            relatorioProdutoMP;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isCadastroProdutosMP() {
        return cadastroProdutosMP;
    }

    public void setCadastroProdutosMP(boolean cadastroProdutosMP) {
        this.cadastroProdutosMP = cadastroProdutosMP;
    }

    public boolean isCadastroForProdutoMP() {
        return cadastroForProdutoMP;
    }

    public void setCadastroForProdutoMP(boolean cadastroForProdutoMP) {
        this.cadastroForProdutoMP = cadastroForProdutoMP;
    }

    public boolean isPedidoClienteMP() {
        return pedidoClienteMP;
    }

    public void setPedidoClienteMP(boolean pedidoClienteMP) {
        this.pedidoClienteMP = pedidoClienteMP;
    }

    public boolean isPedidoFornecedorMP() {
        return pedidoFornecedorMP;
    }

    public void setPedidoFornecedorMP(boolean pedidoFornecedorMP) {
        this.pedidoFornecedorMP = pedidoFornecedorMP;
    }

    public boolean isComposicaoProduto() {
        return composicaoProduto;
    }

    public void setComposicaoProduto(boolean composicaoProduto) {
        this.composicaoProduto = composicaoProduto;
    }

    public boolean isOrdemProducao() {
        return ordemProducao;
    }

    public void setOrdemProducao(boolean ordemProducao) {
        this.ordemProducao = ordemProducao;
    }

    public boolean isConsultaProdutoMP() {
        return consultaProdutoMP;
    }

    public void setConsultaProdutoMP(boolean consultaProdutoMP) {
        this.consultaProdutoMP = consultaProdutoMP;
    }

    public boolean isRelatorioProdutoMP() {
        return relatorioProdutoMP;
    }

    public void setRelatorioProdutoMP(boolean relatorioProdutoMP) {
        this.relatorioProdutoMP = relatorioProdutoMP;
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