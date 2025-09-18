package com.example.academia.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Table(name = "aluno_treino")
@IdClass(AlunoTreinoId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AlunoTreino 
{
    @Id
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_treino")
    private Treino treino;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
}