package com.example.academia.DAO;
import com.example.academia.model.ControleSistema;
import jakarta.persistence.EntityManager;

public class ControleSistemaDAO 
{
    private final EntityManager em;
    public ControleSistemaDAO(EntityManager em) 
    {
        this.em = em;
    }

    public ControleSistema buscarPorId(Integer id) 
    {
        return em.find(ControleSistema.class, id);
    }

    public void salvar(ControleSistema controleSistema) 
    {
        em.getTransaction().begin();
        em.persist(controleSistema);
        em.getTransaction().commit();
    }
    
    public void remover(ControleSistema controleSistema) 
    {
        em.getTransaction().begin();
        em.remove(controleSistema);
        em.getTransaction().commit();
    }
}