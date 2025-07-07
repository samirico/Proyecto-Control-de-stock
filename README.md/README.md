# Sistema de Control de Stock

Este proyecto es una aplicación de consola desarrollada en Java que permite gestionar productos y categorías mediante una conexión a base de datos. Fue creado como práctica para reforzar conocimientos en desarrollo backend con JDBC y arquitectura en capas.

## Tecnologías utilizadas

- **Java 8+**
- **JDBC** para conexión con base de datos
- **MySQL** (o base similar compatible)
- **Maven** para gestión de dependencias
- **Eclipse IDE**

## Estructura del proyecto

control-de-stock/
 src/
    main/java/com/usuario/controlstock/
    controller/
    dao/
    factory/
    modelo/
    ControlDeStockMain.java
 pom.xml

## Funcionalidades

- Registrar productos con nombre, descripción y cantidad.
- Crear y gestionar categorías.
- Listar productos por categoría.
- Conexión con base de datos a través de `ConnectionFactory`.