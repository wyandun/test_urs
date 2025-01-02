# Proyecto de Gestión de Componentes (Spring + React)

Este proyecto está diseñado para gestionar los datos de componentes de un sistema de provincias, cantones y parroquias. El backend está desarrollado con **Spring Boot** utilizando el patrón **MVC** (Modelo-Vista-Controlador), y el frontend está implementado con **React** utilizando **Vite** como bundler.

## Arquitectura del Sistema

### Backend (Spring Boot)

La arquitectura del backend sigue el patrón **MVC**:

- **Modelo (Model)**: Representa la estructura de los datos. Se utiliza para interactuar con la base de datos a través de las entidades que corresponden a provincias, cantones y parroquias.
- **Vista (View)**: Se gestionan a través de las respuestas JSON que el backend envía al frontend.
- **Controlador (Controller)**: Recibe las peticiones HTTP, interactúa con los modelos y devuelve la respuesta adecuada.

Los componentes principales en el backend son:

1. **Modelo**: Cada uno de los componentes (Provincia, Cantón, Parroquia) tiene una entidad correspondiente que se mapea a una tabla de base de datos.
   
2. **Controlador**: Define los endpoints de la API que serán consumidos por el frontend, tales como obtener provincias, cantones y parroquias.

### Frontend (React + Vite)

El frontend está desarrollado con **React** y gestionado por **Vite**. Los componentes principales están alineados horizontalmente y muestran la información seleccionada en tiempo real. Los datos de provincias, cantones y parroquias se obtienen del backend a través de peticiones HTTP.

#### Componentes principales:

- **Formulario de Selección**: Tres dropdowns (selects) permiten al usuario elegir una provincia, cantón y parroquia. Cuando se cambia la provincia, se reinician los valores de cantón y parroquia, mostrando solo los valores correspondientes a la selección actual.
- **Visualización de Datos**: En la parte inferior, se muestra la información de las provincias, cantones y parroquias seleccionadas, con sus respectivos códigos.

### Comunicación entre el Backend y el Frontend

El frontend realiza peticiones **GET** al backend para obtener las listas de provincias, cantones y parroquias. Las rutas son:

- `GET /provincias`: Obtiene todas las provincias.
- `GET /cantones/{id}`: Obtiene los cantones de la provincia seleccionada.
- `GET /parroquias/{provinciaId}/{cantonId}`: Obtiene las parroquias del cantón seleccionado.

---

## Instalación y Ejecución

### Backend (Spring Boot)

1. **Clonar el repositorio**:
   ```bash
   git clone https://url-del-repositorio-backend.git
2. **Acceder al directorio del backend**:
   ```bash
   cd provincias-api
3. **Instalar dependencias y ejecutar el proyecto:**:
   ```bash
   mvn spring-boot:run
El backend estará disponible en http://localhost:8080.

### Frontend (React + Vite)

1. **Acceder al directorio del frontend**:
   ```bash
   cd ..
   cd vite-project
2. **Instalar dependencias**:
   ```bash
   npm install
3.	**Ejecutar el proyecto**:
   ```bash
   npm run dev
El frontend estará disponible en http://localhost:5173.