package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CocineroJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA para la entidad CocineroJpaEntity.
 * Spring Data genera automáticamente la implementación de este repositorio.
 */
public interface ICocineroJpaRepository extends JpaRepository<CocineroJpaEntity, Long> {
    // Spring Data proporciona automáticamente:
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    // - Y muchos más métodos
}