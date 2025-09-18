package com.example.academia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "controle_sistema")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ControleSistema 
{
    @Id
    @Column (name="id")
    private Integer id;
    private Integer total_matriculas_canceladas;
}