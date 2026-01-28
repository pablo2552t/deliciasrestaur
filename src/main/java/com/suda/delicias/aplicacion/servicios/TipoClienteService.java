package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.TipoCliente;
import com.suda.delicias.dominio.repositorios.ITipoClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación para TipoCliente
 * Contiene la lógica de negocio
 */
@Service
public class TipoClienteService {
    
    private final ITipoClienteRepositorio tipoClienteRepositorio;

    public TipoClienteService(ITipoClienteRepositorio tipoClienteRepositorio) {
        this.tipoClienteRepositorio = tipoClienteRepositorio;
    }

    public TipoCliente crearTipoCliente(TipoCliente tipoCliente) {
        return tipoClienteRepositorio.guardar(tipoCliente);
    }

    public Optional<TipoCliente> buscarTipoClientePorId(int id) {
        return tipoClienteRepositorio.buscarPorId(id);
    }

    public List<TipoCliente> listarTodosTiposCliente() {
        return tipoClienteRepositorio.listarTodos();
    }

    public TipoCliente actualizarTipoCliente(TipoCliente tipoCliente) {
        return tipoClienteRepositorio.guardar(tipoCliente);
    }

    public void eliminarTipoCliente(int id) {
        tipoClienteRepositorio.eliminar(id);
    }
}
