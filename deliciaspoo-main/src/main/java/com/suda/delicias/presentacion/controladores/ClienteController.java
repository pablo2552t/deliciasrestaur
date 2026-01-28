package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.ClienteService;
import com.suda.delicias.dominio.entidades.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para Cliente
 * Endpoints para operaciones CRUD
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteRequest request) {
        Cliente cliente = new Cliente(
            null,
            request.getNombre(),
            request.getApellido(),
            request.getDireccion(),
            request.getEmail(),
            request.getTelefono(),
            request.getCi()
        );
        Cliente clienteCreado = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, 
                                                      @RequestBody ClienteRequest request) {
        return clienteService.buscarClientePorId(id)
                .map(clienteExistente -> {
                    Cliente clienteActualizado = new Cliente(
                        id,
                        request.getNombre(),
                        request.getApellido(),
                        request.getDireccion(),
                        request.getEmail(),
                        request.getTelefono(),
                        request.getCi()
                    );
                    Cliente resultado = clienteService.actualizarCliente(clienteActualizado);
                    return new ResponseEntity<>(resultado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(cliente -> {
                    clienteService.eliminarCliente(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DTO para recibir datos de Cliente en las peticiones
     */
    public static class ClienteRequest {
        private String nombre;
        private String apellido;
        private String direccion;
        private String email;
        private String telefono;
        private String ci;

        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        
        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }
        
        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        
        public String getCi() { return ci; }
        public void setCi(String ci) { this.ci = ci; }
    }
}
