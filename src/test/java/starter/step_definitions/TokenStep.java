package starter.step_definitions;
import dtos.UsuarioLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.hamcrest.Matchers;
import questions.ResponseToken;
import questions.TokenGenerationValidation;
import task.TokenTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class TokenStep {

    private UsuarioLogin usuarioLogin;

    @Given("{actor} esta registrado en el sistema")
    public void el_esta_registrado(Actor actor) {
        usuarioLogin = new UsuarioLogin("admin", "admin");
    }
    @When("{actor} solicita la generación del token")
    public void el_admin_solicita_la_generación_del_token(Actor actor) {
        actor.attemptsTo(TokenTask.withData(usuarioLogin));
    }
    @Then("{actor} recibe respuesta que contiene el código del token")
    public void el_admin_recibe_respuesta_que_contiene_el_código_del_token(Actor actor) {
        actor.should( seeThat(ResponseToken.get(), Matchers.not( Matchers.emptyString() )) );
    }
    @And("{actor} observa que la estructura cumple con el formato de {string}")
    public void elUsuarioObservaQueLaEstructuraCumpleConElFormato(Actor actor, String schema) {
        actor.should( seeThat(TokenGenerationValidation.theTokenSchemaIs("schema"+schema)));

    }

    @Given("{actor} ingresa datos incompletos de login")
    public void el_usuario_ingresa_datos_incompletos_de_login(Actor actor) {
            usuarioLogin = new UsuarioLogin("admin", "");
    }
    @Given("{actor} ingresa datos incorrectos de login")
    public void el_usuario_ingresa_datos_incorrectos_de_login(Actor actor) {
        usuarioLogin = new UsuarioLogin("admin", "12345");
    }

}
