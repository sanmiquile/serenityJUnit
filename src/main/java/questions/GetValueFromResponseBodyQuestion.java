package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetValueFromResponseBodyQuestion<T> implements Question<T> {

    private final String jsonPath;

    public GetValueFromResponseBodyQuestion(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static <T> Question<T> theAttributeValue(String jsonPath) {
        return new GetValueFromResponseBodyQuestion<T>(jsonPath);
    }

    @Override
    public T answeredBy(Actor actor) {
        //System.out.println(SerenityRest.lastResponse().getBody().path(jsonPath).toString());
        return SerenityRest.lastResponse().getBody().jsonPath().get(jsonPath);
    }
}