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

  Escenario: Eliminar un producto del carrito
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y elimina la mochila del carrito
    Entonces el icono del carrito debería mostrar un "0"

  Escenario: Completar proceso de compra exitoso
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y se dirige al carrito y hace clic en Checkout
    Y completa los datos de envío con "Juan", "Pérez" y "28001"
    Y hace clic en Continuar
    Y hace clic en Finalizar
    Entonces debería ver el mensaje de éxito "Thank you for your order!"

  Escenario: Intentar realizar checkout con código postal vacío
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y se dirige al carrito y hace clic en Checkout
    Y completa los datos de envío con "Juan", "Pérez" y ""
    Y hace clic en Continuar
    Entonces debería ver el mensaje de error en checkout que contiene "Postal Code is required"

  Escenario: Intentar realizar checkout con nombre vacío
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y se dirige al carrito y hace clic en Checkout
    Y completa los datos de envío con "", "Pérez" y "28001"
    Y hace clic en Continuar
    Entonces debería ver el mensaje de error en checkout que contiene "First Name is required"