Feature: Consulta de un usuario registrado

  Scenario: Get user exitoso
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    When El admin solicita informacion de "el"
    Then El admin obtiene un status con codigo 200
    And El admin obtiene los datos del usuario
    And El admin observa que la estructura cumple con el formato de "User"

  Scenario: Get user no autorizado
    Given El user no esta autenticado
    When El user solicita informacion de "el"
    Then El user obtiene un status con codigo 401
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Get user Sin permiso realizar la operación
    Given existe un user registrado en el sistema
    And El user esta autenticado en el sistema
    When El user solicita informacion de "admin"
    Then El user obtiene un status con codigo 403
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Get user Recurso no encontrado
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    When El admin solicita informacion de usuario no existente
    Then El admin obtiene un status con codigo 404
    And  El admin observa que la estructura cumple con el formato de "Error"