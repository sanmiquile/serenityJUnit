Feature: Registro de usuarios

  Scenario: Registrar usuario exitoso
    Given Soy un usuario no registrado en sistema
    When Invoco el servicio de registro
    Then Obtengo un status con codigo 201
    And La respuesta contiene un usuario con los datos enviados

  Scenario: Registrar usuario duplicado
    Given Soy un usuario ya registrado en sistema
    When Invoco el servicio de registro
    Then Obtengo un status con codigo 409


  Scenario: Registrar usuario campo faltante
    Given Soy un usuario que me registro con campo faltante
    When Invoco el servicio de registro
    Then Obtengo un status con codigo 400

  Scenario: Registrar usuario error interno
    Given Soy un usuario no registrado en sistema
    When Invoco el servicio de registro
    Then Obtengo un status con codigo 500


