Feature: Actualizar un usuario registrado

  Scenario: Update user exitoso
    Given existe un usuario registrado en el sistema
    And El admin esta autenticado en el sistema
    When El admin solicita actualizar el nombre de un usuario
    Then El admin obtiene un status con codigo 200
#    And El admin obtiene los datos del usuario
    And El admin observa que la estructura cumple con el formato de "User"

  Scenario: Update user datos incompletos
    Given El user no esta autorizado en el sistema
    When El user solicita actualizar el nombre de un usuario
    Then El user obtiene un status con codigo 400
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Update user no autorizado
    Given El user no esta autorizado en el sistema
    When El user solicita actualizar el nombre de un usuario
    Then El user obtiene un status con codigo 401
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Update user Sin permiso realizar la operación
    Given El user autenticado no tiene permisos
    When El user solicita actualizar el nombre de un usuario
    Then El user obtiene un status con codigo 403
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Update user Recurso no encontrado
    Given El admin esta autenticado en el sistema
    When El admin solicita actualizar el nombre de un usuario
    Then El admin obtiene un status con codigo 404
    And  El admin observa que la estructura cumple con el formato de "Error"