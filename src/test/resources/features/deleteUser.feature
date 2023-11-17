Feature: Eliminar un usuario registrado

  Scenario: Eliminar usuario exitosamente
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    And existe un usuario registrado en el sistema
    When El admin solicita eliminar un usuario
    Then El admin obtiene un status con codigo 204

  Scenario: Delete user no autorizado
    Given existe un usuario registrado en el sistema
    And El user no esta autenticado
    When El user solicita eliminar un usuario
    Then El user obtiene un status con codigo 401
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Delete user Sin permiso realizar la operación
    Given existe un user registrado en el sistema
    And El user esta autenticado en el sistema
    And existe un user registrado en el sistema
    When El user solicita eliminar un usuario
    Then El user obtiene un status con codigo 403
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Delete user Recurso no encontrado
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    And El user no esta registrado en el sistema
    When El admin solicita eliminar un usuario
    Then El admin obtiene un status con codigo 404
    And  El admin observa que la estructura cumple con el formato de "Error"