package starter.step_definitions;
import dtos.Usuario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static questions.GetValueFromResponseBodyQuestion.*;

//clase para llamar a la API HTTP
public class RegisterStep extends UIInteractions { //clase que viene con Serenity BDD para ayudarnos a interactuar con las API.
    private Usuario usuario;

    Faker faker= new Faker();
    String username = faker.name().username();
   String clave = faker.internet().password();

    @Given("{actor} registra un usuario no registrado en sistema")
    public void registarNoUsuario(Actor actor) {
        usuario = new Usuario(username, clave, List.of("user"));
    }

    @When("{actor} Invoco el servicio de registro")
    public void validarServicio(Actor actor) {
        actor.attemptsTo(RegisterTask.withData(usuario));
    }

    @Then("{actor} Obtengo un status con codigo {int}")
    public void validarStatus(Actor actor, int status) {
        actor.should(seeThat("Código respuesta ", StatusCode.code(), equalTo(status)));
    }

    @And("{actor} La respuesta contiene los datos del usuario")
    public void laRespuestaContieneUnUsuarioConLosDatosEnviados(Actor actor) {
        //then().body("usuario", Matchers.equalTo(usuario.usuario()));
        //usuario.roles().forEach(rol-> then().body("roles",Matchers.contains(rol)));
//        var question = ResponseScheme.get();

        actor.should(
                seeThat(theAttributeValue("usuario"),equalTo(usuario.usuario())),
                seeThat(theAttributeValue("roles").asListOf(String.class),equalTo(usuario.roles()))
        );
//        actor.should(
//                seeThat(question,hasProperty("usuario",equalTo(usuario.usuario()))),
//                seeThat(question,hasProperty("roles",equalTo(usuario.roles())))
//        );
    }

    @Given("{actor} Soy un usuario ya registrado en sistema")
    public void soy_un_usuario_ya_registrado_en_sistema(Actor actor) {
        usuario = new Usuario("pecos14", "pecos", Set.of("user"));
    }

    @Given("{actor} Soy un usuario que me registro con campo faltante")
    public void soy_un_usuario_que_me_registro_con_campo_faltante(Actor actor) {
        usuario = new Usuario("", "pecos", Set.of("user"));
    }

}