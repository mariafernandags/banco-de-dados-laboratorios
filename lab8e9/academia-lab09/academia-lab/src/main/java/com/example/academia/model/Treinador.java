package com.example.academia.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "treinador")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Treinador {
    @Id
    @Column(name = "id_treinador")
    private Integer id;
    private String nome;
    private String especialidade;
}
