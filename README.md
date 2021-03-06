# 馃Ω鈥嶁檪锔廙arvel Comic Shop

## 馃搶 Introducci贸n

Con el paso de los a帽os la biblioteca de comics, necesita tener actualizado todo el listado de escritores, editores y coloristas de c贸mics que han estado
involucrados en las historias de los siguientes integrantes de los Vengadores (Iron Man, Captain America). As铆 como todos los dem谩s h茅roes que a trav茅s de
cada c贸mic han interactuado con cada uno de ellos. Esto hay que actualizarlo diario, ya que hay que pagarles regal铆as respectivas a los escritores,
editores y coloristas.

Esta API tambi茅n contiene documentaci贸n mediante [OpenAPI](https://www.openapis.org) que se puede visualizar en `http://{hostname}/marvel/swagger-ui`

![component-diagram](doc/components-diagram.png)

## 馃棡 Inicio R谩pido

El proyecto cuenta con dos scripts que permiten la ejecuci贸n r谩pida del mismo:

- [x] Para compilar y construir el proyecto ejecutar: `./assemble.sh`
- [x] Para ejecutar la API en el puerto 80: `./assemble.sh`

### Ejecuci贸n sobre el c贸digo fuente

Al contar con el c贸digo fuente, se pueden ejecutar los siguientes comandos, esto permitir谩 iniciar los recursos de base de datos y tambi茅n desplegar la
aplicaci贸n

- [x] Start database service with: `docker-compose up -d`
- [x] Build and execute application: `mvn spring-boot:run`

## 馃搵 Requerimientos de sistema

- Los scripts (assemble.sh y avengers.sh) 煤nicamente se pueden ejecutar sobre S.O. basados en unix.
- Versi贸n 1.8 de [Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html).
- Se requiere contar con [Docker](https://www.docker.com)
- Se requiere contar con [Maven](https://maven.apache.org)

## 馃毆 Definici贸n de endpoints REST

Para conocer la documentaci贸n del API, puedes visitar el recurso del sitio `/marvel/api-docs` y `/marvel/`. Mediante documentaci贸n generada por OpenAPI, se
puede conocer la definici贸n de los endpoints y modelos que componen la API.

![open-api-docs](doc/openapi-docs.png)

### Ruta de contexto

Por defecto, la ruta de contexto para la API es `/marvel/`

### Endpoints disponibles

Los endpoints disponibles en la aplicaci贸n son:

馃煝 Characters endpoint ( `/characters` ):

Obtiene una lista de los otros h茅roes con los cuales el personaje solicitado ha interactuado en cada uno de los c贸mics.

> `curl -X 'GET' 'http://test.albo.mx/marvel/marvel/characters/Iron%20Man' -H 'accept: application/json'`

馃煝 Collaborators endpoint ( `/collaborators` )

Obtiene una lista de los editores, escritores y coloristas que han estado involucrados en los comics del personaje solicitado.

> `curl -X 'GET' 'http://test.albo.mx/marvel/marvel/collaborators/Iron%20Man' -H 'accept: application/json'`

## 馃И Testing

El proyecto contiene una serie de tests unitarios que permiten validar la funcionalidad de la aplicaci贸n. Para poder realizar la ejecuci贸n de los mismos,
se deber谩 ejecutar `mvn test` sobre la ra铆z del proyecto.

- [x] Ejecuci贸n de tests unitarios: `mvn test`

Adicional a las pruebas unitarias, puede ser funcional para las pruebas de integraci贸n el uso de [postman](https://www.postman.com) con las definiciones de
API que se encuentran en [esta carpeta](/doc/postman)