package com.suda.delicias.dominio.entidades;

public class Cajero {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private String turno; // Ejemplo: "Mañana", "Tarde", "Noche"
    private Boolean activo;

    // Constructor vacío
    public Cajero() {}

    // Constructor con todos los campos
    public Cajero(Long id, String nombre, String apellido, String dni, String correo, String turno, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
        this.turno = turno;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}