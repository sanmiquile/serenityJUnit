package starter.step_definitions;

import dtos.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import task.RegisterTask;
import task.UpdateUserTask;

import java.util.List;

public class UpdateUserStep {

    private Usuario usuario;
    private Usuario usuario2;
    @Given("existe un usuario registrado en el sistema")
    public void existe_un_usuario_registrado_en_el_sistema() {
        usuario = new Usuario("sandra6", "12345", List.of("user"));
        Actor.named("admin").attemptsTo(RegisterTask.withData(usuario));
    }

    @When("{actor} solicita actualizar el nombre de un usuario")
    public void el_admin_solicita_actualizar_el_nombre_de_un_usuario(Actor actor) {
        // Write code here that turns the phrase above into concrete actions
        usuario2 = new Usuario(usuario.usuario(), "sandra", usuario.roles());
        actor.attemptsTo(UpdateUserTask.withData(usuario.usuario(),usuario2));
    }
}
