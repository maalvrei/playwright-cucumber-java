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