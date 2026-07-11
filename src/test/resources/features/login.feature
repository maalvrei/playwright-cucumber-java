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

  Escenario: Usuario intenta iniciar sesión con contraseña vacía
    Dado que el usuario está en la página de login de SauceDemo
    Cuando introduce el usuario "standard_user" y la contraseña ""
    Entonces debería ver un mensaje de error que contiene "Password is required"

  Escenario: Cierre de sesión exitoso desde el catálogo
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Cuando cierra la sesión
    Entonces debería ser redirigido a la página de login