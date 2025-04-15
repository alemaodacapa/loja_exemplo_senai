package br.senai.dao;

import br.senai.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(Produto produto) {
        entityManager.persist(produto);
    }

    public Produto atualizar(Produto produto) {
        return entityManager.merge(produto);
    }

    public void remover(Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        if (produto != null) {
            entityManager.remove(produto);
        }
    }

    public Produto buscarPorId(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> listarTodos() {
        return entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    // Métodos adicionais de busca
    public List<Produto> buscarPorCategoria(Long categoriaId) {
        return entityManager.createQuery(
            "SELECT p FROM Produto p WHERE p.categoria.id = :categoriaId", Produto.class)
            .setParameter("categoriaId", categoriaId)
            .getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        return entityManager.createQuery(
            "SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(:nome)", Produto.class)
            .setParameter("nome", "%" + nome + "%")
            .getResultList();
    }
}

