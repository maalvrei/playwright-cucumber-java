# language: es
Característica: Gestión del inventario y carrito en SauceDemo

  Escenario: Usuario inicia sesión con credenciales correctas
    Dado que el usuario está en la página de login de SauceDemo
    Cuando introduce el usuario "standard_user" y la contraseña "secret_sauce"
    Entonces debería ser redirigido a la página principal del inventario

  Escenario: Añadir un producto al carrito correctamente
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Entonces el icono del carrito debería mostrar un "1"