package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.Cocinero;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) que define el contrato de persistencia para Cocinero.
 * El DOMINIO define QUÉ operaciones necesita, sin especificar CÓMO se implementan.
 * La INFRAESTRUCTURA implementará esta interfaz.
 */
public interface ICocineroRepositorio {
    
    /**
     * Obtiene todos los cocineros.
     */
    List<Cocinero> findAll();
    
    /**
     * Busca un cocinero por su ID.
     */
    Optional<Cocinero> findById(Long id);
    
    /**
     * Guarda un cocinero (crear o actualizar).
     */
    Cocinero save(Cocinero cocinero);
    
    /**
     * Elimina un cocinero por su ID.
     */
    void deleteById(Long id);
}