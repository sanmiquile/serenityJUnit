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
import task.GetTokensTask;
import task.TokenTask;
import util.DataProvider;
import util.DataShared;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class GetTokensStep {

    @Shared
    private DataProvider dataProvider;
    @Shared
    private DataShared dataShared;

    @When("El {actor} solicita el listado del tokens")
    public void el_admin_solicita_el_listado_del_tokens(Actor actor) {
        actor.attemptsTo(GetTokensTask.withData(  ));
    }
    @Then("El {actor} recibe respuesta que contiene la lista de tokens")
    public void el_admin_recibe_respuesta_que_contiene_la_lista_de_tokens(Actor actor) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
