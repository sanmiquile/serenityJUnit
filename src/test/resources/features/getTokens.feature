Feature: Generacion lista de tokens

  Scenario: Generar lista de tokens exitoso
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    When El admin solicita el listado del tokens
    Then El admin obtiene un status con codigo 200
    And El admin observa que la estructura cumple con el formato de "Tokens"


  Scenario: Generar lista de tokens user no autorizado
    Given El user no esta autenticado
    When El user solicita el listado del tokens
    Then El user obtiene un status con codigo 401
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Generar lista token usuario sin permiso realizar la operación
    Given existe un user registrado en el sistema
    And El user esta autenticado en el sistema
    When El user solicita el listado del tokens
    Then El user obtiene un status con codigo 403
    And  El user observa que la estructura cumple con el formato de "Error"







