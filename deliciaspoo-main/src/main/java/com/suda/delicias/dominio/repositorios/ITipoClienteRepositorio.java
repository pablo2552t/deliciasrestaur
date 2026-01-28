package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.TipoCliente;
import java.util.List;
import java.util.Optional;

/**
 * Interface de repositorio de dominio para TipoCliente
 * Define los métodos que se van a implementar en la capa de infraestructura
 * Permite gestionar la entidad TipoCliente
 */
public interface ITipoClienteRepositorio {
    
    TipoCliente guardar(TipoCliente cliente);
    
    Optional<TipoCliente> buscarPorId(int id);
    
    List<TipoCliente> listarTodos();
    
    void eliminar(int id);
}
