# language: es
Característica: Pruebas de API en JSONPlaceholder

  Escenario: Crear un usuario con datos dinámicos (POST)
    Dado que preparo los datos de un usuario con nombre y trabajo aleatorios
    Cuando envío la petición POST a la API de JSONPlaceholder
    Entonces el código de respuesta de la API debería ser 201
    Y la respuesta de la API debería contener el nombre generado

  Escenario: Obtener un post existente y validar su estructura (GET)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición GET al endpoint "/posts/1"
    Entonces el código de respuesta de la API debería ser 200
    Y el cuerpo de la respuesta en formato JSON debería contener el atributo "title"

  Escenario: Eliminar un post existente (DELETE)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición DELETE al endpoint "/posts/1"
    Entonces el código de respuesta de la API debería ser 200

  Escenario: Actualizar un post existente (PUT)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición PUT al endpoint "/posts/1"
    Entonces el código de respuesta de la API debería ser 200

  Escenario: Listar todos los posts (GET)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición GET al endpoint "/posts"
    Entonces el código de respuesta de la API debería ser 200
    Y el cuerpo de la respuesta en formato JSON debería contener el atributo "title"

  Escenario: Obtener comentarios de un post específico (GET)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición GET al endpoint "/posts/1/comments"
    Entonces el código de respuesta de la API debería ser 200
    Y el cuerpo de la respuesta en formato JSON debería contener el atributo "email"

  Escenario: Filtrar comentarios por query param (GET)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición GET al endpoint "/comments?postId=1"
    Entonces el código de respuesta de la API debería ser 200
    Y el cuerpo de la respuesta en formato JSON debería contener el atributo "body"

  Escenario: Modificar parcialmente un post existente (PATCH)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición PATCH al endpoint "/posts/1" con el título "Nuevo Titulo Editado"
    Entonces el código de respuesta de la API debería ser 200
    Y la respuesta de la API debería contener el texto "Nuevo Titulo Editado"

  Escenario: Intentar obtener un post inexistente (GET - Caso Negativo)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición GET al endpoint "/posts/999"
    Entonces el código de respuesta de la API debería ser 404

  Escenario: Listar todos los usuarios y validar presencia (GET)
    Dado que la URL base de la API es "https://jsonplaceholder.typicode.com"
    Cuando envío una petición GET al endpoint "/users"
    Entonces el código de respuesta de la API debería ser 200
    Y el cuerpo de la respuesta en formato JSON debería contener el atributo "username"