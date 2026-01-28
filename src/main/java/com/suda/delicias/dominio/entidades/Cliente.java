package com.suda.delicias.dominio.entidades;

/**
 * Entidad de dominio pura - Cliente
 * No depende de Spring, JPA ni Web
 * Atributos finales (inmutables)
 */
public class Cliente {
    
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String direccion;
    private final String email;
    private final String telefono;
    private final String ci;

    public Cliente(Long id, String nombre, String apellido, String direccion, 
                   String email, String telefono, String ci) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.ci = ci;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCi() {
        return ci;
    }
}
