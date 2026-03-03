# language: es
Característica: Login Híbrido con API y UI

  Escenario: Bloqueo de login usando usuario real de la API
    Dado que obtengo un nombre de usuario desde la API de JSONPlaceholder
    Cuando intento hacer login en SauceDemo con ese usuario y la contraseña "clave_inventada"
    Entonces el sistema muestra un mensaje de error que contiene "Epic sadface"