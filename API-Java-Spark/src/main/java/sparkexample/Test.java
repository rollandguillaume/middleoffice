package sparkexample;

import static spark.Spark.*;

public class Test {

    public static void main(String[] args) {
        setPort(80);

        get("/", (request, response) -> {
            return "Test OK";
        });
    }
}
