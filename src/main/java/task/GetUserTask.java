package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static util.Url.getBaseUrl;

public class GetUserTask implements Task {
    private final String usuario;
    public GetUserTask(String usuario) {

        this.usuario = usuario;
    }

    public static GetUserTask withData(String usuario) {

        return new GetUserTask(usuario);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Get.resource("/usuarios/"+usuario)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .log().all()) // Opcional: Registrar detalles de la solicitud
        );
    }

}
