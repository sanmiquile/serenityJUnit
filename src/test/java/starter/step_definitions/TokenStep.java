package starter.step_definitions;
import dtos.UsuarioLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import org.hamcrest.Matchers;
import questions.ResponseToken;
import questions.TokenGenerationValidation;
import task.TokenTask;
import util.DataProvider;
import util.DataShared;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class TokenStep {

    @Shared
    private DataProvider dataProvider;
    @Shared
    private DataShared dataShared;




    @When("El {actor} solicita la generación del token")
    public void el_admin_solicita_la_generación_del_token(Actor actor) {
        actor.attemptsTo(TokenTask.withData( dataShared.credenciales ));
    }
    @Then("El {actor} recibe respuesta que contiene el código del token")
    public void el_admin_recibe_respuesta_que_contiene_el_código_del_token(Actor actor) {
        actor.should( seeThat(ResponseToken.get(), Matchers.not( Matchers.emptyString() )) );
    }
    @And("El {actor} observa que la estructura cumple con el formato de {string}")
    public void elUsuarioObservaQueLaEstructuraCumpleConElFormato(Actor actor, String schema) {
        actor.should( seeThat(TokenGenerationValidation.theTokenSchemaIs("schema"+schema)));

    }

    @Given("El {actor} ingresa datos incompletos de login")
    public void el_usuario_ingresa_datos_incompletos_de_login(Actor actor) {
            dataShared.credenciales = new UsuarioLogin(dataShared.usuario.usuario(), "");
    }
    @Given("El {actor} ingresa datos incorrectos de login")
    public void el_usuario_ingresa_datos_incorrectos_de_login(Actor actor) {
        dataShared.credenciales = new UsuarioLogin(dataShared.usuario.usuario(), dataProvider.getPassword());
    }

}
