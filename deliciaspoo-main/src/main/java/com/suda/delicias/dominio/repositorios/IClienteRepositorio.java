package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.Cliente;
import java.util.List;
import java.util.Optional;

/**
 * Interface de repositorio de dominio para Cliente
 * Define los métodos que se van a implementar en la capa de infraestructura
 * Permite gestionar la entidad Cliente
 */
public interface IClienteRepositorio {
    
    Cliente guardar(Cliente cliente);
    
    Optional<Cliente> buscarPorId(Long id);
    
    List<Cliente> listarTodos();
    
    void eliminar(Long id);
}
