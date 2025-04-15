package br.senai.controller;

import br.senai.dao.ProdutoDAO;
import br.senai.model.Produto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Arrays;

@Named
@RequestScoped
public class ProdutoBean implements Serializable {

    @Inject
    private ProdutoDAO produtoDAO;

    private Produto produto = new Produto();
    private List<Produto> produtos;

    public void salvar() {
        if (produto.getId() == null) {
            produtoDAO.salvar(produto);
        } else {
            produtoDAO.atualizar(produto);
        }
        // Reset produto and refresh list
        produto = new Produto();
        produtos = null;
    }

    public void editar(Produto p) {
        this.produto = p;
    }

    public void remover(Produto p) {
        produtoDAO.remover(p.getId());
        produtos = null;
    }

    public List<Produto> getProdutos() {
        if (produtos == null) {
            produtos = produtoDAO.listarTodos();
        }
        return produtos;
    }

    // Método para retornar voltagens disponíveis
    public List<Produto.Voltagem> getVoltagensDisponiveis() {
        return Arrays.asList(Produto.Voltagem.values());
    }

    // Getters and Setters
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

