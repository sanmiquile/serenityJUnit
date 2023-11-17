package starter.step_definitions;
import dtos.Usuario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Set;
import static net.serenitybdd.rest.SerenityRest.then;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import com.github.javafaker.Faker;
import questions.ResponseScheme;
import questions.StatusCode;
import task.RegisterTask;
import util.DataProvider;
import util.DataShared;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static questions.GetValueFromResponseBodyQuestion.*;

//clase para llamar a la API HTTP
public class RegisterStep extends UIInteractions { //clase que viene con Serenity BDD para ayudarnos a interactuar con las API.
    @Shared
    private DataShared dataShared;

    @Shared
    private DataProvider dataProvider;
    @Given("El {actor} no esta registrado en el sistema")
    public void registarNoUsuario(Actor actor) {
        dataShared.usuario = dataProvider.getUser();
    }

    @When("El {actor} Invoca el servicio de registro")
    public void validarServicio(Actor actor) {
        actor.attemptsTo(RegisterTask.withData(dataShared.usuario));
    }

    @Then("El {actor} obtiene un status con codigo {int}")
    public void validarStatus(Actor actor, int status) {
        actor.should(seeThat("Código respuesta ", StatusCode.code(), equalTo(status)));
    }

    @And("El {actor} La respuesta contiene los datos del usuario")
    public void laRespuestaContieneUnUsuarioConLosDatosEnviados(Actor actor) {

        actor.should(
                seeThat(theAttributeValue("usuario"),equalTo(dataShared.usuario.usuario())),
                seeThat(theAttributeValue("roles").asListOf(String.class),equalTo(dataShared.usuario.roles()))
        );
    }

    @Given("El {actor} Soy un usuario ya registrado en sistema")
    public void soy_un_usuario_ya_registrado_en_sistema(Actor actor) {
        dataShared.usuario = new Usuario("pecos15", "pecos", Set.of("user"));
    }

    @Given("El {actor} Soy un usuario que me registro con campo faltante")
    public void soy_un_usuario_que_me_registro_con_campo_faltante(Actor actor) {
        dataShared.usuario = dataProvider.getUserWithOutUsername();
    }

}