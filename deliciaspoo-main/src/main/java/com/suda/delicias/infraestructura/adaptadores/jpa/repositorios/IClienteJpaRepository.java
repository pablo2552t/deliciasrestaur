package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para ClienteJpaEntity
 * Extiende JpaRepository de Spring Data
 */
@Repository
public interface IClienteJpaRepository extends JpaRepository<ClienteJpaEntity, Long> {
}
