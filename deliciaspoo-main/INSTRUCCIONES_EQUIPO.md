# 📘 Instrucciones para el Equipo - Proyecto Delicias Restaurant

**Repositorio GitHub:** https://github.com/pablo2552t/deliciaspoo.git

---

## 🎯 Objetivo

Cada miembro del equipo debe implementar **su rol asignado** siguiendo la **misma arquitectura hexagonal** que ya está implementada para Cliente.

---

## 📋 Paso 1: Clonar el Repositorio

Abre tu terminal (Git Bash, PowerShell, o CMD) y ejecuta:

```bash
git clone https://github.com/pablo2552t/deliciaspoo.git
cd deliciaspoo
```

---

## 📂 Paso 2: Abrir el Proyecto en Spring Tool Suite

1. Abre **Spring Tool Suite**
2. **File** → **Import** → **Maven** → **Existing Maven Projects**
3. Selecciona la carpeta `deliciaspoo` que acabas de clonar
4. Click en **Finish**

---

## ✅ Paso 3: Verificar la Estructura Existente

Antes de empezar, revisa cómo está organizado el código de **Cliente** (ya implementado por Pablo):

```
src/main/java/com/suda/delicias/
├── dominio/
│   ├── entidades/
│   │   ├── Cliente.java          ← Entidad pura (POJO)
│   │   └── TipoCliente.java
│   └── repositorios/
│       ├── IClienteRepositorio.java     ← Interfaz (Puerto)
│       └── ITipoClienteRepositorio.java
│
├── aplicacion/
│   └── servicios/
│       ├── ClienteService.java          ← Servicio de negocio
│       └── TipoClienteService.java
│
├── infraestructura/
│   └── adaptadores/
│       ├── jpa/entidades/
│       │   ├── ClienteJpaEntity.java    ← Entidad JPA
│       │   └── TipoClienteJpaEntity.java
│       ├── jpa/repositorios/
│       │   ├── IClienteJpaRepository.java
│       │   └── ITipoClienteJpaRepository.java
│       ├── mapeadores/
│       │   ├── IClienteJpaMapper.java   ← MapStruct mapper
│       │   └── ITipoClienteJpaMapper.java
│       └── repositorios/
│           ├── ClienteRepositorioImpl.java  ← Implementación
│           └── TipoClienteRepositorioImpl.java
│
└── presentacion/
    └── controladores/
        ├── ClienteController.java       ← REST Controller
        └── TipoClienteController.java
```

---

## 🛠️ Paso 4: Implementar TU ROL

### Ejemplo: Si te asignaron **Pedido**

Crea EXACTAMENTE la misma estructura pero para `Pedido`:

#### 1. Dominio - Entidad Pura

**Archivo:** `src/main/java/com/suda/delicias/dominio/entidades/Pedido.java`

```java
package com.suda.delicias.dominio.entidades;

public class Pedido {
    private final Long id;
    private final String numeroPedido;
    private final String estado;
    // ... más campos
    
    // Constructor
    public Pedido(Long id, String numeroPedido, String estado) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.estado = estado;
    }
    
    // Solo getters (inmutable)
    public Long getId() { return id; }
    public String getNumeroPedido() { return numeroPedido; }
    public String getEstado() { return estado; }
}
```

#### 2. Dominio - Interfaz del Repositorio

**Archivo:** `src/main/java/com/suda/delicias/dominio/repositorios/IPedidoRepositorio.java`

```java
package com.suda.delicias.dominio.repositorios;

import com.suda.delicias.dominio.entidades.Pedido;
import java.util.List;
import java.util.Optional;

public interface IPedidoRepositorio {
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    void deleteById(Long id);
}
```

#### 3. Infraestructura - Entidad JPA

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/jpa/entidades/PedidoJpaEntity.java`

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_pedido")
    private String numeroPedido;
    
    @Column(name = "estado")
    private String estado;
    
    // ... más campos con @Column
}
```

#### 4. Infraestructura - Repositorio JPA

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/jpa/repositorios/IPedidoJpaRepository.java`

```java
package com.suda.delicias.infraestructura.adaptadores.jpa.repositorios;

import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.PedidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoJpaRepository extends JpaRepository<PedidoJpaEntity, Long> {
}
```

#### 5. Infraestructura - Mapper

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/mapeadores/IPedidoJpaMapper.java`

```java
package com.suda.delicias.infraestructura.adaptadores.mapeadores;

import com.suda.delicias.dominio.entidades.Pedido;
import com.suda.delicias.infraestructura.adaptadores.jpa.entidades.PedidoJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPedidoJpaMapper {
    Pedido toDomain(PedidoJpaEntity entity);
    PedidoJpaEntity toEntity(Pedido pedido);
}
```

#### 6. Infraestructura - Implementación del Repositorio

**Archivo:** `src/main/java/com/suda/delicias/infraestructura/adaptadores/repositorios/PedidoRepositorioImpl.java`

```java
package com.suda.delicias.infraestructura.adaptadores.repositorios;

import com.suda.delicias.dominio.entidades.Pedido;
import com.suda.delicias.dominio.repositorios.IPedidoRepositorio;
import com.suda.delicias.infraestructura.adaptadores.jpa.repositorios.IPedidoJpaRepository;
import com.suda.delicias.infraestructura.adaptadores.mapeadores.IPedidoJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public final class PedidoRepositorioImpl implements IPedidoRepositorio {
    
    private final IPedidoJpaRepository jpaRepository;
    private final IPedidoJpaMapper mapper;
    
    public PedidoRepositorioImpl(IPedidoJpaRepository jpaRepository, IPedidoJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public List<Pedido> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Pedido> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    
    @Override
    public Pedido save(Pedido pedido) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(pedido)));
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
```

#### 7. Aplicación - Servicio

**Archivo:** `src/main/java/com/suda/delicias/aplicacion/servicios/PedidoService.java`

```java
package com.suda.delicias.aplicacion.servicios;

import com.suda.delicias.dominio.entidades.Pedido;
import com.suda.delicias.dominio.repositorios.IPedidoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    
    private final IPedidoRepositorio repositorio;
    
    public PedidoService(IPedidoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    public List<Pedido> obtenerTodos() {
        return repositorio.findAll();
    }
    
    public Optional<Pedido> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }
    
    public Pedido crear(Pedido pedido) {
        return repositorio.save(pedido);
    }
    
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
```

#### 8. Presentación - Controller

**Archivo:** `src/main/java/com/suda/delicias/presentacion/controladores/PedidoController.java`

```java
package com.suda.delicias.presentacion.controladores;

import com.suda.delicias.aplicacion.servicios.PedidoService;
import com.suda.delicias.dominio.entidades.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    private final PedidoService service;
    
    public PedidoController(PedidoService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Pedido> obtenerTodos() {
        return service.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Pedido crear(@RequestBody PedidoRequest request) {
        Pedido pedido = new Pedido(null, request.getNumeroPedido(), request.getEstado());
        return service.crear(pedido);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    // DTO interno
    public static class PedidoRequest {
        private String numeroPedido;
        private String estado;
        
        public String getNumeroPedido() { return numeroPedido; }
        public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
}
```

---

## 🔄 Paso 5: Workflow de Git

### ANTES de empezar a trabajar:

```bash
git pull origin main
```

**Esto trae los cambios más recientes del repositorio.**

---

### DESPUÉS de crear tus archivos:

```bash
# Ver qué archivos modificaste
git status

# Agregar todos los archivos nuevos/modificados
git add .

# Hacer commit con mensaje descriptivo
git commit -m "Agregada estructura hexagonal para Pedido"

# Subir al repositorio
git push origin main
```

---

## ⚠️ REGLAS IMPORTANTES

### ✅ HACER:
- Trabajar solo en archivos de **TU ROL** (Pedido, Producto, Mesa, etc.)
- Seguir **EXACTAMENTE** la misma estructura que Cliente
- Hacer `git pull` antes de empezar a trabajar cada día
- Hacer commit y push cuando termines tu trabajo

### ❌ NO HACER:
- **NUNCA** modificar archivos de Cliente (ya hechos por Pablo)
- **NUNCA** modificar archivos de otros compañeros
- **NUNCA** hacer push sin hacer pull primero
- **NUNCA** cambiar la estructura del proyecto

---

## 📊 Distribución de Roles Sugerida

| Compañero | Rol | Archivos a Crear |
|-----------|-----|------------------|
| Pablo | Cliente (✅ YA HECHO) | Cliente.java, TipoCliente.java, etc. |
| Compañero 1 | Pedido | Pedido.java, IPedidoRepositorio.java, etc. |
| Compañero 2 | Producto | Producto.java, IProductoRepositorio.java, etc. |
| Compañero 3 | Mesa | Mesa.java, IMesaRepositorio.java, etc. |
| Compañero 4 | Empleado | Empleado.java, IEmpleadoRepositorio.java, etc. |

**Cada uno crea 8 archivos siguiendo el patrón de Cliente.**

---

## 🆘 Solución de Problemas

### Problema: "Git no está instalado"
**Solución:** Descarga e instala Git desde https://git-scm.com/

### Problema: "Conflicto al hacer push"
**Solución:** 
```bash
git pull origin main
# Resolver conflictos si los hay
git add .
git commit -m "Resuelto conflicto"
git push origin main
```

### Problema: "MapStruct no genera los mappers"
**Solución:** 
1. Click derecho en el proyecto → **Maven** → **Update Project**
2. **Run As** → **Maven build...**
3. Goals: `clean compile`

---

## 📞 Contacto

Si tienes dudas, contacta a Pablo (coordinador del proyecto).

**Repositorio:** https://github.com/pablo2552t/deliciaspoo.git

---

## ✅ Checklist Final

Antes de decir que terminaste, verifica:

- [ ] Creaste los 8 archivos para tu rol
- [ ] Seguiste la misma estructura que Cliente
- [ ] El proyecto compila sin errores
- [ ] Hiciste commit y push a GitHub
- [ ] Probaste que tus endpoints funcionen (opcional)

---

¡Éxito! 🚀
