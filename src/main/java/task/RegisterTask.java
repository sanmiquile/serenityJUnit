package task;

import com.github.javafaker.Faker;
import dtos.Usuario;
import net.serenitybdd.screenplay.Task;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Set;

public class RegisterTask implements Task {
    private final Usuario usuario;
    public RegisterTask(Usuario usuario) {

        this.usuario = usuario;
    }

    public static RegisterTask withData(Usuario usuario) {

        return new RegisterTask(usuario);
    }

    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at("http://localhost:8090/api"));

        actor.attemptsTo(
                Post.to("/usuarios")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(usuario, ObjectMapperType.GSON)
                        )
                        .with(request -> request.log().all()) // Opcional: Registrar detalles de la solicitud
        );
    }

}
