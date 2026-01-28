# Actividad 3 — Análisis de Arquitectura Limpia (Rol Administrador)

**Estudiante:** [Nombre del Estudiante]  
**Asignatura:** POO Avanzada  
**Tema:** Arquitectura Limpia - Rol Administrador  
**Fecha:** 27 de Enero, 2026  

---

## 📋 Introducción

Esta actividad analiza la implementación de **Arquitectura Limpia** aplicada específicamente al módulo de **Administrador** del proyecto Delicias Restaurant. Se responderán las preguntas fundamentales sobre por qué las entidades están en el centro, por qué la infraestructura no debe afectar al dominio, y cómo se relaciona el diagrama de arquitectura con la estructura real del proyecto.

---

## ❓ Pregunta 1: ¿Por qué las entidades están en el centro?

### Respuesta:

Las **entidades de dominio** están en el centro de la Arquitectura Limpia porque representan el **núcleo del negocio**, las reglas fundamentales que definen QUÉ es el sistema, independientemente de CÓMO se implementa técnicamente.

### Aplicación al Rol Administrador:

En el proyecto Delicias Restaurant, la entidad `Administrador` está ubicada en el centro (paquete `dominio.entidades`) porque:

#### 1. Representa un Concepto de Negocio Puro

```java
public class Administrador {
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String email;
    private final String telefono;
    private final String rol;
    // ...
}
```

**¿Qué dice este código?**

> "Un Administrador en el restaurante Delicias es una persona con nombre, apellido, email, teléfono y un rol específico dentro del sistema."

Esta definición es **eterna y universal**. Un administrador ES esto, sin importar:
- ❌ Si usamos PostgreSQL, MySQL, MongoDB o archivos de texto
- ❌ Si exponemos la información por REST, GraphQL, o SOAP
- ❌ Si usamos Spring Boot, Laravel, Django o .NET
- ❌ Si desplegamos en AWS, Azure, Google Cloud o on-premise

#### 2. Es Inmutable y Estable

La entidad `Administrador` tiene campos `final` (inmutables). Una vez creado un administrador, sus datos NO cambian de forma descontrolada. Esto representa una **regla de negocio**: la identidad de un administrador es fija.

```java
// Constructor - los campos se asignan UNA SOLA VEZ
public Administrador(Long id, String nombre, String apellido, ...) {
    this.id = id;           // FINAL - no se puede modificar después
    this.nombre = nombre;   // FINAL - estable
    // ...
}
```

#### 3. No Tiene Dependencias Externas

La clase `Administrador.java` NO importa:
- ❌ `jakarta.persistence.*` (JPA)
- ❌ `org.springframework.*` (Spring)
- ❌ `com.fasterxml.jackson.*` (JSON)
- ❌ Ninguna librería externa

Es un **POJO puro** (Plain Old Java Object). Esto significa que:
- ✅ Puedes testear las reglas de negocio sin Spring
- ✅ Puedes cambiar de framework sin tocar esta clase
- ✅ El conocimiento del negocio está protegido

### Conclusión:

Las entidades están en el centro porque son **LO MÁS IMPORTANTE** del sistema. El restaurante Delicias necesita administradores para funcionar, pero NO necesita específicamente PostgreSQL o Spring Boot. Las tecnologías son reemplazables, las reglas de negocio NO.

---

## ❓ Pregunta 2: ¿Por qué la infraestructura no debe afectar al dominio?

### Respuesta:

La **infraestructura es volátil** y cambia frecuentemente por razones técnicas externas al negocio (nuevas versiones de frameworks, migraciones de base de datos, cambios de arquitectura de despliegue, etc.). Si el dominio dependiera de la infraestructura, cada cambio técnico ROMPERÍA las reglas de negocio.

### Aplicación al Rol Administrador:

#### Escenario Problemático (SI el dominio dependiera de infraestructura):

Imagina que la clase `Administrador` tuviera anotaciones JPA:

```java
// ❌ MAL - Dominio contaminado con tecnología
@Entity
@Table(name = "administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    // ...
}
```

**Problemas que causaría:**

1. **Cambio de Base de Datos:**
   - Si migramos de PostgreSQL a MongoDB, tendríamos que CAMBIAR las anotaciones en `Administrador`
   - La definición de "qué es un administrador" NO debería cambiar por un cambio técnico

2. **Actualización de JPA:**
   - Si JPA 3.0 cambia sus anotaciones en JPA 4.0, tendríamos que modificar nuestra entidad de negocio
   - Las reglas del restaurante no cambian porque cambie una librería

3. **Testing Complejo:**
   - Para testear `Administrador`, necesitaríamos levantar toda la infraestructura JPA
   - No podrías hacer tests unitarios simples de las reglas de negocio

#### Solución Implementada (Separación dominio-infraestructura):

En nuestro proyecto, tenemos DOS representaciones de Administrador:

**1. Administrador (Dominio) - SIN dependencias:**

```java
package com.suda.delicias.dominio.entidades;

// ✅ BIEN - POJO puro sin anotaciones
public class Administrador {
    private final Long id;
    private final String nombre;
    // ... Solo lógica de negocio
}
```

**2. AdministradorJpaEntity (Infraestructura) - CON anotaciones JPA:**

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.entidades;

@Entity  // ← Anotación de JPA
@Table(name = "administradores")  // ← Tecnología específica
public class AdministradorJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ...
}
```

#### El Puente: Mapper MapStruct

```java
@Mapper(componentModel = "spring")
public interface IAdministradorJpaMapper {
    Administrador toDomain(AdministradorJpaEntity entity);
    AdministradorJpaEntity toEntity(Administrador administrador);
}
```

**¿Cómo funciona?**

```
Dominio (Administrador)
        ↕️ Mapper convierte
Infraestructura (AdministradorJpaEntity)
        ↕️ JPA persiste
Base de Datos PostgreSQL
```

#### Ventajas de esta Separación:

| Escenario | Con Separación | Sin Separación |
|-----------|----------------|----------------|
| Cambiar de PostgreSQL a MongoDB | ✅ Solo cambias infraestructura | ❌ Cambias entidad de negocio |
| Actualizar JPA | ✅ Solo cambias AdministradorJpaEntity | ❌ Cambias Administrador |
| Migrar a otro framework | ✅ Solo cambias adaptadores | ❌ Reescribes todo |
| Testear reglas de negocio | ✅ Test simple sin BD | ❌ Necesitas BD completa |

### Ejemplo Concreto:

**Regla de Negocio:** "Un administrador debe tener un email único"

**Dominio (Administrador):**
```java
// Validación de negocio
public Administrador(String email, ...) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Email inválido");
    }
    this.email = email;
}
```

**Infraestructura (AdministradorJpaEntity):**
```java
@Column(name = "email", unique = true)  // ← Restricción técnica de BD
private String email;
```

Si cambiamos de PostgreSQL a MongoDB:
- ✅ **Dominio:** NO cambia (la regla de negocio sigue igual)
- ⚙️ **Infraestructura:** Cambiamos `@Column` por anotaciones de MongoDB

### Conclusión:

La infraestructura NO debe afectar al dominio porque:
1. **Las tecnologías cambian**, las reglas de negocio NO
2. **El negocio es estable**, la infraestructura es volátil
3. **La lógica de negocio debe ser testeable** sin dependencias técnicas
4. **Protege la inversión** de conocimiento del dominio

---

## ❓ Pregunta 3: Relación del diagrama de Arquitectura Limpia con la estructura del proyecto

### Respuesta:

El diagrama de Arquitectura Limpia se representa como **círculos concéntricos**, donde las capas internas son estables y las externas son volátiles. El proyecto implementa exactamente este patrón.

### Diagrama de Arquitectura Limpia (Teórico)

```
┌──────────────────────────────────────────────┐
│  Frameworks & Drivers (Infraestructura)     │  ← Más volátil
│  ┌────────────────────────────────────────┐ │
│  │  Interface Adapters (Presentación)    │ │
│  │  ┌──────────────────────────────────┐ │ │
│  │  │  Use Cases (Aplicación)         │ │ │
│  │  │  ┌────────────────────────────┐ │ │ │
│  │  │  │  Entities (Dominio)        │ │ │ │  ← Más estable
│  │  │  │  • Administrador           │ │ │ │
│  │  │  │  • Reglas de negocio       │ │ │ │
│  │  │  └────────────────────────────┘ │ │ │
│  │  └──────────────────────────────────┘ │ │
│  └────────────────────────────────────────┘ │
└──────────────────────────────────────────────┘
```

### Correspondencia con el Proyecto (Rol Administrador)

| Capa de Arquitectura Limpia | Paquete en el Proyecto | Archivos de Administrador |
|-----------------------------|------------------------|---------------------------|
| **Entities (Centro)** | `dominio.entidades` | `Administrador.java` |
| **Gateways (Puertos)** | `dominio.repositorios` | `IAdministradorRepositorio.java` |
| **Use Cases** | `aplicacion.servicios` | `AdministradorService.java` |
| **Controllers (Adaptadores)** | `presentacion.controladores` | `AdministradorController.java` |
| **Frameworks & Drivers** | `infraestructura.adaptadores.*` | `AdministradorJpaEntity.java`<br>`IAdministradorJpaRepository.java`<br>`IAdministradorJpaMapper.java`<br>`AdministradorRepositorioImpl.java` |

### Flujo de Dependencias (Regla de Dependencia)

**La regla fundamental:** Las dependencias SOLO pueden apuntar hacia adentro (hacia el dominio).

#### Dependencias Correctas ✅:

```
AdministradorController (presentación)
        ↓ depende de
AdministradorService (aplicación)
        ↓ depende de
IAdministradorRepositorio (dominio - interfaz)
        ↑ implementado por
AdministradorRepositorioImpl (infraestructura)
```

#### Código Real:

**1. Presentación depende de Aplicación:**
```java
// AdministradorController.java
public class AdministradorController {
    private final AdministradorService service;  // ← Depende de aplicación
    
    @GetMapping
    public List<Administrador> obtenerTodos() {
        return service.obtenerTodos();
    }
}
```

**2. Aplicación depende de Dominio:**
```java
// AdministradorService.java
public class AdministradorService {
    private final IAdministradorRepositorio repositorio;  // ← Depende de interfaz del dominio
    
    public List<Administrador> obtenerTodos() {
        return repositorio.findAll();
    }
}
```

**3. Infraestructura implementa interfaz del Dominio:**
```java
// AdministradorRepositorioImpl.java
public class AdministradorRepositorioImpl implements IAdministradorRepositorio {
    // ↑ Implementa la interfaz del DOMINIO
    
    private final IAdministradorJpaRepository jpaRepository;
    private final IAdministradorJpaMapper mapper;
    
    @Override
    public List<Administrador> findAll() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomain)  // ← Convierte JPA → Dominio
            .collect(Collectors.toList());
    }
}
```

### Inversión de Dependencias (Clave de la Arquitectura)

**❌ Sin inversión (Acoplamiento directo):**
```
AdministradorService → AdministradorRepositorioImpl
    (Aplicación depende de implementación concreta)
```

**✅ Con inversión (Desacoplamiento):**
```
AdministradorService → IAdministradorRepositorio ← AdministradorRepositorioImpl
    (Aplicación depende de abstracción, infraestructura implementa)
```

**Ventaja:**
- Puedes cambiar `AdministradorRepositorioImpl` por `AdministradorMongoRepositoryImpl`
- El servicio NO cambia porque depende de la INTERFAZ

### Estructura Real vs Diagrama

```
DIAGRAMA                    →    PROYECTO REAL

Frameworks & Drivers        →    infraestructura/adaptadores/
                                 ├── jpa/entidades/
                                 │   └── AdministradorJpaEntity.java
                                 ├── jpa/repositorios/
                                 │   └── IAdministradorJpaRepository.java
                                 ├── mapeadores/
                                 │   └── IAdministradorJpaMapper.java
                                 └── repositorios/
                                     └── AdministradorRepositorioImpl.java

Interface Adapters          →    presentacion/controladores/
                                 └── AdministradorController.java

Use Cases                   →    aplicacion/servicios/
                                 └── AdministradorService.java

Entities                    →    dominio/
                                 ├── entidades/
                                 │   └── Administrador.java
                                 └── repositorios/
                                     └── IAdministradorRepositorio.java
```

### Regla de Dependencia Aplicada

**✅ PERMITIDO:**
- `AdministradorController` → `AdministradorService`
- `AdministradorService` → `IAdministradorRepositorio`
- `AdministradorRepositorioImpl` → `IAdministradorRepositorio` (implementa)
- `AdministradorRepositorioImpl` → `Administrador` (usa)

**❌ PROHIBIDO:**
- `Administrador` → `AdministradorJpaEntity`
- `Administrador` → `AdministradorService`
- `IAdministradorRepositorio` → `AdministradorRepositorioImpl`
- `Dominio` → `Infraestructura` (EN NINGÚN CASO)

### Beneficios de esta Estructura

1. **Testabilidad:**
   ```java
   // Test del servicio SIN base de datos
   @Test
   void testObtenerTodos() {
       IAdministradorRepositorio mockRepo = mock(IAdministradorRepositorio.class);
       AdministradorService service = new AdministradorService(mockRepo);
       // ... test sin infraestructura
   }
   ```

2. **Intercambiabilidad:**
   - Cambiar PostgreSQL por MongoDB: Solo cambias infraestructura
   - Cambiar REST por GraphQL: Solo cambias presentación
   - El dominio y aplicación NO cambian

3. **Evolución Independiente:**
   - Desarrollar dominio antes de decidir tecnologías
   - Cada capa evoluciona a su ritmo
   - Equipos diferentes pueden trabajar en capas diferentes

---

## 📊 Conclusión General

La implementación del módulo **Administrador** en el proyecto Delicias Restaurant demuestra una aplicación rigurosa de los principios de Arquitectura Limpia:

### ✅ Logros:

1. **Entidades en el Centro:**
   - `Administrador` es un POJO puro sin dependencias
   - Representa conocimiento de negocio protegido
   - Testeable de forma aislada

2. **Infraestructura Separada:**
   - `AdministradorJpaEntity` maneja detalles técnicos
   - Mappers convierten entre capas
   - Cambios tecnológicos NO afectan el dominio

3. **Arquitectura Hexagonal:**
   - Puertos: `IAdministradorRepositorio`
   - Adaptadores: `AdministradorRepositorioImpl`
   - Inversión de dependencias correctamente aplicada

4. **Mantenibilidad:**
   - Código organizado y responsabilidades claras
   - Fácil de testear y extender
   - Preparado para evolución futura

### 🎯 Aplicación de Principios SOLID:

- **SRP:** Cada clase tiene una responsabilidad única
- **OCP:** Abierto a extensión (nuevos adaptadores), cerrado a modificación (dominio estable)
- **LSP:** Los adaptadores sustituyen interfaces del dominio
- **ISP:** Interfaces específicas y pequeñas
- **DIP:** Dependencias apuntan a abstracciones, no a implementaciones

---

## 🔗 Link del Repositorio en GitHub

https://github.com/pablo2552t/deliciaspoo

---

**Fecha de entrega:** 27 de Enero, 2026  
**Proyecto:** Delicias Restaurant - Módulo Administrador  
**Arquitectura:** Hexagonal / Clean Architecture
