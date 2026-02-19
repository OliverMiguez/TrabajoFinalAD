# Proyecto Microservicios AD - Gestión de Libros

Este proyecto implementa un sistema de gestión de lecturas basado en una arquitectura de microservicios, cumpliendo estrictamente con el Diseño Técnico especificado para la materia de Acceso a Datos.

##  Arquitectura del Sistema

El sistema se divide en tres microservicios principales interconectados mediante API REST:

1.  **Executor (con-external / prd-rex):**
    *   **Puerto:** 8080
    *   **Función:** Actúa como la puerta de entrada principal del sistema. Coordina el registro de libros en ambas bases de datos y gestiona la persistencia en el sistema de archivos (XML y Logs).
    *   **Responsabilidad:** Implementa el caso de uso *Rexistro Libro* y *Consulta Rexistros Sistema*.

2.  **Postgres (relational-prd-query):**
    *   **Puerto:** 8081
    *   **Función:** Gestiona la comunicación con la base de datos relacional PostgreSQL.
    *   **Responsabilidad:** Implementa el caso de uso *Consulta Libro (db relacional)* con filtros avanzados.

3.  **Mongo (nonrelational-prd-query):**
    *   **Puerto:** 8094
    *   **Función:** Gestiona la comunicación con la base de datos no relacional MongoDB.
    *   **Responsabilidad:** Implementa el caso de uso *Consulta Libro (db non relacional)* con filtros avanzados.

##  Cumplimiento de Requisitos

### 1. Registro de Libros (Dual + XML)
Cada vez que se registra un libro a través del endpoint `/api/registro` en el **Executor**:
*   Se inserta en **PostgreSQL**.
*   Se inserta en **MongoDB**.
*   Se añade al archivo **`registros.xml`**.
*   Se genera un evento de éxito o error en **`sistema.log`**.

### 2. Consultas y Filtros
Se han implementado las búsquedas requeridas en todos los servicios:
*   **Búsqueda por ISBN o Título:** Disponible en Postgres, Mongo y el archivo XML.
*   **Listado por Autor:** Filtro implementado en ambas bases de datos.
*   **Rangos de Fechas:** Filtros por fecha de lectura y fecha de registro en ambas bases de datos.

### 3. Persistencia y Trazabilidad
*   **XML:** Los datos son persistentes en un fichero físico, permitiendo su consulta incluso si las bases de datos están caídas.
*   **Logs:** El sistema registra cada operación, permitiendo al usuario revisar errores de inserción o confirmar registros exitosos.

##  Cómo Ejecutar el Proyecto

1.  **Bases de Datos:** Asegurarse de tener instancias de PostgreSQL y MongoDB funcionando.
2.  **Arranque de Servicios:** Ejecutar los tres microservicios simultáneamente (Postgres, Mongo y Executor).
3.  **Pruebas:** Acceder a la interfaz de Swagger del Executor: `http://localhost:8080/swagger-ui/index.html`.

---
*Proyecto desarrollado siguiendo los estándares de diseño técnico para microservicios de Acceso a Datos.*
