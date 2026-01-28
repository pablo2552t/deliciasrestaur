# 🛠️ Guía Completa: Implementación del Rol ADMINISTRADOR

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

Esta guía te ayudará a implementar el rol de **Administrador** en el proyecto Delicias Restaurant siguiendo **exactamente la misma estructura** que el rol Cliente (ya implementado por Pablo).

### ⚠️ Reglas Importantes

✅ **SÍ HACER:**
- Crear archivos SOLO para Administrador
- Seguir la misma estructura que Cliente
- Hacer `git pull` antes de empezar
- Hacer commit y push cuando termines

❌ **NO HACER:**
- Modificar archivos de Cliente
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
│   │   ├── TipoCliente.java                ← Ya existe (NO TOCAR)
│   │   └── Administrador.java              ← TÚ CREAS ESTE ✅
│   │
│   └── repositorios/
│       ├── IClienteRepositorio.java        ← Ya existe (NO TOCAR)
│       ├── ITipoClienteRepositorio.java    ← Ya existe (NO TOCAR)
│       └── IAdministradorRepositorio.java  ← TÚ CREAS ESTE ✅
│
├── aplicacion/
│   └── servicios/
│       ├── ClienteService.java             ← Ya existe (NO TOCAR)
│       ├── TipoClienteService.java         ← Ya existe (NO TOCAR)
│       └── AdministradorService.java       ← TÚ CREAS ESTE ✅
│
├── infraestructura/
│   └── adaptadores/
│       ├── jpa/
│       │   ├── entidades/
│       │   │   ├── ClienteJpaEntity.java           ← Ya existe (NO TOCAR)
│       │   │   ├── TipoClienteJpaEntity.java       ← Ya existe (NO TOCAR)
│       │   │   └── AdministradorJpaEntity.java     ← TÚ CREAS ESTE ✅
│       │   │
│       │   └── repositorios/
│       │       ├── IClienteJpaRepository.java          ← Ya existe (NO TOCAR)
│       │       ├── ITipoClienteJpaRepository.java      ← Ya existe (NO TOCAR)
│       │       └── IAdministradorJpaRepository.java    ← TÚ CREAS ESTE ✅
│       │
│       ├── mapeadores/
│       │   ├── IClienteJpaMapper.java              ← Ya existe (NO TOCAR)
│       │   ├── ITipoClienteJpaMapper.java          ← Ya existe (NO TOCAR)
│       │   └── IAdministradorJpaMapper.java        ← TÚ CREAS ESTE ✅
│       │
│       └── repositorios/
│           ├── ClienteRepositorioImpl.java         ← Ya existe (NO TOCAR)
│           ├── TipoClienteRepositorioImpl.java     ← Ya existe (NO TOCAR)
│           └── AdministradorRepositorioImpl.java   ← TÚ CREAS ESTE ✅
│
└── presentacion/
    └── controladores/
        ├── ClienteController.java          ← Ya existe (NO TOCAR)
        ├── TipoClienteController.java      ← Ya existe (NO TOCAR)
        └── AdministradorController.java    ← TÚ CREAS ESTE ✅
```

---

## 📊 Diagrama de Arquitectura Hexagonal

```
┌─────────────────────────────────────────────────────────────────┐
│                         PRESENTACIÓN                            │
│  (API REST - Punto de entrada HTTP)                             │
│                                                                  │
│  AdministradorController.java                                   │
│  ├─ GET    /api/administradores                                 │
│  ├─ GET    /api/administradores/{id}                            │
│  ├─ POST   /api/administradores                                 │
│  ├─ PUT    /api/administradores/{id}                            │
│  └─ DELETE /api/administradores/{id}                            │
└──────────────────────┬──────────────────────────────────────────┘
                       │ Llama a ↓
┌──────────────────────▼──────────────────────────────────────────┐
│                         APLICACIÓN                               │
│  (Casos de Uso - Servicios de Negocio)                          │
│                                                                  │
│  AdministradorService.java                                      │
│  ├─ obtenerTodos()                                              │
│  ├─ obtenerPorId(id)                                            │
│  ├─ crear(administrador)                                        │
│  ├─ actualizar(id, administrador)                               │
│  └─ eliminar(id)                                                │
└──────────────────────┬──────────────────────────────────────────┘
                       │ Usa ↓
┌──────────────────────▼──────────────────────────────────────────┐
│                         DOMINIO                                  │
│  (Reglas de Negocio Puras - Núcleo del Sistema)                 │
│                                                                  │
│  Administrador.java (Entidad Inmutable)                         │
│  ├─ id: Long                                                    │
│  ├─ nombre: String                                              │
│  ├─ apellido: String                                            │
│  ├─ email: String                                               │
│  ├─ telefono: String                                            │
│  └─ rol: String                                                 │
│                                                                  │
│  IAdministradorRepositorio.java (Puerto - Interfaz)             │
│  ├─ findAll()                                                   │
│  ├─ findById(id)                                                │
│  ├─ save(administrador)                                         │
│  └─ deleteById(id)                                              │
└──────────────────────▲──────────────────────────────────────────┘
                       │ Implementado por ↑
┌──────────────────────┴──────────────────────────────────────────┐
│                      INFRAESTRUCTURA                             │
│  (Detalles Técnicos - Persistencia con PostgreSQL)              │
│                                                                  │
│  AdministradorJpaEntity.java                                    │
│  ├─ @Entity, @Table(name = "administradores")                   │
│  └─ Campos con @Column, @Id, etc.                               │
│                                                                  │
│  IAdministradorJpaRepository.java                               │
│  └─ extends JpaRepository<AdministradorJpaEntity, Long>         │
│                                                                  │
│  IAdministradorJpaMapper.java                                   │
│  ├─ toDomain(entity) → Administrador                            │
│  └─ toEntity(administrador) → AdministradorJpaEntity            │
│                                                                  │
│  AdministradorRepositorioImpl.java (Adaptador)                  │
│  └─ Implementa IAdministradorRepositorio usando JPA             │
└─────────────────────────────────────────────────────────────────┘

BASE DE DATOS: PostgreSQL
Tabla: administradores
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

**Archivo:** `src/main/java/com/suda/delicias/dominio/entidades/Administrador.java`

```java
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
```

**✅ Qué hace este archivo:**
- Define QUÉ es un Administrador en términos de negocio
- Es un POJO puro (sin anotaciones de Spring/JPA)
- Inmutable (campos `final`, solo getters)

---

## 🔌 Paso 3: Crear Interfaz del Repositorio

**Archivo:** `src/main/java/com/suda/delicias/dominio/repositorios/IAdministradorRepositorio.java`

```java
package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.Administrador;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) que define el contrato de persistencia para Administrador.
 * El DOMINIO define QUÉ operaciones necesita, sin especificar CÓMO se implementan.
 * La INFRAESTRUCTURA implementará esta interfaz.
 */
public interface IAdministradorRepositorio {
    
    /**
     * Obtiene todos los administradores.
     */
    List<Administrador> findAll();
    
    /**
     * Busca un administrador por su ID.
     */
    Optional<Administrador> findById(Long id);
    
    /**
     * Guarda un administrador (crear o actualizar).
     */
    Administrador save(Administrador administrador);
    
    /**
     * Elimina un administrador por su ID.
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

**Archivo:** `src/main/java/com/suda/delicias/aplicacion/servicios/AdministradorService.java`

```java
package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.Administrador;
import com.suda.delicias.dominio.repositorios.IAdministradorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación que implementa los casos de uso de Administrador.
 * Coordina la lógica de negocio usando el repositorio del dominio.
 */
@Service
public class AdministradorService {
    
    private final IAdministradorRepositorio repositorio;
    
    /**
     * Inyección de dependencias por constructor.
     * Spring inyectará automáticamente la implementación de IAdministradorRepositorio.
     */
    public AdministradorService(IAdministradorRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    /**
     * Caso de uso: Obtener todos los administradores.
     */
    public List<Administrador> obtenerTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Caso de uso: Obtener un administrador por ID.
     */
    public Optional<Administrador> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }
    
    /**
     * Caso de uso: Crear un nuevo administrador.
     */
    public Administrador crear(Administrador administrador) {
        return repositorio.save(administrador);
    }
    
    /**
     * Caso de uso: Actualizar un administrador existente.
     */
    public Administrador actualizar(Long id, Administrador administrador) {
        // Aquí podrías agregar lógica adicional si es necesario
        return repositorio.save(administrador);
    }
    
    /**
     * Caso de uso: Eliminar un administrador.
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

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/jpa/entidades/AdministradorJpaEntity.java`

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa la tabla "administradores" en PostgreSQL.
 * Esta clase SÍ tiene anotaciones de JPA porque pertenece a la INFRAESTRUCTURA.
 * Usa Lombok para reducir código boilerplate.
 */
@Entity
@Table(name = "administradores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorJpaEntity {
    
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
    
    @Column(name = "rol", nullable = false, length = 50)
    private String rol;
}
```

**✅ Qué hace este archivo:**
- Mapea a la tabla `administradores` en PostgreSQL
- Tiene anotaciones JPA (@Entity, @Table, @Column)
- Lombok genera getters, setters, constructores automáticamente

---

## 🔗 Paso 6: Crear Repositorio JPA

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/jpa/repositorios/IAdministradorJpaRepository.java`

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.AdministradorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA para la entidad AdministradorJpaEntity.
 * Spring Data genera automáticamente la implementación de este repositorio.
 */
public interface IAdministradorJpaRepository extends JpaRepository<AdministradorJpaEntity, Long> {
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

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/mapeadores/IAdministradorJpaMapper.java`

```java
package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Administrador;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.AdministradorJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct que convierte entre:
 * - Administrador (dominio) ↔ AdministradorJpaEntity (infraestructura)
 * 
 * MapStruct genera automáticamente la implementación de este mapper en tiempo de compilación.
 */
@Mapper(componentModel = "spring")
public interface IAdministradorJpaMapper {
    
    /**
     * Convierte de entidad JPA a entidad de dominio.
     */
    Administrador toDomain(AdministradorJpaEntity entity);
    
    /**
     * Convierte de entidad de dominio a entidad JPA.
     */
    AdministradorJpaEntity toEntity(Administrador administrador);
}
```

**✅ Qué hace este archivo:**
- Define conversiones entre dominio ↔ JPA
- MapStruct genera automáticamente el código de conversión
- `componentModel = "spring"` hace que sea un bean de Spring

---

## 🔌 Paso 8: Crear Adaptador de Repositorio

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/repositorios/AdministradorRepositorioImpl.java`

```java
package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Administrador;
import com.suda.delicias.dominio.repositorios.IAdministradorRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.IAdministradorJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.IAdministradorJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa IAdministradorRepositorio (del dominio)
 * usando tecnología JPA (infraestructura).
 * 
 * Este es el "pegamento" entre el dominio y la tecnología específica.
 */
@Repository
public final class AdministradorRepositorioImpl implements IAdministradorRepositorio {
    
    private final IAdministradorJpaRepository jpaRepository;
    private final IAdministradorJpaMapper mapper;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public AdministradorRepositorioImpl(
            IAdministradorJpaRepository jpaRepository,
            IAdministradorJpaMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public List<Administrador> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Administrador> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Administrador save(Administrador administrador) {
        var entity = mapper.toEntity(administrador);
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
- IMPLEMENTA la interfaz del dominio (IAdministradorRepositorio)
- USA tecnología JPA internamente
- CONVIERTE entre entidades de dominio y JPA usando el mapper
- Anotado con `@Repository`

---

## 🌐 Paso 9: Crear Controlador REST

**Archivo:** `src/main/java/com/suda/delicias/presentacion/controladores/AdministradorController.java`

```java
package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.AdministradorService;
import com.suda.delicias.dominio.entidades.Administrador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone endpoints HTTP para gestionar Administradores.
 * Este es el punto de entrada del sistema desde el exterior.
 */
@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {
    
    private final AdministradorService service;
    
    /**
     * Inyección de dependencias por constructor.
     */
    public AdministradorController(AdministradorService service) {
        this.service = service;
    }
    
    /**
     * GET /api/administradores
     * Obtiene todos los administradores.
     */
    @GetMapping
    public ResponseEntity<List<Administrador>> obtenerTodos() {
        List<Administrador> administradores = service.obtenerTodos();
        return ResponseEntity.ok(administradores);
    }
    
    /**
     * GET /api/administradores/{id}
     * Obtiene un administrador por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST /api/administradores
     * Crea un nuevo administrador.
     */
    @PostMapping
    public ResponseEntity<Administrador> crear(@RequestBody AdministradorRequest request) {
        Administrador administrador = new Administrador(
                null,  // El ID será generado por la BD
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getTelefono(),
                request.getRol()
        );
        Administrador creado = service.crear(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    /**
     * PUT /api/administradores/{id}
     * Actualiza un administrador existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizar(
            @PathVariable Long id,
            @RequestBody AdministradorRequest request
    ) {
        return service.obtenerPorId(id)
                .map(existente -> {
                    Administrador actualizado = new Administrador(
                            id,
                            request.getNombre(),
                            request.getApellido(),
                            request.getEmail(),
                            request.getTelefono(),
                            request.getRol()
                    );
                    return ResponseEntity.ok(service.actualizar(id, actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * DELETE /api/administradores/{id}
     * Elimina un administrador.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(administrador -> {
                    service.eliminar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // DTO para requests
    public static class AdministradorRequest {
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private String rol;
        
        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        
        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        
        public String getRol() { return rol; }
        public void setRol(String rol) { this.rol = rol; }
    }
}
```

**✅ Qué hace este archivo:**
- Expone endpoints REST (GET, POST, PUT, DELETE)
- Recibe peticiones HTTP y las delega al servicio
- Retorna respuestas HTTP con códigos de estado apropiados
- Usa un DTO interno (AdministradorRequest) para recibir datos

---

## ✅ Paso 10: Compilar y Probar

### 10.1. Compilar con Maven

Abre la terminal en la raíz del proyecto y ejecuta:

```bash
mvn clean compile
```

Esto compilará todo el proyecto y MapStruct generará automáticamente la implementación de `IAdministradorJpaMapper`.

### 10.2. Verificar que MapStruct generó el Mapper

Busca en:
```
target/generated-sources/annotations/com/suda/delicias/infraestructura/adaptadores/mapeadores/
```

Deberías ver: `IAdministradorJpaMapperImpl.java`

### 10.3. (Opcional) Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

### 10.4. (Opcional) Probar los Endpoints

Usa **Postman** o **cURL** para probar:

```bash
# Crear administrador
curl -X POST http://localhost:8080/api/administradores \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "María",
    "apellido": "González",
    "email": "maria@delicias.com",
    "telefono": "0999888777",
    "rol": "Administrador General"
  }'

# Listar todos
curl http://localhost:8080/api/administradores

# Obtener por ID
curl http://localhost:8080/api/administradores/1
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
git commit -m "Implementada estructura hexagonal para rol Administrador"
```

### 11.4. Subir a GitHub

```bash
git push origin main
```

---

## ✅ Checklist Final

Antes de subir a GitHub, verifica:

- [ ] Creaste los 8 archivos para Administrador
- [ ] NO modificaste archivos de Cliente
- [ ] El proyecto compila sin errores (`mvn clean compile`)
- [ ] MapStruct generó `IAdministradorJpaMapperImpl.java`
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

---

## 📞 Contacto

Si tienes dudas, contacta a Pablo (coordinador del proyecto).

**Repositorio:** https://github.com/pablo2552t/deliciaspoo

---

¡Éxito con la implementación! 🚀
