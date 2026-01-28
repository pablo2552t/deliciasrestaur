package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.CajeroService;
import com.suda.delicias.dominio.entidades.Cajero;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cajeros")
public class CajeroController {

    private final CajeroService cajeroService;

    public CajeroController(CajeroService cajeroService) {
        this.cajeroService = cajeroService;
    }

    @GetMapping
    public List<Cajero> obtenerCajeros() {
        return cajeroService.listarTodos();
    }

    @PostMapping
    public Cajero guardarCajero(@RequestBody Cajero cajero) {
        return cajeroService.crear(cajero);
    }
}