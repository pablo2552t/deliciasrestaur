package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.Cliente;
import com.suda.delicias.dominio.repositorios.IClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación para Cliente
 * Contiene la lógica de negocio
 */
@Service
public class ClienteService {
    
    private final IClienteRepositorio clienteRepositorio;

    public ClienteService(IClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepositorio.guardar(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepositorio.buscarPorId(id);
    }

    public List<Cliente> listarTodosLosClientes() {
        return clienteRepositorio.listarTodos();
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepositorio.guardar(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepositorio.eliminar(id);
    }
}
