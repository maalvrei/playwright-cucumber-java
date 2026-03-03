# language: es
Característica: Gestión de usuarios vía API

  Escenario: Crear un usuario aleatorio correctamente en el sistema
    Dado que preparo los datos de un usuario con nombre y trabajo aleatorios
    Cuando envío la petición POST a la API de JSONPlaceholder
    Entonces el código de respuesta de la API debería ser 201
    Y la respuesta de la API debería contener el nombre generado