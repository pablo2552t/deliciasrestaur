package com.suda.delicias.dominio.entidades;

/**
 * Entidad de dominio que representa a un Administrador del restaurante.
 * Esta clase es INMUTABLE (todos los campos son final).
 * NO tiene anotaciones de JPA porque pertenece al DOMINIO puro.
 */
public class Administrador {
    
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String email;
    private final String telefono;
    private final String rol;
    
    /**
     * Constructor con todos los parámetros.
     * Los campos son final, por lo tanto se asignan una sola vez.
     */
    public Administrador(
            Long id,
            String nombre,
            String apellido,
            String email,
            String telefono,
            String rol
    ) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }
    
    // Solo getters (NO setters porque es inmutable)
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getRol() {
        return rol;
    }
}