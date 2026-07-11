# language: es
Característica: Login en SauceDemo

  Escenario: Usuario intenta hacer login con credenciales inválidas
    Dado que el usuario está en la página de login de SauceDemo
    Cuando introduce el usuario "usuario_falso" y la contraseña "clave_inventada"
    Entonces debería ver un mensaje de error que contiene "Epic sadface"

  Escenario: Usuario bloqueado intenta iniciar sesión
    Dado que el usuario está en la página de login de SauceDemo
    Cuando introduce el usuario "locked_out_user" y la contraseña "secret_sauce"
    Entonces debería ver un mensaje de error que contiene "Sorry, this user has been locked out"