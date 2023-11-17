package starter.step_definitions;

import dtos.Usuario;
import dtos.UsuarioIncompleto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import task.RegisterTask;
import task.UpdateUserTask;
import util.DataProvider;
import util.DataShared;

public class UpdateUserStep {

    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;
    @Given("existe un {actor} registrado en el sistema")
    public void existe_un_usuario_registrado_en_el_sistema(Actor actor) {
        if( "admin".equals(actor.getName()) ){
            dataShared.usuario = dataProvider.getAdmin();
        } else {
            dataShared.usuario = dataProvider.getUser();
            actor.attemptsTo(RegisterTask.withData(dataShared.usuario));
        }
        dataShared.credenciales = dataProvider.getCredential(dataShared.usuario);
    }

    @When("El {actor} solicita actualizar un usuario")
    public void el_admin_solicita_actualizar_el_nombre_de_un_usuario(Actor actor) {
        // Write code here that turns the phrase above into concrete actions
        dataShared.usuario = new Usuario(dataShared.usuario.usuario(), dataProvider.getPassword(), dataShared.usuario.roles());
        actor.attemptsTo(UpdateUserTask.withData(dataShared.usuario.usuario(), dataShared.usuario));
    }

    @When("El {actor} solicita actualizar un usuario con datos incompleto")
    public void elAdminSolicitaActualizarLaClaveDeUnUsuarioConDatosIncompleto(Actor actor) {
        var usuario = new UsuarioIncompleto(dataShared.usuario.usuario());
        actor.attemptsTo(UpdateUserTask.withData(dataShared.usuario.usuario(), usuario));
    }
}
