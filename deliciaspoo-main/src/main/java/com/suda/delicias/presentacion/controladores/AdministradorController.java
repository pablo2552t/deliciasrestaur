package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.AdministradorService;
import com.suda.delicias.dominio.entidades.Administrador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone endpoints HTTP para gestionar Administradores.
 * Este es el punto de entrada del sistema desde el exterior.
 */
@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {
    
    private final AdministradorService service;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public AdministradorController(AdministradorService service) {
        this.service = service;
    }
    
    /**
     * GET /api/administradores
     * Obtiene todos los administradores.
     */
    @GetMapping
    public ResponseEntity<List<Administrador>> obtenerTodos() {
        List<Administrador> administradores = service.obtenerTodos();
        return ResponseEntity.ok(administradores);
    }
    
    /**
     * GET /api/administradores/{id}
     * Obtiene un administrador por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST /api/administradores
     * Crea un nuevo administrador.
     */
    @PostMapping
    public ResponseEntity<Administrador> crear(@RequestBody AdministradorRequest request) {
        Administrador administrador = new Administrador(
                null,  // El ID será generado por la BD
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getTelefono(),
                request.getRol()
        );
        Administrador creado = service.crear(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    /**
     * PUT /api/administradores/{id}
     * Actualiza un administrador existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizar(
            @PathVariable Long id,
            @RequestBody AdministradorRequest request
    ) {
        return service.obtenerPorId(id)
                .map(existente -> {
                    Administrador actualizado = new Administrador(
                            id,
                            request.getNombre(),
                            request.getApellido(),
                            request.getEmail(),
                            request.getTelefono(),
                            request.getRol()
                    );
                    return ResponseEntity.ok(service.actualizar(id, actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * DELETE /api/administradores/{id}
     * Elimina un administrador.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(administrador -> {
                    service.eliminar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // DTO para requests
    public static class AdministradorRequest {
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private String rol;
        
        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        
        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        
        public String getRol() { return rol; }
        public void setRol(String rol) { this.rol = rol; }
    }
}