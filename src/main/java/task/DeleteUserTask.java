package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static util.Url.getBaseUrl;

public class DeleteUserTask implements Task {

    private String usuario;

    public DeleteUserTask(String usuario) {
        this.usuario = usuario;
    }

    public static DeleteUserTask withData(String usuario) {

        return new DeleteUserTask(usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Delete.from("/usuarios/" + usuario)
                        .with(request -> request
                                .headers("Authorization", actor.gaveAsThe("token"))
                        ).with(request -> request.log().all())
        );


    }
}
