package middleoffice;

import static spark.Spark.*;
import org.json.JSONObject;

public class Route {

    private JSONObject obj = new JSONObject();
    private int id = 0;

    public static void main(String[] args) {
        Route route = new Route();
        setPort(80);

        get("/", (request, response) -> {
            return "GET /";
        });

        post("/demandes", (request, response) -> {
          route.obj.put("id"+route.id, ""+route.id);

          // obj.put("num", new Integer(100));
          // obj.put("balance", new Double(1000.21));
          // obj.put("is_vip", new Boolean(true));

          route.id++;
          return "demande ajoutée : voir GET /demandes";
        });

        put("/demandes/:id", (request, response) -> {
            return "Test OK " + request.params(":id");
        });

        get("/demandes", (request, response) -> {
          return route.obj;
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
