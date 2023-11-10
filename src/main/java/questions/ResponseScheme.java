package questions;

import dtos.Usuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseScheme implements Question<Usuario>{

    public static Question<Integer> get() {
        return new StatusCode();
    }

    @Override
    public Usuario answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Usuario.class);
    }
}
