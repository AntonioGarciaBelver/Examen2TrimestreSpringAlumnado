package com.example.SpringHello.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
@Data
@Entity
@Table(name = "alumnado")
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Email")
    private String email;
    @Column(name = "Ad")
    private Double ad;
    @Column(name = "Di")
    private Double di;
}