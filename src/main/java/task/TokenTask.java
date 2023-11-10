package task;

import dtos.Usuario;
import dtos.UsuarioLogin;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class TokenTask implements Task {
    private final UsuarioLogin usuarioLogin;
    public TokenTask(UsuarioLogin usuarioLogin) {

        this.usuarioLogin = usuarioLogin;
    }

    public static TokenTask withData(UsuarioLogin usuarioLogin) {

        return new TokenTask(usuarioLogin);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at("http://localhost:8090/api"));

        actor.attemptsTo(
                Post.to("/tokens")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(usuarioLogin, ObjectMapperType.GSON)
                        )
                        .with(request -> request.log().all())
        );
        actor.remember("token", SerenityRest.lastResponse().getHeader("Authorization"));
    }

}
