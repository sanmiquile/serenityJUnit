package starter.step_definitions;
import dtos.UsuarioLogin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.hamcrest.Matchers;
import questions.ResponseToken;
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
        // Write code here that turns the phrase above into concrete actions
        actor.should( seeThat(ResponseToken.get(), Matchers.not( Matchers.emptyString() )) );
    }



}
