Feature: Registro de usuarios

  Scenario: Registrar usuario exitoso
    Given El usuario no esta registrado en el sistema
    When El usuario Invoca el servicio de registro
    Then El usuario obtiene un status con codigo 201
    And El usuario La respuesta contiene los datos del usuario
    And El usuario observa que la estructura cumple con el formato de "User"

  Scenario: Registrar usuario duplicado
    Given existe un usuario registrado en el sistema
    #Given El usuario Soy un usuario ya registrado en sistema
    When El usuario Invoca el servicio de registro
    Then El usuario obtiene un status con codigo 409
    And  El usuario observa que la estructura cumple con el formato de "Error"


  Scenario: Registrar usuario campo faltante
    Given El usuario Soy un usuario que me registro con campo faltante
    When El usuario Invoca el servicio de registro
    Then El usuario obtiene un status con codigo 400
    And  El usuario observa que la estructura cumple con el formato de "Error"

  Scenario: Registrar usuario error interno
    Given El usuario no esta registrado en el sistema
    When El usuario Invoca el servicio de registro
    Then El usuario obtiene un status con codigo 500


