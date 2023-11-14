package util;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

public class Url {

    private static final Dotenv dotenv = new DotenvBuilder()
            .filename("dotenv.env")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    public static final String BASE_URL = dotenv.get("BASE_URL");

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
