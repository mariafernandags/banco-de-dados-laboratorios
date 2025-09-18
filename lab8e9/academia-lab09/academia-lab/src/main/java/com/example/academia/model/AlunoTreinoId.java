package com.example.academia.model;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTreinoId implements Serializable 
{
    private Integer aluno;
    private Integer treino;
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (!(o instanceof AlunoTreinoId)) return false;
        AlunoTreinoId that = (AlunoTreinoId) o;
        return Objects.equals(aluno, that.aluno) &&
        Objects.equals(treino, that.treino);
    }
    @Override
    public int hashCode() 
    {
        return Objects.hash(aluno, treino);
    }
}
