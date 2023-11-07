Feature: Registro de usuarios

  Scenario: Registrar usuario
    Given Soy un usuario no registrado en sistema
    When Invoco el servicio de registro
    Then Obtengo un status con codigo 201
    And La respuesta contiene un usuario con los datos enviados

