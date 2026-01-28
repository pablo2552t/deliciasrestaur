package com.suda.delicias.dominio.entidades;

/**
 * Entidad de dominio que representa a un Cocinero del restaurante.
 * Esta clase es INMUTABLE (todos los campos son final).
 * NO tiene anotaciones de JPA porque pertenece al DOMINIO puro.
 */
public class Cocinero {
    
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String email;
    private final String telefono;
    private final String especialidad;
    private final String turno;
    
    /**
     * Constructor con todos los parámetros.
     * Los campos son final, por lo tanto se asignan una sola vez.
     */
    public Cocinero(
            Long id,
            String nombre,
            String apellido,
            String email,
            String telefono,
            String especialidad,
            String turno
    ) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.turno = turno;
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
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public String getTurno() {
        return turno;
    }
}