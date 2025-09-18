package com.example.academia.DAO;
import com.example.academia.model.AlunoTreino;
import com.example.academia.model.AlunoTreinoId;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AlunoTreinoDAO 
{
    private final EntityManager em;
    public AlunoTreinoDAO(EntityManager em) 
    {
        this.em = em;
    }

    public List<Object[]> buscarTreinosPorAlunoId(Integer alunoid)
    {
        String consulta =
        """
            SELECT t.id, t.descricao, tr.nome
            FROM AlunoTreino at
            JOIN at.treino t
            JOIN t.treinador tr
            WHERE at.aluno.id = :alunoid       
        """;

        return em.createQuery(consulta, Object[].class)
            .setParameter("alunoid", alunoid)
            .getResultList();
    }

    public List<AlunoTreino> buscarTreinosPoralunoid(Integer alunoid)
    {
        String consulta =
        """
            SELECT at
            FROM AlunoTreino at
            WHERE at.aluno.id = :alunoid       
        """;

        return em.createQuery(consulta, AlunoTreino.class)
            .setParameter("alunoid", alunoid)
            .getResultList();
    }

    public AlunoTreino buscarPorId(AlunoTreinoId id) 
    {
        return em.find(AlunoTreino.class, id);
    }

    public void salvar(AlunoTreino at) 
    {
        em.getTransaction().begin();
        em.persist(at);
        em.getTransaction().commit();
    }

    public void remover(AlunoTreino at) 
    {
        em.getTransaction().begin();
        em.remove(at);
        em.getTransaction().commit();
    }
}