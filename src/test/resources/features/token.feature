Feature: Generacion de token

  Scenario: Generar token exitoso
    Given El usuario esta registrado en el sistema
    When El usuario solicita la generación del token
    Then El usuario Obtengo un status con codigo 201
    And El usuario recibe respuesta que contiene el código del token
    And El usuario observa que la estructura cumple con el formato de "Token"

  Scenario: Generar token datos incompletos
    Given El usuario ingresa datos incompletos de login
    When El usuario solicita la generación del token
    Then El usuario Obtengo un status con codigo 400
    And El usuario recibe respuesta que contiene el código del token

  Scenario: Generar token datos incorrectos
    Given El usuario ingresa datos incorrectos de login
    When El usuario solicita la generación del token
    Then El usuario Obtengo un status con codigo 401
    And El usuario recibe respuesta que contiene el código del token