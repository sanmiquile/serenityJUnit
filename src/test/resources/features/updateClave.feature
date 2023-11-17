Feature: Actualizar clave de un usuario

  Scenario: Update password user exitoso
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    And existe un usuario registrado en el sistema
    When El admin solicita actualizar la clave de un usuario
    Then El admin obtiene un status con codigo 200
    And El admin obtiene los datos del usuario
    And El admin observa que la estructura cumple con el formato de "User"

  Scenario: Update user datos incompletos
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    And existe un usuario registrado en el sistema
    When El admin solicita actualizar la clave de un usuario con datos incompletos
    Then El admin obtiene un status con codigo 400
    And  El admin observa que la estructura cumple con el formato de "Error"

  Scenario: Update user no autorizado
    Given existe un user registrado en el sistema
    And El user no esta autenticado
    When El user solicita actualizar la clave de un usuario
    Then El user obtiene un status con codigo 401
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Update user Sin permiso realizar la operación
    Given existe un user registrado en el sistema
    And El user esta autenticado en el sistema
    And existe un user registrado en el sistema
    When El user solicita actualizar la clave de un usuario
    Then El user obtiene un status con codigo 403
    And  El user observa que la estructura cumple con el formato de "Error"

  Scenario: Update user Recurso no encontrado
    Given existe un admin registrado en el sistema
    And El admin esta autenticado en el sistema
    And El user no esta registrado en el sistema
    When El admin solicita actualizar la clave de un usuario
    Then El admin obtiene un status con codigo 404
    And  El admin observa que la estructura cumple con el formato de "Error"