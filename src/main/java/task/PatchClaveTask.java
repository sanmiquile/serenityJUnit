package task;

import dtos.ClaveNueva;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static util.Url.getBaseUrl;

public class PatchClaveTask<T> implements Task {
    private final ClaveNueva claveNueva;
    private final String user;
    public PatchClaveTask(ClaveNueva claveNueva, String user ) {

        this.claveNueva = claveNueva;
        this.user = user;
    }

    public static <T> PatchClaveTask<T> withData(ClaveNueva claveNueva, String user) {
        return new PatchClaveTask<T>(claveNueva,user);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Patch.to("/usuarios/" + user)
                        .with(request -> request
                                .headers("Authorization", actor.gaveAsThe("token"))
                                .contentType(ContentType.JSON)
                                .body(claveNueva, ObjectMapperType.GSON)
                                )
                        .with(request -> request.log().all())
        );
    }

}
