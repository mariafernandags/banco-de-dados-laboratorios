package com.example.academia.DAO;
import java.util.List;

import com.example.academia.model.Aluno;
import jakarta.persistence.EntityManager;

public class AlunoDAO 
{
    private final EntityManager em;

    public AlunoDAO(EntityManager em)
    {
        this.em = em;
    }

    public List<Aluno> buscarAlunosPorCidade(String cidadeEsp)
    {
        String consulta =
        """
            SELECT a
            FROM Aluno a
            WHERE a.cidade = :cidadeEsp       
        """;

        return em.createQuery(consulta, Aluno.class)
            .setParameter("cidadeEsp", cidadeEsp)
            .getResultList();
    }

    public Aluno buscarPorId (Integer id)
    {
        return em.find(Aluno.class, id);
    }

    public void salvar(Aluno aluno)
    {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void remover(Aluno aluno)
    {
        em.getTransaction().begin();
        em.remove(aluno);
        em.getTransaction().commit();
    }
}
