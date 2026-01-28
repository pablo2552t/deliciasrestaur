# Delicias Restaurant - Spring Boot Project (Cliente Role)

## 📋 Descripción del Proyecto

Este es un proyecto Spring Boot para el restaurante "Delicias" que implementa el **rol de CLIENTE**. El proyecto incluye gestión de clientes y pedidos con una API REST completa.

## 🏗️ Arquitectura del Proyecto

```
delicias/
├── src/
│   └── main/
│       ├── java/com/suda/delicias/
│       │   ├── DeliciasApplication.java          (Clase principal)
│       │   ├── controller/
│       │   │   └── PedidoController.java         (API REST)
│       │   ├── service/
│       │   │   └── PedidoService.java            (Lógica de negocio)
│       │   ├── repository/
│       │   │   ├── ClienteRepository.java        (Acceso a datos)
│       │   │   └── PedidoRepository.java
│       │   └── model/
│       │       ├── Cliente.java                  (Entidad)
│       │       ├── Pedido.java
│       │       └── DetallePedido.java
│       └── resources/
│           └── application.properties            (Configuración)
└── pom.xml                                       (Dependencias Maven)
```

## 🚀 Requisitos Previos

- **Java 17** o superior
- **Maven 3.6+** (para compilar y ejecutar)
- IDE recomendado: IntelliJ IDEA, Eclipse, o VS Code con Java Extension

## 📦 Dependencias Incluidas

- Spring Boot 3.2.0
- Spring Boot Starter Web (API REST)
- Spring Boot Starter Data JPA (Persistencia)
- H2 Database (Base de datos en memoria)
- Lombok (Reducción de código boilerplate)

## ⚙️ Instalación y Ejecución

### 1. Instalar Maven (si no lo tienes)

**Windows:**
```powershell
# Descarga Maven desde https://maven.apache.org/download.cgi
# Descomprime y agrega al PATH del sistema
```

**O usando Chocolatey:**
```powershell
choco install maven
```

### 2. Compilar el Proyecto

```bash
mvn clean compile
```

### 3. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en `http://localhost:8080`

### 4. Acceder a la Consola H2

Una vez que la aplicación esté corriendo, accede a la consola de base de datos:

**URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:deliciasdb`
- **Username:** `sa`
- **Password:** *(dejar vacío)*

## 📡 API REST - Endpoints Disponibles

### Crear un Pedido
```http
POST http://localhost:8080/api/pedidos
Content-Type: application/json

{
  "cliente": {
    "nombre": "Juan Pérez",
    "telefono": "555-1234",
    "direccion": "Calle Principal 123"
  },
  "detalles": [
    {
      "nombreProducto": "Pizza Margarita",
      "cantidad": 2,
      "precioUnitario": 12.50
    },
    {
      "nombreProducto": "Refresco",
      "cantidad": 2,
      "precioUnitario": 2.00
    }
  ]
}
```

### Obtener Todos los Pedidos
```http
GET http://localhost:8080/api/pedidos
```

### Obtener Pedido por ID
```http
GET http://localhost:8080/api/pedidos/{id}
```

### Obtener Pedidos por Cliente
```http
GET http://localhost:8080/api/pedidos/cliente/{idCliente}
```

### Obtener Pedidos por Estado
```http
GET http://localhost:8080/api/pedidos/estado/{estado}
```
Estados válidos: `PENDIENTE`, `EN_PROCESO`, `COMPLETADO`, `CANCELADO`

### Actualizar Estado de Pedido
```http
PUT http://localhost:8080/api/pedidos/{id}/estado?estado=EN_PROCESO
```

## 🗄️ Modelo de Datos

### Cliente
- `idCliente` (Long) - Primary Key
- `nombre` (String) - Nombre del cliente
- `telefono` (String) - Teléfono
- `direccion` (String) - Dirección

### Pedido
- `idPedido` (Long) - Primary Key
- `fecha` (LocalDateTime) - Fecha del pedido
- `total` (Double) - Total del pedido
- `estado` (String) - Estado: PENDIENTE, EN_PROCESO, COMPLETADO, CANCELADO
- `cliente` (Cliente) - Relación Many-to-One

### DetallePedido
- `idDetalle` (Long) - Primary Key
- `cantidad` (Integer) - Cantidad de productos
- `precioUnitario` (Double) - Precio unitario
- `subtotal` (Double) - Subtotal (calculado automáticamente)
- `nombreProducto` (String) - Nombre del producto
- `pedido` (Pedido) - Relación Many-to-One

## 🧪 Probar con Postman o cURL

### Ejemplo con cURL - Crear Pedido:
```bash
curl -X POST http://localhost:8080/api/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "cliente": {
      "nombre": "María García",
      "telefono": "555-5678",
      "direccion": "Avenida Central 456"
    },
    "detalles": [
      {
        "nombreProducto": "Hamburguesa Deluxe",
        "cantidad": 1,
        "precioUnitario": 15.00
      }
    ]
  }'
```

### Ejemplo con cURL - Obtener Pedidos:
```bash
curl http://localhost:8080/api/pedidos
```

## 📝 Notas Importantes

1. **Base de Datos:** El proyecto usa H2 en memoria, los datos se perderán al reiniciar la aplicación.
2. **CORS:** El controlador tiene `@CrossOrigin(origins = "*")` para permitir peticiones desde cualquier origen.
3. **Cálculos Automáticos:** 
   - El subtotal de cada detalle se calcula automáticamente
   - El total del pedido se calcula sumando todos los subtotales
4. **Estado Inicial:** Los pedidos se crean con estado `PENDIENTE` por defecto

## 🔧 Configuración de Base de Datos

Si deseas cambiar a MySQL o PostgreSQL, modifica `application.properties`:

### Para MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/deliciasdb
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

Y agrega la dependencia en `pom.xml`:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

## 👨‍💻 Estructura del Código

- **Controller:** Maneja las peticiones HTTP y devuelve respuestas JSON
- **Service:** Contiene la lógica de negocio (validaciones, cálculos)
- **Repository:** Interfaces JPA para acceso a la base de datos
- **Model:** Entidades JPA que representan las tablas de la base de datos

## 📚 Recursos Adicionales

- [Documentación Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)

---

**Proyecto creado para:** Restaurante Delicias  
**Rol implementado:** Cliente  
**Tecnología:** Spring Boot 3.2.0 + Java 17
