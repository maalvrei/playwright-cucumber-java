# language: es
Característica: Gestión de usuarios vía API

  Escenario: Crear un usuario correctamente en el sistema
    Dado que preparo los datos del usuario "Miguel QA" con el trabajo "Automation Engineer"
    Cuando envío la petición POST a la API de JSONPlaceholder
    Entonces el código de respuesta de la API debería ser 201
    Y la respuesta de la API debería contener el nombre "Miguel QA"