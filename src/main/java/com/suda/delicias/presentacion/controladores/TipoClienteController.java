package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.TipoClienteService;
import com.suda.delicias.dominio.entidades.TipoCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para TipoCliente
 * Endpoints para operaciones CRUD
 */
@RestController
@RequestMapping("/api/tipos-cliente")
public class TipoClienteController {
    
    private final TipoClienteService tipoClienteService;

    public TipoClienteController(TipoClienteService tipoClienteService) {
        this.tipoClienteService = tipoClienteService;
    }

    @PostMapping
    public ResponseEntity<TipoCliente> crearTipoCliente(@RequestBody TipoClienteRequest request) {
        TipoCliente tipoCliente = new TipoCliente(
            0,
            request.getNombreTipo(),
            request.getDescripcion(),
            request.isEstado()
        );
        TipoCliente tipoCreado = tipoClienteService.crearTipoCliente(tipoCliente);
        return new ResponseEntity<>(tipoCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCliente> obtenerTipoCliente(@PathVariable int id) {
        return tipoClienteService.buscarTipoClientePorId(id)
                .map(tipo -> new ResponseEntity<>(tipo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<TipoCliente>> listarTiposCliente() {
        List<TipoCliente> tipos = tipoClienteService.listarTodosTiposCliente();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCliente> actualizarTipoCliente(@PathVariable int id, 
                                                              @RequestBody TipoClienteRequest request) {
        return tipoClienteService.buscarTipoClientePorId(id)
                .map(tipoExistente -> {
                    TipoCliente tipoActualizado = new TipoCliente(
                        id,
                        request.getNombreTipo(),
                        request.getDescripcion(),
                        request.isEstado()
                    );
                    TipoCliente resultado = tipoClienteService.actualizarTipoCliente(tipoActualizado);
                    return new ResponseEntity<>(resultado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoCliente(@PathVariable int id) {
        return tipoClienteService.buscarTipoClientePorId(id)
                .map(tipo -> {
                    tipoClienteService.eliminarTipoCliente(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DTO para recibir datos de TipoCliente en las peticiones
     */
    public static class TipoClienteRequest {
        private String nombreTipo;
        private String descripcion;
        private boolean estado;

        // Getters y Setters
        public String getNombreTipo() { return nombreTipo; }
        public void setNombreTipo(String nombreTipo) { this.nombreTipo = nombreTipo; }
        
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        
        public boolean isEstado() { return estado; }
        public void setEstado(boolean estado) { this.estado = estado; }
    }
}
