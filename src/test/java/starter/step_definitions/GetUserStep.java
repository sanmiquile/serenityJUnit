package starter.step_definitions;

import dtos.UsuarioLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import task.GetUserTask;
import task.TokenTask;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static questions.GetValueFromResponseBodyQuestion.theAttributeValue;

public class GetUserStep {
    @Given("{actor} esta autenticado en el sistema")
    public void elAdminEstaAutenticadoEnElSistema(Actor actor) {
        actor.attemptsTo(TokenTask.withData(new UsuarioLogin("admin","admin")));
    }

    @When("{actor} solicita informacion de un usuario")
    public void elAdminSolicitaInformacionDeUnUsuario(Actor actor) {
        actor.attemptsTo(GetUserTask.withData("admin"));
    }

    @And("{actor} obtiene los datos del usuario")
    public void elAdminObtieneLosDatosDelUsuario(Actor actor) {
        actor.should(
                seeThat(theAttributeValue("usuario"),equalTo("admin")),
                seeThat(theAttributeValue("roles").asListOf(String.class),equalTo(List.of("admin")))
        );
    }
}
