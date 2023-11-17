package task;

import dtos.UsuarioLogin;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static util.Url.getBaseUrl;

public class GetTokensTask implements Task {
    public static GetTokensTask withData() {

        return new GetTokensTask();
    }

    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Get.resource("/tokens")
                        .with(requestSpecification -> requestSpecification
                                .headers("Authorization", actor.gaveAsThe("token"))

                        )
                        .with(request -> request.log().all()) // Opcional: Registrar detalles de la solicitud
        );
        actor.remember("requestTokens", SerenityRest.lastResponse().getBody().asString());

    }

}
