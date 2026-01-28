package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.Administrador;
import com.suda.delicias.dominio.repositorios.IAdministradorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación que implementa los casos de uso de Administrador.
 * Coordina la lógica de negocio usando el repositorio del dominio.
 */
@Service
public class AdministradorService {
    
    private final IAdministradorRepositorio repositorio;
    
    /**
     * Inyección de dependencias por constructor.
     * Spring inyectará automáticamente la implementación de IAdministradorRepositorio.
     */
    public AdministradorService(IAdministradorRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    /**
     * Caso de uso: Obtener todos los administradores.
     */
    public List<Administrador> obtenerTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Caso de uso: Obtener un administrador por ID.
     */
    public Optional<Administrador> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }
    
    /**
     * Caso de uso: Crear un nuevo administrador.
     */
    public Administrador crear(Administrador administrador) {
        return repositorio.save(administrador);
    }
    
    /**
     * Caso de uso: Actualizar un administrador existente.
     */
    public Administrador actualizar(Long id, Administrador administrador) {
        // Aquí podrías agregar lógica adicional si es necesario
        return repositorio.save(administrador);
    }
    
    /**
     * Caso de uso: Eliminar un administrador.
     */
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}