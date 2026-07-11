# language: es
Característica: Login Híbrido con API y UI

  Escenario: Bloqueo de login usando usuario real de la API
    Dado que obtengo un nombre de usuario desde la API de JSONPlaceholder
    Cuando intento hacer login en SauceDemo con ese usuario y la contraseña "clave_inventada"
    Entonces el sistema muestra un mensaje de error que contiene "Epic sadface"

  Escenario: Login negativo usando el email de la API como usuario
    Dado que obtengo el valor del atributo "email" desde el endpoint "/users/2" de la API
    Cuando intento hacer login en SauceDemo con ese valor como usuario y la contraseña "clave_inventada"
    Entonces el sistema muestra un mensaje de error que contiene "Epic sadface"

  Escenario: Login negativo usando el sitio web de la API como contraseña
    Dado que obtengo el valor del atributo "website" desde el endpoint "/users/1" de la API
    Cuando intento hacer login en SauceDemo con el usuario "standard_user" y ese valor como contraseña
    Entonces el sistema muestra un mensaje de error que contiene "Epic sadface"

  Escenario: Login negativo usando la compañía de la API como usuario
    Dado que obtengo el valor del atributo "company.name" desde el endpoint "/users/1" de la API
    Cuando intento hacer login en SauceDemo con ese valor como usuario y la contraseña "clave_inventada"
    Entonces el sistema muestra un mensaje de error que contiene "Epic sadface"

  Escenario: Checkout negativo usando el zipcode de la API como código postal
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Y que obtengo el valor del atributo "address.zipcode" desde el endpoint "/users/1" de la API
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y completo el checkout en SauceDemo usando ese valor como código postal, con nombre "" y apellido "Pérez"
    Entonces debería ver el mensaje de error en checkout que contiene "First Name is required"

  Escenario: Checkout negativo usando la calle de la API como nombre
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Y que obtengo el valor del atributo "address.street" desde el endpoint "/users/1" de la API
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y completo el checkout en SauceDemo usando ese valor como nombre, con apellido "Pérez" y código postal ""
    Entonces debería ver el mensaje de error en checkout que contiene "Postal Code is required"

  Escenario: Checkout negativo usando la ciudad de la API como apellido
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Y que obtengo el valor del atributo "address.city" desde el endpoint "/users/1" de la API
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y completo el checkout en SauceDemo usando ese valor como apellido, con nombre "Juan" y código postal ""
    Entonces debería ver el mensaje de error en checkout que contiene "Postal Code is required"

  Escenario: Login negativo con el título de un TODO de la API como usuario
    Dado que obtengo el valor del atributo "title" desde el endpoint "/todos" de la API
    Cuando intento hacer login en SauceDemo con ese valor como usuario y la contraseña "clave_inventada"
    Entonces el sistema muestra un mensaje de error que contiene "Epic sadface"

  Escenario: Checkout negativo con el título de un Album de la API como código postal
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Y que obtengo el valor del atributo "title" desde el endpoint "/albums" de la API
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y completo el checkout en SauceDemo usando ese valor como código postal, con nombre "" y apellido "Pérez"
    Entonces debería ver el mensaje de error en checkout que contiene "First Name is required"

  Escenario: Checkout negativo con el título de un Post de la API como apellido
    Dado que el usuario ha iniciado sesión correctamente con "standard_user" y "secret_sauce"
    Y que obtengo el valor del atributo "title" desde el endpoint "/posts" de la API
    Cuando añade la mochila "Sauce Labs Backpack" al carrito
    Y completo el checkout en SauceDemo usando ese valor como apellido, con nombre "Juan" y código postal ""
    Entonces debería ver el mensaje de error en checkout que contiene "Postal Code is required"