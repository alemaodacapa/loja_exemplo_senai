package br.senai.dao;

import br.senai.model.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoriaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(Categoria categoria) {
        entityManager.persist(categoria);
    }

    public Categoria atualizar(Categoria categoria) {
        return entityManager.merge(categoria);
    }

    public void remover(Long id) {
        Categoria categoria = entityManager.find(Categoria.class, id);
        entityManager.remove(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return entityManager.find(Categoria.class, id);
    }

    public List<Categoria> listarTodas() {
        return entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }
}

