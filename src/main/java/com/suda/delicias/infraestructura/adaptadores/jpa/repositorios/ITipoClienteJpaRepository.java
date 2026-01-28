package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.TipoClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para TipoClienteJpaEntity
 * Extiende JpaRepository de Spring Data
 */
@Repository
public interface ITipoClienteJpaRepository extends JpaRepository<TipoClienteJpaEntity, Integer> {
}
