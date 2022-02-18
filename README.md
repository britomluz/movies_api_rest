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

Para loguearse se puede utilizar las siguientes credenciales:
  - Email: britomluz@gmail.com
  - Password: luz123
  
ACLARACIÓN: todas las solicitudes GET son de acceso público por lo que no se necesita enviar el token de acceso.

*Registro de usuarios*
También se puede registrar un usuario nuevo para posteriormente loguearse y obtener el token de acceso.

El endpoint encargado del registro es:
  - POST /users
  
Para esta solicitud es necesario enviar un objeto JSON con los atributos del usuario:
El JSON debe tener la siguiente estructura:
    {                          
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string"    
        }
                    
Ejemplo de solicitud:
  - POST /users
    {                          
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string"    
        }
                    
*REST API*
Para ingresar a la API via rest se debe contar con permisos de administrador, el endpoint es:

  - /rest
Ejemplo de solicitud:
  - /rest/users
  - /rest/movies
  - /rest/actors
  - /rest/directors
  - /rest/genders

*Swagger*
Para el registro automático de los endpoints se integro swagger
Puedes ir haciendo click en este enlace: 
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

***** PELICULAS *****

*Listado de películas:*
El listado muestra:
  - ID
  - Image
  - Year
  - Title
  
El endpoint es
  -GET /movies
  
*Detalles de película:*
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
  
El número de página debe especificarse por parámetro:
  - GET /movies?page=numPage
  - 
El orden debe especificarse por parámetro:
  - GET /movies?sort=atributo,asc|desc
  - 
Ejemplo de solicitud:
  - GET /movies?page=0&sort=titulo,desc
  
*Búsqueda de películas:*
Se puede buscar peliculas por título y/o año.

Para especificar el termino de búsqueda se deberán enviar como parámetros de query.
  - GET /movies?name=nombre
  - GET /movies?year=año  

Igualmente se puede unificar la búsqueda con todos los parametros
- GET /movies?name=nombre&premiereDate=fecha&gender=genero

Ejemplo de querys:
  - GET /movies?title=twilight
  - GET /movies?year=2021  
  - GET /movies?name=twilight&year=2013
  
*Creación, edición y eliminación de películas*
Para las siguientes solicitudes es necesario estar autenticado y enviar el token de acceso en el header de la solicitud.

Debe enviarse con la key Authorization y la palabra "Bearer " + espacio, seguido del token de acceso, de la siguiente manera
Authorization: "Bearer token_access"

Ejemplo:
Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicml0b21sdXpAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NDUwNzUwMDR9.PGP9TyyKGAuP0bRQrYJLEoxx_HS_VyGxuiAp0_OD2WI"

*Creación de película*
Para crear una película es necesario enviar un objeto JSON con los atributos de la película

El endpoint es:
  - POST /movies
  {                          
      "image": "string(url)",
      "title": "string",
      "duration": "string",
      "gender": "string",
      "director": "string"                                
      "year": "string(yyyy-mm-dd)"    
      "rating": "string(del 1 al 5)"    
      "overview": "string"    
      }
                    
Ejemplo de solicitud:
  - POST /movies
Ejemplo de JSON a enviar:
  {
      "image": "https://www.themoviedb.org/movie/13995-captain-america#",
      "title": "CAPTAIN AMERICA",
      "duration": "1h 58m",
      "gender": "FICCION",
      "director": "STEPHEN TOLKIN"                                
      "year": "1990-12-14"    
      "rating": "3.8"    
      "overview": "During World War II, a brave, patriotic American Soldier undergoes experiments to become a new supersoldier, "Captain America". Racing to Germany to sabotage the rockets of Nazi baddie "Red Skull"."    
      }
      
*Edición de película:*
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
      "image": "https://www.themoviedb.org/movie/49018-insidious#",
      "title": "INSIDIOUS",
      "duration": "1h 30m",
      "gender": "TERROR",
      "director": "JAMES WAN"                                
      "year": "2018-01-25"    
      "rating": "4.5"    
      "overview": "A family discovers that dark spirits have invaded their home."    
      }
                    
*Eliminación de película:*
Para especificar la película que será eliminada es necesario enviar el ID de la película en el path

El endpoint es:
  - DELETE /movies/{id}

Ejemplo de solicitud:
  - DELETE /movies/3
  

**** ACTORES *****

*Listado de actores*
El listado muestra:
  - ID
  - Image
  - Name
  
El endpoint es:
  - GET /actors

El número de página debe especificarse por parámetro:
  - GET /actors?page=numPage
  
El orden debe especificarse por parámetro:
  - GET /actors?sort=atributo,asc|desc
  - 
Ejemplo de solicitud:
  - GET /actors?page=0&sort=edad,desc

*Detalles de actor*
En el detalle se muestran todos los atributos de los actores como asi tambien sus películas relacionados.
  - ID
  - Image
  - Name
  - Gender
  - Age
  - Movies
  
Para especificar el actor que se desea obtener se debe pasar el id como parametro:
  - GET /actors/{id}
  
*Búsqueda de actores*
Se puede actores por nombre y/o género. Genero debera ser female = 0 o male = 1
Para especificar el termino de búsqueda se deberán enviar como parámetros de query.
  - GET /actors?name=nombre  
  - GET /actors?gender=0|1

Ejemplo de querys:
  - GET /actors?name=avengers
  - GET /actors?age=40
  - GET /actors?gender=0  
  
*Creación de actores:*
Para esta solicitud es necesario estar autenticado y enviar el token de acceso en el header de la solicitud.
Debe enviarse con la key Authorization y la palabra "Bearer " + espacio, seguido del token de acceso, de la siguiente manera

Authorization: "Bearer token_access"
Ejemplo:
Authorization: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicml0b21sdXpAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NDUwNzUwMDR9.PGP9TyyKGAuP0bRQrYJLEoxx_HS_VyGxuiAp0_OD2WI"

*Creación de actor:*
Para crear un actor es necesario enviar un objeto JSON con los atributos del mismo

El endpoint es:
  - POST /actors
    {                          
        "image": "string",
        "name": "string",                            
        "gender": "string",
        "age": "string"   
        }
                    
Ejemplo de solicitud:
  - POST /actors
  
Ejemplo de JSON a enviar:
    {                          
        "image": "https://www.themoviedb.org/person/90633-gal-gadot#",
        "name": "Gal Gadot",                            
        "gender": "FEMALE",
        "age": "36"    
        }
        
***** DIRECTORES *****

*Listado de directores*
El listado muestra:
ID
Name
El endpoint es
  - GET /directors

*Detalles de director*
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
        "name": "JAMES HUNT"    
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
        "name": "JUVENIL"    
        }
