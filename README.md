# movies_api_rest

API REST de películas

Reseña de la API:
Desarrollada con Java 11 siguiendo el estándar de java 8. Spring framework. Spring Boot. Gradle. Hibernate/JPA.
Autenticacion con Spring Security y JWT.
Patrón de diseño MVC.
Paginación.
Filtros con JPA Specifications y Querys.
Pruebas unitarias con Junit.
Configuración de CORS para llamadas externas.
Implementación de lombok.
Integrado con Swagger.
Base de datos H2 console para poder subirlo al servidor de heroku.
Especial focus en las buenas prácticas en el desarrollo de APIs REST.


Autenticación de usuarios
Para realizar solicitudes a los endpoints subsiguientes el usuario deberá contar con un token que obtendrá al autenticarse. Para ello, deberán desarrollarse los endpoints de registro y login, que permitan obtener el token.

El endpoint encargado de la autenticación es:
  - POST /login
  
ACLARACIÓN: todas las solicitudes GET son de acceso público por lo que no se necesita enviar el token de acceso.

***** PELICULAS *****

Listado de películas
El listado muestra:
  - ID
  - Image
  - Title
  
El endpoint es
  -GET /movies
  
Detalles de película
En el detalle se muestran todos los atributos de las peliculas como asi tambien su director, genero y actores relacionados.
  - ID
  - Image
  - Title
  - Duration
  - Gender
  - Director
  - Year
  - Rating
  - Overview
  - Actors
  
Para especificar la pelicula que se desea obtener se debe pasar el id como parametro:
  -GET /movies/{id}
  
Búsqueda de películas
Se puede buscar peliculas por título, año y género.

Para especificar el termino de búsqueda se deberán enviar como parámetros de query.
  - GET /movies?name=nombre
  - GET /movies?year=año
  - GET /movies?gender=genero

Igualmente se puede unificar la búsqueda con todos los parametros
- GET /movies?name=nombre&premiereDate=fecha&gender=genero

Ejemplo de querys:
  - GET /movies?name=avengers
  - GET /movies?year=2021
  - GET /movies?gender=ficcion
  - GET /movies?name=avengers&year=fecha&gender=genero
  
Creación, edición y eliminación de películas
Para las siguientes solicitudes es necesario estar autenticado y enviar el token de acceso en el header de la solicitud.

Debe enviarse con la key Authorization y la palabra "Bearer " + espacio, seguido del token de acceso, de la siguiente manera
Authorization: "Bearer token_access"

Ejemplo:
Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicml0b21sdXpAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NDUwNzUwMDR9.PGP9TyyKGAuP0bRQrYJLEoxx_HS_VyGxuiAp0_OD2WI"

Creación de película
Para crear una película es necesario enviar un objeto JSON con los atributos de la película

El endpoint es:
  - POST /movies
  {                          
      "image": "string",
      "title": "string",
      "duration": "string",
      "gender": "string",
      "director": "string"                                
      "year": "string"    
      "rating": "string"    
      "overview": "string"    
      }
                    
Ejemplo de solicitud:
  - POST /movies
Ejemplo de JSON a enviar:
  {
      "image": "string",
      "title": "string",
      "duration": "string",
      "gender": "string",
      "director": "string"                                
      "year": "string"    
      "rating": "string"    
      "overview": "string"    
      }
      
Edición de película
Para editar una película es necesario especificar el ID de la película en el path y se debe enviar un objeto JSON con los atributos de la película

El endpoint es:
  - PUT /movies/{id}
    {                         
        "image": "string",
        "title": "string",
        "duration": "string",
        "gender": "string",
        "director": "string"                                
        "year": "string"    
        "rating": "string"    
        "overview": "string"    
        }
                    
Ejemplo de solicitud:
  - PUT /movies/8
  
Ejemplo de JSON a enviar:
    {                        
        "image": "string",
        "title": "string",
        "duration": "string",
        "gender": "string",
        "director": "string"                                
        "year": "string"    
        "rating": "string"    
        "overview": "string"    
        }
                    
Eliminación de película
Para especificar la película que será eliminada es necesario enviar el ID de la película en el path

El endpoint es:
  - DELETE /movies/{id}

Ejemplo de solicitud:
  - DELETE /movies/3
  

**** ACTORES *****

Listado de actores

El listado muestra:
  - ID
  - Image
  - Name
  
El endpoint es:
  - GET /actors

Detalles de actor
En el detalle se muestran todos los atributos de los actores como asi tambien sus películas relacionados.
  - ID
  - Image
  - Name
  - Gender
  - Age
  - Movies
  
Para especificar el actor que se desea obtener se debe pasar el id como parametro:
  - GET /actors/{id}
  
Búsqueda de actores
Se puede actores por nombre, edad y género.
Para especificar el termino de búsqueda se deberán enviar como parámetros de query.
  - GET /actors?name=nombre
  - GET /actors?age=edad
  - GET /actors?gender=genero

Igualmente se puede unificar la búsqueda con todos los parametros
  - GET /actors?name=nombre&premiereDate=fecha&gender=genero

Ejemplo de querys:
  - GET /actors?name=avengers
  - GET /actors?age=40
  - GET /actors?gender=female/male
  - GET /actors?name=scarlett&age=&gender=
  
Creación de actores
Para esta solicitud es necesario estar autenticado y enviar el token de acceso en el header de la solicitud.
Debe enviarse con la key Authorization y la palabra "Bearer " + espacio, seguido del token de acceso, de la siguiente manera

Authorization: "Bearer token_access"
Ejemplo:
Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicml0b21sdXpAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NDUwNzUwMDR9.PGP9TyyKGAuP0bRQrYJLEoxx_HS_VyGxuiAp0_OD2WI"

Creación de actor
Para crear un actor es necesario enviar un objeto JSON con los atributos del mismo

El endpoint es:
  - POST /actors
    {                          
        "image": "string",
        "name": "string",                            
        "gender": "string",
        "age": "string",                            
        "movies": "string"    
        }
                    
Ejemplo de solicitud:
  - POST /actors
  
Ejemplo de JSON a enviar:
    {                          
        "image": "string",
        "name": "string",                            
        "gender": "string",
        "age": "string",                            
        "movies": "string"    
        }
        
***** DIRECTORES *****

Listado de directores
El listado muestra:

ID
Name
El endpoint es

  - GET /directors

Detalles de director
En el detalle se muestran todos los atributos de los directores como asi tambien sus películas relacionados.
  - ID
  - Name
  - Movies

Para especificar el director que se desea obtener se debe pasar el id como parametro:
- GET /directors/{id}
  
Creación de directores
Para esta solicitud es necesario estar autenticado y enviar el token de acceso en el header de la solicitud.
Debe enviarse con la key Authorization y la palabra "Bearer " + espacio, seguido del token de acceso, de la siguiente manera
Authorization: "Bearer token_access"
Ejemplo:
Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicml0b21sdXpAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NDUwNzUwMDR9.PGP9TyyKGAuP0bRQrYJLEoxx_HS_VyGxuiAp0_OD2WI"
  
Creación de director
Para crear un director es necesario enviar un objeto JSON con los atributos del mismo

El endpoint es:
  - POST /directors
    {   
        "name": "string"                                                     
        }
                    
Ejemplo de solicitud:
  - POST /directors
  
Ejemplo de JSON a enviar:
    {    
        "name": "string"    
        }
        
***** GENEROS *****
Listado de géneros

El listado muestra:
  - ID
  - Name

El endpoint es
  - GET /genders

Detalles de género
En el detalle se muestran todos los atributos de los géneros como asi tambien sus películas relacionados.
  - ID
  - Name
  - Movies

Para especificar el género que se desea obtener se debe pasar el id como parametro:
  - GET /genders/{id}

Creación de géneros
Para esta solicitud es necesario estar autenticado y enviar el token de acceso en el header de la solicitud.
Debe enviarse con la key Authorization y la palabra "Bearer " + espacio, seguido del token de acceso, de la siguiente manera
Authorization: "Bearer token_access"
Ejemplo:
Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicml0b21sdXpAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NDUwNzUwMDR9.PGP9TyyKGAuP0bRQrYJLEoxx_HS_VyGxuiAp0_OD2WI"

Creación de género
Para crear un género es necesario enviar un objeto JSON con los atributos del mismo

El endpoint es:
  - POST /genders
    {   
        "name": "string"                                                     
        }
                    
Ejemplo de solicitud:
  - POST /genders
Ejemplo de JSON a enviar:

    {    
        "name": "string"    
        }
