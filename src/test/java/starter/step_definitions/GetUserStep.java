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
    public void elAdminSolicitaInformacionDeUnUsuario(Actor actor)
    {
        actor.attemptsTo(GetUserTask.withData("admin"));
    }

    @And("{actor} obtiene los datos del usuario")
    public void elAdminObtieneLosDatosDelUsuario(Actor actor) {
        actor.should(
                seeThat(theAttributeValue("usuario"),equalTo("admin")),
                seeThat(theAttributeValue("roles").asListOf(String.class),equalTo(List.of("admin")))
                // TODO crear el datamager
        );
    }
    @Given("{actor} no esta autorizado en el sistema")
    public void el_admin_no_esta_autorizado_en_el_sistema(Actor actor) {
        //actor.attemptsTo(TokenTask.withData(new UsuarioLogin("laura","laura")));
        actor.remember("token","");
    }

    @Given("{actor} autenticado no tiene permisos")
    public void el_admin_autenticado_no_tiene_permisos(Actor actor) {
        actor.attemptsTo(TokenTask.withData(new UsuarioLogin("pecos14","pecos")));
    }

    @When("{actor} solicita informacion de usuario no existente")
    public void el_admin_solicita_informacion_en_un_recursos_no_existente(Actor actor) {
            actor.attemptsTo(GetUserTask.withData("ggfssd"));
    }
}
