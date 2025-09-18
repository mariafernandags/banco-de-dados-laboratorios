package com.example.academia.DAO;
import com.example.academia.model.Treino;
import jakarta.persistence.EntityManager;

public class TreinoDAO 
{
    private final EntityManager em;
    public TreinoDAO(EntityManager em) 
    {
        this.em = em;
    }

    public Treino buscarPorId(Integer id) 
    {
        return em.find(Treino.class, id);
    }

    public void salvar(Treino treino) 
    {
        em.getTransaction().begin();
        em.persist(treino);
        em.getTransaction().commit();
    }
    
    public void remover(Treino treino) 
    {
        em.getTransaction().begin();
        em.remove(treino);
        em.getTransaction().commit();
    }
}
