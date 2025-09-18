package com.example.academia.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "treino")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Treino 
{
    @Id
    @Column(name = "id_treino")
    private Integer id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_treinador")
    private Treinador treinador;
}
