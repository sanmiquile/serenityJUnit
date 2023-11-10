Feature: Generacion de token

  Scenario: Get user exitoso
    Given El admin esta autenticado en el sistema
    When El admin solicita informacion de un usuario
    Then El admin Obtengo un status con codigo 200
    And El admin obtiene los datos del usuario
