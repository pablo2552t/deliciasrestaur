package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.AdministradorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA para la entidad AdministradorJpaEntity.
 * Spring Data genera automáticamente la implementación de este repositorio.
 */
public interface IAdministradorJpaRepository extends JpaRepository<AdministradorJpaEntity, Long> {
    // Spring Data proporciona automáticamente:
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    // - Y muchos más métodos
}