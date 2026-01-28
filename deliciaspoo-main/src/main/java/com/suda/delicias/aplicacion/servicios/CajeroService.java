
package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.Cajero;
import com.suda.delicias.dominio.repositorios.ICajeroRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CajeroService {

    private final ICajeroRepositorio cajeroRepositorio;

    public CajeroService(ICajeroRepositorio cajeroRepositorio) {
        this.cajeroRepositorio = cajeroRepositorio;
    }

    public List<Cajero> listarTodos() {
        return cajeroRepositorio.obtenerTodos();
    }

    public Cajero crear(Cajero cajero) {
        return cajeroRepositorio.guardar(cajero);
    }
}