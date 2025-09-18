package com.example.academia.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Aluno {
    @Id
    @Column(name = "id_aluno")
    private Integer id;
    private String nome;
    private String cidade;
    @Column(name = "data_ultima_matricula")
    private LocalDate dataUltimaMatircula;
}
