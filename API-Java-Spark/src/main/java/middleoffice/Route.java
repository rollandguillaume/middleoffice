package middleoffice;

import static spark.Spark.*;

public class Route {

    public static void main(String[] args) {
        setPort(80);

        get("/", (request, response) -> {
            return "GET /";
        });

        post("/demandes", (request, response) -> {
            return "Test OK";
        });

        put("/demandes/:id", (request, response) -> {
            return "Test OK " + request.params(":id");
        });

        get("/demandes", (request, response) -> {
            return "Test OK";
        });

        get("/demandes/:id", (request, response) -> {
            return "Test OK" + request.params(":id");
        });

        post("/demandes/:id", (request, response) -> {
            return "Test OK" + request.params(":id");
        });
    }
}
