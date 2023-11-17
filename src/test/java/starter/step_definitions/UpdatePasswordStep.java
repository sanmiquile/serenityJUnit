package starter.step_definitions;

import dtos.ClaveNueva;
import dtos.Usuario;
import dtos.UsuarioIncompleto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import task.PatchClaveTask;
import task.RegisterTask;
import task.UpdateUserTask;
import util.DataProvider;
import util.DataShared;

public class UpdatePasswordStep {

    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    private ClaveNueva claveNueva;
    @When("El {actor} solicita actualizar la clave de un usuario")
    public void el_admin_solicita_actualizar_la_clave_de_un_usuario(Actor actor) {
        String nuevaClave= dataProvider.getPassword();
        claveNueva = new ClaveNueva(dataShared.usuario.clave(),nuevaClave,nuevaClave);
        actor.attemptsTo(PatchClaveTask.withData(claveNueva,dataShared.usuario.usuario()));
    }
    @When("El {actor} solicita actualizar la clave de un usuario con datos incompletos")
    public void el_admin_solicita_actualizar_la_clave_de_un_usuario_con_datos_incompletos(Actor actor) {
        String nuevaClave= "";
        claveNueva = new ClaveNueva(dataShared.usuario.clave(),nuevaClave,nuevaClave);
        actor.attemptsTo(PatchClaveTask.withData(claveNueva,dataShared.usuario.usuario()));
    }
}


