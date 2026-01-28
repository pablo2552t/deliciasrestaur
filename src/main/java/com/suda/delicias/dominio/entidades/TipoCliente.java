package com.suda.delicias.dominio.entidades;

/**
 * Entidad de dominio pura - TipoCliente
 * No depende de Spring, JPA ni Web
 * Atributos finales (inmutables)
 */
public class TipoCliente {
    
    private final int idTipoCliente;
    private final String nombreTipo;
    private final String descripcion;
    private final boolean estado;

    public TipoCliente(int idTipoCliente, String nombreTipo, String descripcion, boolean estado) {
        this.idTipoCliente = idTipoCliente;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isEstado() {
        return estado;
    }
}
