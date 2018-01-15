package middleoffice;

import static spark.Spark.*;
import org.json.JSONObject;

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
          JSONObject obj = new JSONObject();

          obj.put("name", "foo");
          // obj.put("num", new Integer(100));
          // obj.put("balance", new Double(1000.21));
          // obj.put("is_vip", new Boolean(true));

          return obj;
        });

        get("/demandes/:id", (request, response) -> {

          return "Test OK" + request.params(":id");
        });

        post("/demandes/:id", (request, response) -> {
          String vote = request.queryParams("vote");

          String json = "{'demandes':{'id':3}}";

          return "paramId=" + request.params(":id") + "; vote=" + vote +" ; json="+json;
        });
    }
}
