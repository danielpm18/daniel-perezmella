# Order Management System - Evolutivo v2.0

Este proyecto es una aplicación Java basada en el patrón **MVC (Modelo-Vista-Controlador)** que permite la gestión de pedidos almacenados en formato JSON. Esta versión incluye mejoras significativas en la interactividad y persistencia de datos.

## 🚀 Nuevas Funcionalidades (Evolutivo)

Se han implementado los siguientes casos de uso adicionales:

1.  **Visualización Inicial**: Ahora la aplicación muestra una lista de los IDs de pedidos disponibles nada más arrancar, permitiendo al usuario saber qué datos existen sin realizar búsquedas previas.
2.  **Creación de Pedidos**: Formulario integrado para añadir nuevos pedidos. El sistema valida que el **ID sea único** para evitar duplicados en la base de datos.
3.  **Borrado de Pedidos**: Posibilidad de eliminar pedidos existentes mediante el botón "Borrar".
4.  **Edición de Artículos (Opcional)**: Funcionalidad avanzada para modificar la **cantidad** y el **descuento** de los artículos de un pedido ya existente.
5.  **Persistencia Real**: Todos los cambios (crear, borrar, editar) se sobrescriben automáticamente en el archivo `orders.json`, asegurando que los datos se mantengan al reiniciar la aplicación.

---

## 🛠️ Tecnologías Utilizadas

* **Java SE**: Lenguaje principal.
* **Swing**: Interfaz gráfica de usuario.
* **Maven**: Gestión de dependencias.
* **Jackson**: Librería para el parseo y escritura de archivos JSON.
* **PlantUML**: Generación de diagramas de casos de uso.
* **SLF4J/Logback**: Sistema de trazas y logs.

---

## 📖 Manual de Usuario

### 1. Consultar Pedidos
Al abrir la app, consulta la lista de IDs en la parte superior. Escribe un ID en el cuadro de texto y pulsa **"Buscar"**. Verás los detalles del pedido, el desglose de artículos y los totales convertidos a USD mediante un servicio de tasa de cambio.

### 2. Crear un Pedido
* Pulsa el botón **"Nuevo Pedido"**.
* Introduce un ID único (ej. `O006`).
* Introduce el nombre del primer artículo.
* El pedido se guardará automáticamente en el sistema.

### 3. Borrar un Pedido
* Escribe el ID del pedido que deseas eliminar.
* Pulsa el botón **"Borrar"**.
* El ID desaparecerá de la lista superior y del archivo JSON.

### 4. Editar un Pedido (Opcional)
* Busca primero el pedido que quieres modificar.
* Pulsa **"Editar Artículos"**.
* Introduce la nueva cantidad y el nuevo porcentaje de descuento.
* La vista se actualizará automáticamente con los nuevos cálculos de totales.

---

## 📊 Diagrama de Casos de Uso

El diagrama actualizado se encuentra en la ruta `src/main/resources/plantuml/`. Refleja las nuevas interacciones del usuario con el sistema y la relación de inclusión con el proceso de persistencia en JSON.



---

## 📦 Instalación y Ejecución

1.  Clonar el repositorio:
    ```bash
    git clone [https://github.com/TU_USUARIO/TU_REPOSIORIO.git](https://github.com/TU_USUARIO/TU_REPOSIORIO.git)
    ```
2.  Importar como proyecto Maven en tu IDE (IntelliJ, Eclipse, VS Code).
3.  Asegurarse de que el archivo `src/main/resources/orders.json` tiene permisos de escritura.
4.  Ejecutar la clase `Main.java`.

---

© 2025 - Desarrollado por Daniel Perezmella