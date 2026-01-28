package com.suda.delicias.infraestructura.adaptadores.jpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa la tabla "cocineros" en PostgreSQL.
 * Esta clase SÍ tiene anotaciones de JPA porque pertenece a la INFRAESTRUCTURA.
 * Usa Lombok para reducir código boilerplate.
 */
@Entity
@Table(name = "cocineros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocineroJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "telefono", length = 20)
    private String telefono;
    
    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;
    
    @Column(name = "turno", nullable = false, length = 50)
    private String turno;
}