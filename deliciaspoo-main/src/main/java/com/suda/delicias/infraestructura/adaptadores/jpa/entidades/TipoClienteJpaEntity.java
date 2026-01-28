package com.suda.delicias.infraestructura.adaptadores.jpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para TipoCliente
 * Contiene todas las anotaciones de persistencia
 */
@Entity
@Table(name = "tipos_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoClienteJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoCliente;
    
    @Column(nullable = false, length = 50)
    private String nombreTipo;
    
    @Column(length = 200)
    private String descripcion;
    
    @Column(nullable = false)
    private Boolean estado;
}
