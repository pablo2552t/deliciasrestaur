package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.Cajero;
import java.util.List;
import java.util.Optional;

public interface ICajeroRepositorio {
    List<Cajero> obtenerTodos();
    Optional<Cajero> obtenerPorId(Long id);
    Cajero guardar(Cajero cajero);
    void eliminar(Long id);
}