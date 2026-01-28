package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CajeroJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICajeroJpaRepository extends JpaRepository<CajeroJpaEntity, Long> {
    // Al extender de JpaRepository, ya tienes listos los métodos:
    // .save(), .findById(), .findAll(), .deleteById(), etc.
}