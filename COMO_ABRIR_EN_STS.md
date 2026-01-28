# 🚀 Cómo Abrir el Proyecto en Spring Tool Suite

Esta guía te ayudará a configurar el proyecto **Delicias Restaurant** en Spring Tool Suite después de clonarlo desde GitHub.

---

## 📋 Requisitos Previos

Antes de empezar, asegúrate de tener instalado:

- ✅ **Git** - [Descargar aquí](https://git-scm.com/)
- ✅ **Java JDK 17** - [Descargar aquí](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- ✅ **Spring Tool Suite (STS)** - [Descargar aquí](https://spring.io/tools)
- ✅ **PostgreSQL** (opcional, si vas a ejecutar la aplicación)

---

## 📥 Paso 1: Clonar el Repositorio

### Opción A: Con Git Bash / Terminal

1. Abre **Git Bash** (Windows) o **Terminal** (Mac/Linux)
2. Navega a la carpeta donde quieres guardar el proyecto:
   ```bash
   cd Desktop
   ```
3. Clona el repositorio:
   ```bash
   git clone https://github.com/pablo2552t/deliciaspoo.git
   ```
4. Espera a que se descarguen todos los archivos

### Opción B: Descargar ZIP desde GitHub

1. Ve a: https://github.com/pablo2552t/deliciaspoo
2. Click en el botón verde **"Code"**
3. Selecciona **"Download ZIP"**
4. Descomprime el archivo en tu carpeta deseada

---

## 🔧 Paso 2: Abrir en Spring Tool Suite

### 2.1. Iniciar Spring Tool Suite

1. Abre **Spring Tool Suite**
2. Selecciona o crea un **Workspace** (carpeta donde se guardan tus proyectos)
3. Click en **"Launch"**

### 2.2. Importar el Proyecto Maven

1. En STS, ve al menú superior:
   ```
   File → Import...
   ```

2. En la ventana de diálogo, expande la carpeta **"Maven"**

3. Selecciona **"Existing Maven Projects"**

4. Click en **"Next"**

### 2.3. Seleccionar la Carpeta del Proyecto

1. Click en **"Browse..."**

2. Navega hasta la carpeta donde clonaste el repositorio:
   ```
   Ejemplo: C:\Users\TuUsuario\Desktop\deliciaspoo
   ```

3. Selecciona la carpeta **deliciaspoo**

4. Click en **"Select Folder"** o **"Abrir"**

### 2.4. Confirmar el archivo pom.xml

1. STS detectará automáticamente el archivo **pom.xml**

2. Verás una lista que dice:
   ```
   /pom.xml  com.suda.delicias:delicias:0.0.1-SNAPSHOT
   ```

3. Asegúrate de que la **casilla esté marcada** ✅

4. Click en **"Finish"**

---

## ⏳ Paso 3: Esperar la Descarga de Dependencias

### ¿Qué está pasando?

STS comenzará a descargar automáticamente todas las dependencias de Maven (Spring Boot, PostgreSQL, MapStruct, Lombok, etc.).

### Indicadores:

- En la parte inferior derecha verás: **"Building workspace"** o **"Downloading..."**
- En la barra de progreso: **"Maven Dependencies"**

### ⏱️ Tiempo estimado:
- **Primera vez:** 5-15 minutos (depende de tu internet)
- **Siguientes veces:** 1-2 minutos

### ⚠️ Importante:
**NO cierres STS** mientras está descargando dependencias.

---

## ✅ Paso 4: Verificar que el Proyecto se Importó Correctamente

### 4.1. Expandir el Proyecto

En el **Package Explorer** (panel izquierdo), expande el proyecto:

```
▼ delicias [boot]
  ▼ src/main/java
    ▼ com.suda.delicias
      ▼ dominio
        ▼ entidades
          Cliente.java
          TipoCliente.java
        ▼ repositorios
          IClienteRepositorio.java
          ITipoClienteRepositorio.java
      ▼ aplicacion
        ▼ servicios
          ClienteService.java
          TipoClienteService.java
      ▼ infraestructura
        ▼ adaptadores
          ...
      ▼ presentacion
        ▼ controladores
          ClienteController.java
          TipoClienteController.java
      DeliciasApplication.java
```

### 4.2. Verificar que NO hay Errores

1. Ve a la pestaña **"Problems"** (panel inferior)
2. Si hay **0 errors**, todo está bien ✅
3. Si hay **warnings** (advertencias), no te preocupes, son normales

---

## 🔨 Paso 5: Compilar el Proyecto (Generar Mappers de MapStruct)

MapStruct necesita generar automáticamente las implementaciones de los mappers.

### Opción A: Con Maven (Recomendado)

1. Click derecho en el proyecto **"delicias"**
2. Selecciona: **Run As → Maven build...**
3. En **Goals**, escribe:
   ```
   clean compile
   ```
4. Click en **"Run"**
5. Espera a que termine (verás "BUILD SUCCESS" en la consola)

### Opción B: Desde el Menú

1. Click derecho en el proyecto
2. **Maven → Update Project...**
3. Marca la casilla **"Force Update of Snapshots/Releases"**
4. Click en **"OK"**

### ✅ Verificar que MapStruct generó los Mappers

Expande:
```
▼ target
  ▼ generated-sources
    ▼ annotations
      ▼ com.suda.delicias.infraestructura.adaptadores.mapeadores
        IClienteJpaMapperImpl.java
        ITipoClienteJpaMapperImpl.java
```

Si ves estos archivos, ¡perfecto! ✅

---

## ▶️ Paso 6: Ejecutar la Aplicación (Opcional)

Si quieres ejecutar la aplicación para probar los endpoints:

### 6.1. Configurar la Base de Datos (Si la tienes)

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/delicias_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 6.2. Ejecutar

1. Abre **DeliciasApplication.java**
2. Click derecho → **Run As → Spring Boot App**
3. Espera a que inicie (verás en la consola: "Started DeliciasApplication")
4. La aplicación estará disponible en: http://localhost:8080

### 6.3. Probar Endpoints (Opcional)

Usa **Postman** o **cURL**:

```bash
# Listar todos los clientes
GET http://localhost:8080/api/clientes

# Crear un cliente
POST http://localhost:8080/api/clientes
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "direccion": "Av. Principal 123",
  "email": "juan@example.com",
  "telefono": "0999123456",
  "ci": "1234567890",
  "tipoClienteId": 1
}
```

---

## 🔍 Estructura del Proyecto

Una vez abierto, verás esta estructura de paquetes:

```
com.suda.delicias
├── dominio
│   ├── entidades (POJOs puros)
│   └── repositorios (Interfaces - Puertos)
├── aplicacion
│   └── servicios (Casos de uso)
├── infraestructura
│   └── adaptadores
│       ├── jpa
│       │   ├── entidades (Entidades JPA)
│       │   └── repositorios (Spring Data Repositories)
│       ├── mapeadores (MapStruct Mappers)
│       └── repositorios (Implementaciones - Adaptadores)
└── presentacion
    └── controladores (REST Controllers)
```

Esta es la **Arquitectura Hexagonal** implementada.

---

## 🆘 Solución de Problemas Comunes

### ❌ Problema 1: "Maven Dependencies no se descargan"

**Solución:**
1. Click derecho en el proyecto
2. **Maven → Update Project...**
3. Marca **"Force Update of Snapshots/Releases"**
4. Click **"OK"**

### ❌ Problema 2: "No encuentra Java 17"

**Solución:**
1. Click derecho en el proyecto → **Properties**
2. **Java Build Path → Libraries**
3. Verifica que esté **JRE System Library [JavaSE-17]**
4. Si no, elimina la actual y agrega JDK 17

### ❌ Problema 3: "Lombok no funciona"

**Solución:**
1. Descarga Lombok: https://projectlombok.org/download
2. Ejecuta el archivo `.jar`
3. Especifica la ubicación de STS
4. Click en **"Install/Update"**
5. Reinicia STS

### ❌ Problema 4: "MapStruct no genera los mappers"

**Solución:**
1. Ejecuta: **Maven → Update Project**
2. Luego: **Run As → Maven build... → Goals: clean compile**
3. Busca los mappers en `target/generated-sources/annotations`

### ❌ Problema 5: "Muchos errores en el código"

**Solución:**
1. Asegúrate de que Maven termine de descargar dependencias
2. Ejecuta: **Project → Clean...**
3. Marca tu proyecto y click en **"Clean"**
4. Espera a que recompile

---

## 📚 Próximos Pasos

Una vez que el proyecto esté abierto correctamente:

1. ✅ Revisa la estructura de paquetes
2. ✅ Lee los archivos de código para entender la arquitectura
3. ✅ Si vas a agregar un nuevo rol (ej: Administrador), lee `GUIA_ROL_ADMINISTRADOR.md`
4. ✅ Lee `INSTRUCCIONES_EQUIPO.md` para trabajar en equipo con Git

---

## 📞 Ayuda Adicional

- **Repositorio GitHub:** https://github.com/pablo2552t/deliciaspoo
- **Contacto:** Pablo (coordinador del proyecto)

---

¡Listo! Ahora tienes el proyecto completamente configurado en Spring Tool Suite. 🎉

**Happy Coding!** 💻✨
