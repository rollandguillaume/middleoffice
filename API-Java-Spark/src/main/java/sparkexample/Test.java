package sparkexample;

import static spark.Spark.*;

public class Test {

    public static void main(String[] args) {
        setPort(80);

        get("/", (request, response) -> {
            return "GET /";
        });

        get("/test", (request, response) -> {
            return "GET /test";
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
