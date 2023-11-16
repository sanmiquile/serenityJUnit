package task;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static util.Url.getBaseUrl;

public class UpdateUserTask<T> implements Task {
    private final String usuarioActual;
    private final T usuario;
    public UpdateUserTask(String usuarioActual, T usuario) {

        this.usuarioActual = usuarioActual;
        this.usuario = usuario;
    }

    public static <T> UpdateUserTask<T> withData(String usuarioActual,T usuario) {
        return new UpdateUserTask<T>(usuarioActual,usuario);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Put.to("/usuarios/" + usuarioActual)
                        .with(request -> request
                                .headers("Authorization", actor.gaveAsThe("token"))
                                .contentType(ContentType.JSON)
                                .body(usuario, ObjectMapperType.GSON)
                                )
                        .with(request -> request.log().all())
        );
    }

}
