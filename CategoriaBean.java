package br.senai.controller;

import br.senai.dao.CategoriaDAO;
import br.senai.model.Categoria;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class CategoriaBean implements Serializable {

    @Inject
    private CategoriaDAO categoriaDAO;

    private Categoria categoria = new Categoria();
    private List<Categoria> categorias;

    public void salvar() {
        if (categoria.getId() == null) {
            categoriaDAO.salvar(categoria);
        } else {
            categoriaDAO.atualizar(categoria);
        }
        categoria = new Categoria();
        categorias = null;
    }

    public void editar(Categoria c) {
        this.categoria = c;
    }

    public void remover(Categoria c) {
        categoriaDAO.remover(c.getId());
        categorias = null;
    }

    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = categoriaDAO.listarTodas();
        }
        return categorias;
    }

    // Getters and Setters
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

