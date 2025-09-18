package com.example.academia.DAO;
import com.example.academia.model.Treinador;
import jakarta.persistence.EntityManager;

public class TreinadorDAO 
{
    private final EntityManager em;
    public TreinadorDAO(EntityManager em) 
    {
        this.em = em;
    }

    public Treinador buscarPorId(Integer id) 
    {
        return em.find(Treinador.class, id);
    }

    public void salvar(Treinador treinador) 
    {
        em.getTransaction().begin();
        em.persist(treinador);
        em.getTransaction().commit();
    }
    
    public void remover(Treinador treinador) 
    {
        em.getTransaction().begin();
        em.remove(treinador);
        em.getTransaction().commit();
    }
}