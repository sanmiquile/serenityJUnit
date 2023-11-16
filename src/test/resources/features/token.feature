Feature: Generacion de token

  Scenario: Generar token exitoso
    Given existe un usuario registrado en el sistema
    When El usuario solicita la generación del token
    Then El usuario obtiene un status con codigo 201
    And El usuario recibe respuesta que contiene el código del token
    And El usuario observa que la estructura cumple con el formato de "Token"

  Scenario: Generar token datos incompletos
    Given existe un usuario registrado en el sistema
    And El usuario ingresa datos incompletos de login
    When El usuario solicita la generación del token
    Then El usuario obtiene un status con codigo 400
    And El usuario recibe respuesta que contiene el código del token
    And  El usuario observa que la estructura cumple con el formato de "Error"


  Scenario: Generar token datos incorrectos
    Given existe un usuario registrado en el sistema
    And El usuario ingresa datos incorrectos de login
    When El usuario solicita la generación del token
    Then El usuario obtiene un status con codigo 401
    And El usuario recibe respuesta que contiene el código del token
    And  El usuario observa que la estructura cumple con el formato de "Error"