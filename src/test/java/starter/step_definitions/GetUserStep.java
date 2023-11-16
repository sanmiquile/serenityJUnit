package starter.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import task.GetUserTask;
import task.TokenTask;
import util.DataProvider;
import util.DataShared;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static questions.GetValueFromResponseBodyQuestion.theAttributeValue;

public class GetUserStep {
    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    @Given("El {actor} esta autenticado en el sistema")
    public void elAdminEstaAutenticadoEnElSistema(Actor actor) {
        //var credential = dataProvider.getCredential(dataShared.usuario);
        actor.attemptsTo(TokenTask.withData(dataShared.credenciales));
    }

    @When("El {actor} solicita informacion de {string}")
    public void elAdminSolicitaInformacionDeUnUsuario(Actor actor,String usuario) {
        dataShared.usuario = dataProvider.getAdmin();
        if( "el".equals(usuario) ){
            usuario = dataShared.usuario.usuario();
        }
        actor.attemptsTo(GetUserTask.withData(usuario));
    }

    @And("El {actor} obtiene los datos del usuario")
    public void elAdminObtieneLosDatosDelUsuario(Actor actor) {
        actor.should(
                seeThat(theAttributeValue("usuario"), equalTo(dataShared.usuario.usuario())),
                seeThat(theAttributeValue("roles").asListOf(String.class), equalTo(dataShared.usuario.roles()))
                // TODO crear el datamager
        );
    }

    @Given("El {actor} no esta autenticado")
    public void el_admin_no_esta_autorizado_en_el_sistema(Actor actor) {
        //actor.attemptsTo(TokenTask.withData(new UsuarioLogin("laura","laura")));
        actor.remember("token", "");
        dataShared.usuario = dataProvider.getUser();
    }

    @When("El {actor} solicita informacion de usuario no existente")
    public void el_admin_solicita_informacion_en_un_recursos_no_existente(Actor actor) {
        actor.attemptsTo(GetUserTask.withData(dataProvider.getUsername()));
    }
}
