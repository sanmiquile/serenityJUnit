Feature: Registro de usuarios

  Scenario: Registrar usuario exitoso
    Given El usuario registra un usuario no registrado en sistema
    When El usuario Invoco el servicio de registro
    Then El usuario Obtengo un status con codigo 201
    And El usuario La respuesta contiene los datos del usuario
    And El usuario observa que la estructura cumple con el formato de "User"

  Scenario: Registrar usuario duplicado
    Given El usuario Soy un usuario ya registrado en sistema
    When El usuario Invoco el servicio de registro
    Then El usuario Obtengo un status con codigo 409
    And  El usuario observa que la estructura cumple con el formato de "Error"


  Scenario: Registrar usuario campo faltante
    Given El usuario Soy un usuario que me registro con campo faltante
    When El usuario Invoco el servicio de registro
    Then El usuario Obtengo un status con codigo 400

  Scenario: Registrar usuario error interno
    Given El usuario registra un usuario no registrado en sistema
    When El usuario Invoco el servicio de registro
    Then El usuario Obtengo un status con codigo 500


