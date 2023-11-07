package starter.step_Definitions;
import dtos.Usuario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.core.steps.UIInteractions;
import org.hamcrest.Matchers;
import java.util.Set;
import static net.serenitybdd.rest.SerenityRest.*;
import com.github.javafaker.Faker;

//clase para llamar a la API HTTP
public class RegisterStep extends UIInteractions { //clase que viene con Serenity BDD para ayudarnos a interactuar con las API.
    private Usuario usuario;
    Faker faker= new Faker();
    String username = faker.name().username();
    String clave = faker.internet().password();

    @Given("Soy un usuario no registrado en sistema")
    public void registarNoUsuario() {
        usuario = new Usuario(username, clave, Set.of("user"));
    }

    @When("Invoco el servicio de registro")
    public void validarServicio() {
        given().baseUri("http://localhost:8090/api")
                .basePath("/usuarios")
                .body(usuario, ObjectMapperType.GSON)
                .contentType(ContentType.JSON)
                .when()
                .accept(ContentType.JSON)
                .post(); // después de armar la solicitud este when es para que realice
    }

    @Then("Obtengo un status con codigo {int}")
    public void validarStatus(int status) {
        then().statusCode(status);
    }

    @And("La respuesta contiene un usuario con los datos enviados")
    public void laRespuestaContieneUnUsuarioConLosDatosEnviados() {
        then().body("usuario", Matchers.equalTo(usuario.usuario()));
        usuario.roles().forEach(rol-> then().body("roles",Matchers.contains(rol)));
    }

    @Given("Soy un usuario ya registrado en sistema")
    public void soy_un_usuario_ya_registrado_en_sistema() {
        usuario = new Usuario("pecos14", "pecos", Set.of("user"));
    }

    @Given("Soy un usuario que me registro con campo faltante")
    public void soy_un_usuario_que_me_registro_con_campo_faltante() {
        usuario = new Usuario("", "pecos", Set.of("user"));
    }
}