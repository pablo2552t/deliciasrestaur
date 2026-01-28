package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.CocineroService;
import com.suda.delicias.dominio.entidades.Cocinero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone endpoints HTTP para gestionar Cocineros.
 * Este es el punto de entrada del sistema desde el exterior.
 */
@RestController
@RequestMapping("/api/cocineros")
public class CocineroController {
    
    private final CocineroService service;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public CocineroController(CocineroService service) {
        this.service = service;
    }
    
    /**
     * GET /api/cocineros
     * Obtiene todos los cocineros.
     */
    @GetMapping
    public ResponseEntity<List<Cocinero>> obtenerTodos() {
        List<Cocinero> cocineros = service.obtenerTodos();
        return ResponseEntity.ok(cocineros);
    }
    
    /**
     * GET /api/cocineros/{id}
     * Obtiene un cocinero por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cocinero> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST /api/cocineros
     * Crea un nuevo cocinero.
     */
    @PostMapping
    public ResponseEntity<Cocinero> crear(@RequestBody CocineroRequest request) {
        Cocinero cocinero = new Cocinero(
                null,  // El ID será generado por la BD
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getTelefono(),
                request.getEspecialidad(),
                request.getTurno()
        );
        Cocinero creado = service.crear(cocinero);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    /**
     * PUT /api/cocineros/{id}
     * Actualiza un cocinero existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cocinero> actualizar(
            @PathVariable Long id,
            @RequestBody CocineroRequest request
    ) {
        return service.obtenerPorId(id)
                .map(existente -> {
                    Cocinero actualizado = new Cocinero(
                            id,
                            request.getNombre(),
                            request.getApellido(),
                            request.getEmail(),
                            request.getTelefono(),
                            request.getEspecialidad(),
                            request.getTurno()
                    );
                    return ResponseEntity.ok(service.actualizar(id, actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * DELETE /api/cocineros/{id}
     * Elimina un cocinero.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(cocinero -> {
                    service.eliminar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // DTO para requests
    public static class CocineroRequest {
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private String especialidad;
        private String turno;
        
        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        
        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        
        public String getEspecialidad() { return especialidad; }
        public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
        
        public String getTurno() { return turno; }
        public void setTurno(String turno) { this.turno = turno; }
    }
}