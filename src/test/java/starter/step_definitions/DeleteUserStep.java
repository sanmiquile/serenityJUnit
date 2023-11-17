package starter.step_definitions;

import dtos.Usuario;
import dtos.UsuarioIncompleto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import task.DeleteUserTask;
import task.RegisterTask;
import task.UpdateUserTask;
import util.DataProvider;
import util.DataShared;

public class DeleteUserStep {

    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    @When("El {actor} solicita eliminar un usuario")
    public void el_admin_solicita_eliminar_un_usuario(Actor actor) {
        actor.attemptsTo(DeleteUserTask.withData(dataShared.usuario.usuario()));
    }

}
