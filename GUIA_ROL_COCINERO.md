# 🛠️ Guía Completa: Implementación del Rol COCINERO

**Proyecto:** Delicias Restaurant  
**Repositorio:** https://github.com/pablo2552t/deliciaspoo  
**Arquitectura:** Hexagonal (Puertos y Adaptadores)

---

## 📋 Tabla de Contenidos

1. [Introducción](#introducción)
2. [Estructura de Archivos](#estructura-de-archivos)
3. [Paso 1: Clonar el Repositorio](#paso-1-clonar-el-repositorio)
4. [Paso 2: Crear Entidad de Dominio](#paso-2-crear-entidad-de-dominio)
5. [Paso 3: Crear Interfaz del Repositorio](#paso-3-crear-interfaz-del-repositorio)
6. [Paso 4: Crear Servicio de Aplicación](#paso-4-crear-servicio-de-aplicación)
7. [Paso 5: Crear Entidad JPA](#paso-5-crear-entidad-jpa)
8. [Paso 6: Crear Repositorio JPA](#paso-6-crear-repositorio-jpa)
9. [Paso 7: Crear Mapper MapStruct](#paso-7-crear-mapper-mapstruct)
10. [Paso 8: Crear Adaptador de Repositorio](#paso-8-crear-adaptador-de-repositorio)
11. [Paso 9: Crear Controlador REST](#paso-9-crear-controlador-rest)
12. [Paso 10: Compilar y Probar](#paso-10-compilar-y-probar)
13. [Paso 11: Subir a GitHub](#paso-11-subir-a-github)

---

## 🎯 Introducción

Esta guía te ayudará a implementar el rol de **Cocinero** en el proyecto Delicias Restaurant siguiendo **exactamente la misma estructura** que los roles Cliente y Administrador (ya implementados).

### ⚠️ Reglas Importantes

✅ **SÍ HACER:**
- Crear archivos SOLO para Cocinero
- Seguir la misma estructura que Cliente y Administrador
- Hacer `git pull` antes de empezar
- Hacer commit y push cuando termines

❌ **NO HACER:**
- Modificar archivos de Cliente o Administrador
- Cambiar la estructura del proyecto
- Crear carpetas nuevas fuera del patrón
- Trabajar sin hacer `git pull` primero

---

## 📁 Estructura de Archivos

Vas a crear **8 archivos** siguiendo este patrón:

```
src/main/java/com/suda/delicias/
│
├── dominio/
│   ├── entidades/
│   │   ├── Cliente.java                    ← Ya existe (NO TOCAR)
│   │   ├── Administrador.java              ← Ya existe (NO TOCAR)
│   │   └── Cocinero.java                   ← TÚ CREAS ESTE ✅
│   │
│   └── repositorios/
│       ├── IClienteRepositorio.java        ← Ya existe (NO TOCAR)
│       ├── IAdministradorRepositorio.java  ← Ya existe (NO TOCAR)
│       └── ICocineroRepositorio.java       ← TÚ CREAS ESTE ✅
│
├── aplicacion/
│   └── servicios/
│       ├── ClienteService.java             ← Ya existe (NO TOCAR)
│       ├── AdministradorService.java       ← Ya existe (NO TOCAR)
│       └── CocineroService.java            ← TÚ CREAS ESTE ✅
│
├── infraestructura/
│   └── adaptadores/
│       ├── jpa/
│       │   ├── entidades/
│       │   │   ├── ClienteJpaEntity.java           ← Ya existe (NO TOCAR)
│       │   │   ├── AdministradorJpaEntity.java     ← Ya existe (NO TOCAR)
│       │   │   └── CocineroJpaEntity.java          ← TÚ CREAS ESTE ✅
│       │   │
│       │   └── repositorios/
│       │       ├── IClienteJpaRepository.java          ← Ya existe (NO TOCAR)
│       │       ├── IAdministradorJpaRepository.java    ← Ya existe (NO TOCAR)
│       │       └── ICocineroJpaRepository.java         ← TÚ CREAS ESTE ✅
│       │
│       ├── mapeadores/
│       │   ├── IClienteJpaMapper.java              ← Ya existe (NO TOCAR)
│       │   ├── IAdministradorJpaMapper.java        ← Ya existe (NO TOCAR)
│       │   └── ICocineroJpaMapper.java             ← TÚ CREAS ESTE ✅
│       │
│       └── repositorios/
│           ├── ClienteRepositorioImpl.java         ← Ya existe (NO TOCAR)
│           ├── AdministradorRepositorioImpl.java   ← Ya existe (NO TOCAR)
│           └── CocineroRepositorioImpl.java        ← TÚ CREAS ESTE ✅
│
└── presentacion/
    └── controladores/
        ├── ClienteController.java          ← Ya existe (NO TOCAR)
        ├── AdministradorController.java    ← Ya existe (NO TOCAR)
        └── CocineroController.java         ← TÚ CREAS ESTE ✅
```

---

## 📊 Diagrama de Arquitectura Hexagonal

```
┌─────────────────────────────────────────────────────────────────┐
│                         PRESENTACIÓN                            │
│  (API REST - Punto de entrada HTTP)                             │
│                                                                  │
│  CocineroController.java                                        │
│  ├─ GET    /api/cocineros                                       │
│  ├─ GET    /api/cocineros/{id}                                  │
│  ├─ POST   /api/cocineros                                       │
│  ├─ PUT    /api/cocineros/{id}                                  │
│  └─ DELETE /api/cocineros/{id}                                  │
└──────────────────────┬──────────────────────────────────────────┘
                       │ Llama a ↓
┌──────────────────────▼──────────────────────────────────────────┐
│                         APLICACIÓN                               │
│  (Casos de Uso - Servicios de Negocio)                          │
│                                                                  │
│  CocineroService.java                                           │
│  ├─ obtenerTodos()                                              │
│  ├─ obtenerPorId(id)                                            │
│  ├─ crear(cocinero)                                             │
│  ├─ actualizar(id, cocinero)                                    │
│  └─ eliminar(id)                                                │
└──────────────────────┬──────────────────────────────────────────┘
                       │ Usa ↓
┌──────────────────────▼──────────────────────────────────────────┐
│                         DOMINIO                                  │
│  (Reglas de Negocio Puras - Núcleo del Sistema)                 │
│                                                                  │
│  Cocinero.java (Entidad Inmutable)                              │
│  ├─ id: Long                                                    │
│  ├─ nombre: String                                              │
│  ├─ apellido: String                                            │
│  ├─ email: String                                               │
│  ├─ telefono: String                                            │
│  ├─ especialidad: String                                        │
│  └─ turno: String                                               │
│                                                                  │
│  ICocineroRepositorio.java (Puerto - Interfaz)                  │
│  ├─ findAll()                                                   │
│  ├─ findById(id)                                                │
│  ├─ save(cocinero)                                              │
│  └─ deleteById(id)                                              │
└──────────────────────▲──────────────────────────────────────────┘
                       │ Implementado por ↑
┌──────────────────────┴──────────────────────────────────────────┐
│                      INFRAESTRUCTURA                             │
│  (Detalles Técnicos - Persistencia con PostgreSQL)              │
│                                                                  │
│  CocineroJpaEntity.java                                         │
│  ├─ @Entity, @Table(name = "cocineros")                         │
│  └─ Campos con @Column, @Id, etc.                               │
│                                                                  │
│  ICocineroJpaRepository.java                                    │
│  └─ extends JpaRepository<CocineroJpaEntity, Long>              │
│                                                                  │
│  ICocineroJpaMapper.java                                        │
│  ├─ toDomain(entity) → Cocinero                                 │
│  └─ toEntity(cocinero) → CocineroJpaEntity                      │
│                                                                  │
│  CocineroRepositorioImpl.java (Adaptador)                       │
│  └─ Implementa ICocineroRepositorio usando JPA                  │
└─────────────────────────────────────────────────────────────────┘

BASE DE DATOS: PostgreSQL
Tabla: cocineros
```

---

## 🚀 Paso 1: Clonar el Repositorio

Abre **Git Bash** o **PowerShell** y ejecuta:

```bash
git clone https://github.com/pablo2552t/deliciaspoo.git
cd deliciaspoo
```

Si ya lo clonaste antes, asegúrate de tener los últimos cambios:

```bash
cd deliciaspoo
git pull origin main
```

---

## 📝 Paso 2: Crear Entidad de Dominio

**Archivo:** `src/main/java/com/suda/delicias/dominio/entidades/Cocinero.java`

```java
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
```

**✅ Qué hace este archivo:**
- Define QUÉ es un Cocinero en términos de negocio
- Es un POJO puro (sin anotaciones de Spring/JPA)
- Inmutable (campos `final`, solo getters)
- Incluye campos específicos del cocinero: `especialidad` (ej: "Parrillero", "Repostero") y `turno` (ej: "Mañana", "Tarde", "Noche")

---

## 🔌 Paso 3: Crear Interfaz del Repositorio

**Archivo:** `src/main/java/com/suda/delicias/dominio/repositorios/ICocineroRepositorio.java`

```java
package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.Cocinero;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) que define el contrato de persistencia para Cocinero.
 * El DOMINIO define QUÉ operaciones necesita, sin especificar CÓMO se implementan.
 * La INFRAESTRUCTURA implementará esta interfaz.
 */
public interface ICocineroRepositorio {
    
    /**
     * Obtiene todos los cocineros.
     */
    List<Cocinero> findAll();
    
    /**
     * Busca un cocinero por su ID.
     */
    Optional<Cocinero> findById(Long id);
    
    /**
     * Guarda un cocinero (crear o actualizar).
     */
    Cocinero save(Cocinero cocinero);
    
    /**
     * Elimina un cocinero por su ID.
     */
    void deleteById(Long id);
}
```

**✅ Qué hace este archivo:**
- Define el CONTRATO de persistencia (puerto)
- El dominio dice QUÉ necesita, no CÓMO se hace
- Será implementado por la infraestructura

---

## ⚙️ Paso 4: Crear Servicio de Aplicación

**Archivo:** `src/main/java/com/suda/delicias/aplicacion/servicios/CocineroService.java`

```java
package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.Cocinero;
import com.suda.delicias.dominio.repositorios.ICocineroRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación que implementa los casos de uso de Cocinero.
 * Coordina la lógica de negocio usando el repositorio del dominio.
 */
@Service
public class CocineroService {
    
    private final ICocineroRepositorio repositorio;
    
    /**
     * Inyección de dependencias por constructor.
     * Spring inyectará automáticamente la implementación de ICocineroRepositorio.
     */
    public CocineroService(ICocineroRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    /**
     * Caso de uso: Obtener todos los cocineros.
     */
    public List<Cocinero> obtenerTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Caso de uso: Obtener un cocinero por ID.
     */
    public Optional<Cocinero> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }
    
    /**
     * Caso de uso: Crear un nuevo cocinero.
     */
    public Cocinero crear(Cocinero cocinero) {
        return repositorio.save(cocinero);
    }
    
    /**
     * Caso de uso: Actualizar un cocinero existente.
     */
    public Cocinero actualizar(Long id, Cocinero cocinero) {
        // Aquí podrías agregar lógica adicional si es necesario
        return repositorio.save(cocinero);
    }
    
    /**
     * Caso de uso: Eliminar un cocinero.
     */
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
```

**✅ Qué hace este archivo:**
- Implementa los CASOS DE USO del negocio
- Orquesta llamadas al repositorio
- Anotado con `@Service` para que Spring lo gestione

---

## 🗄️ Paso 5: Crear Entidad JPA

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/jpa/entidades/CocineroJpaEntity.java`

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa la tabla "cocineros" en PostgreSQL.
 * Esta clase SÍ tiene anotaciones de JPA porque pertenece a la INFRAESTRUCTURA.
 * Usa Lombok para reducir código boilerplate.
 */
@Entity
@Table(name = "cocineros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocineroJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "telefono", length = 20)
    private String telefono;
    
    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;
    
    @Column(name = "turno", nullable = false, length = 50)
    private String turno;
}
```

**✅ Qué hace este archivo:**
- Mapea a la tabla `cocineros` en PostgreSQL
- Tiene anotaciones JPA (@Entity, @Table, @Column)
- Lombok genera getters, setters, constructores automáticamente
- Incluye campos específicos: `especialidad` y `turno`

---

## 🔗 Paso 6: Crear Repositorio JPA

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/jpa/repositorios/ICocineroJpaRepository.java`

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CocineroJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA para la entidad CocineroJpaEntity.
 * Spring Data genera automáticamente la implementación de este repositorio.
 */
public interface ICocineroJpaRepository extends JpaRepository<CocineroJpaEntity, Long> {
    // Spring Data proporciona automáticamente:
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    // - Y muchos más métodos
}
```

**✅ Qué hace este archivo:**
- Extiende JpaRepository de Spring Data
- Spring genera automáticamente la implementación SQL
- No necesitas escribir queries básicas

---

## 🔄 Paso 7: Crear Mapper MapStruct

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/mapeadores/ICocineroJpaMapper.java`

```java
package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Cocinero;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.CocineroJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct que convierte entre:
 * - Cocinero (dominio) ↔ CocineroJpaEntity (infraestructura)
 * 
 * MapStruct genera automáticamente la implementación de este mapper en tiempo de compilación.
 */
@Mapper(componentModel = "spring")
public interface ICocineroJpaMapper {
    
    /**
     * Convierte de entidad JPA a entidad de dominio.
     */
    Cocinero toDomain(CocineroJpaEntity entity);
    
    /**
     * Convierte de entidad de dominio a entidad JPA.
     */
    CocineroJpaEntity toEntity(Cocinero cocinero);
}
```

**✅ Qué hace este archivo:**
- Define conversiones entre dominio ↔ JPA
- MapStruct genera automáticamente el código de conversión
- `componentModel = "spring"` hace que sea un bean de Spring

---

## 🔌 Paso 8: Crear Adaptador de Repositorio

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/repositorios/CocineroRepositorioImpl.java`

```java
package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Cocinero;
import com.suda.delicias.dominio.repositorios.ICocineroRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.ICocineroJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.ICocineroJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa ICocineroRepositorio (del dominio)
 * usando tecnología JPA (infraestructura).
 * 
 * Este es el "pegamento" entre el dominio y la tecnología específica.
 */
@Repository
public final class CocineroRepositorioImpl implements ICocineroRepositorio {
    
    private final ICocineroJpaRepository jpaRepository;
    private final ICocineroJpaMapper mapper;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public CocineroRepositorioImpl(
            ICocineroJpaRepository jpaRepository,
            ICocineroJpaMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public List<Cocinero> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Cocinero> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Cocinero save(Cocinero cocinero) {
        var entity = mapper.toEntity(cocinero);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
```

**✅ Qué hace este archivo:**
- IMPLEMENTA la interfaz del dominio (ICocineroRepositorio)
- USA tecnología JPA internamente
- CONVIERTE entre entidades de dominio y JPA usando el mapper
- Anotado con `@Repository`

---

## 🌐 Paso 9: Crear Controlador REST

**Archivo:** `src/main/java/com/suda/delicias/presentacion/controladores/CocineroController.java`

```java
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
```

**✅ Qué hace este archivo:**
- Expone endpoints REST (GET, POST, PUT, DELETE)
- Recibe peticiones HTTP y las delega al servicio
- Retorna respuestas HTTP con códigos de estado apropiados
- Usa un DTO interno (CocineroRequest) para recibir datos

---

## ✅ Paso 10: Compilar y Probar

### 10.1. Compilar con Maven

Abre la terminal en la raíz del proyecto y ejecuta:

```bash
mvn clean compile
```

Esto compilará todo el proyecto y MapStruct generará automáticamente la implementación de `ICocineroJpaMapper`.

### 10.2. Verificar que MapStruct generó el Mapper

Busca en:
```
target/generated-sources/annotations/com/suda/delicias/infraestructura/adaptadores/mapeadores/
```

Deberías ver: `ICocineroJpaMapperImpl.java`

### 10.3. (Opcional) Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

### 10.4. (Opcional) Probar los Endpoints

Usa **Postman** o **cURL** para probar:

```bash
# Crear cocinero
curl -X POST http://localhost:8080/api/cocineros \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos",
    "apellido": "Ramírez",
    "email": "carlos@delicias.com",
    "telefono": "0987654321",
    "especialidad": "Parrillero",
    "turno": "Noche"
  }'

# Listar todos
curl http://localhost:8080/api/cocineros

# Obtener por ID
curl http://localhost:8080/api/cocineros/1

# Actualizar cocinero
curl -X PUT http://localhost:8080/api/cocineros/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos",
    "apellido": "Ramírez",
    "email": "carlos@delicias.com",
    "telefono": "0987654321",
    "especialidad": "Chef Ejecutivo",
    "turno": "Mañana"
  }'

# Eliminar cocinero
curl -X DELETE http://localhost:8080/api/cocineros/1
```

---

## 📤 Paso 11: Subir a GitHub

### 11.1. Verificar Cambios

```bash
git status
```

Deberías ver tus 8 archivos nuevos en rojo.

### 11.2. Agregar Archivos

```bash
git add .
```

### 11.3. Hacer Commit

```bash
git commit -m "Implementada estructura hexagonal para rol Cocinero"
```

### 11.4. Subir a GitHub

```bash
git push origin main
```

---

## ✅ Checklist Final

Antes de subir a GitHub, verifica:

- [ ] Creaste los 8 archivos para Cocinero
- [ ] NO modificaste archivos de Cliente o Administrador
- [ ] El proyecto compila sin errores (`mvn clean compile`)
- [ ] MapStruct generó `ICocineroJpaMapperImpl.java`
- [ ] Hiciste commit con mensaje descriptivo
- [ ] Hiciste push a GitHub

---

## 🆘 Solución de Problemas

### Problema: "MapStruct no genera el mapper"

**Solución:**
1. Ejecuta: `mvn clean compile`
2. Si persiste, verifica que `pom.xml` tenga la configuración de MapStruct
3. Busca en `target/generated-sources/annotations/`

### Problema: "Git dice que hay conflictos"

**Solución:**
```bash
git pull origin main
# Resolver conflictos manualmente si los hay
git add .
git commit -m "Resueltos conflictos"
git push origin main
```

### Problema: "No puedo compilar"

**Solución:**
1. Verifica que todos los archivos tengan los `package` correctos
2. Revisa que no haya errores de sintaxis
3. Ejecuta: `mvn clean compile`

### Problema: "Error al crear la tabla en PostgreSQL"

**Solución:**
Asegúrate de que exista la tabla `cocineros` en PostgreSQL. Puedes crearla con:

```sql
CREATE TABLE cocineros (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    especialidad VARCHAR(100) NOT NULL,
    turno VARCHAR(50) NOT NULL
);
```

---

## 📊 Resumen de Campos del Cocinero

| Campo | Tipo | Descripción | Ejemplo |
|-------|------|-------------|---------|
| `id` | Long | Identificador único | 1 |
| `nombre` | String | Nombre del cocinero | "Carlos" |
| `apellido` | String | Apellido del cocinero | "Ramírez" |
| `email` | String | Correo electrónico | "carlos@delicias.com" |
| `telefono` | String | Número de teléfono | "0987654321" |
| `especialidad` | String | Especialidad culinaria | "Parrillero", "Repostero", "Chef Ejecutivo" |
| `turno` | String | Turno de trabajo | "Mañana", "Tarde", "Noche" |

---

## 📞 Contacto

Si tienes dudas, contacta a Pablo (coordinador del proyecto).

**Repositorio:** https://github.com/pablo2552t/deliciaspoo

---

¡Éxito con la implementación! 🚀
