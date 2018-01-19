package middleoffice;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;

public class Route {
	public String demandesCreation() {
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader("Web/demandesCreation.html"));
			String str;
			while ((str = in.readLine()) != null) {
				content += str;
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Route::demandesCreation Exception : ");
			System.err.println(e);
		}

		return content;
	}

    private JSONObject obj = new JSONObject();
    private int id = 0;

    public static void main(String[] args) {
        Route route = new Route();
        setPort(80);

		get("/", (request, response) -> {
			return "GET /";
		});

    post("/demandes", (request, response) -> {
      int iddemande = route.id;

      JSONObject demande = new JSONObject();
      demande.put("id", route.id);

          JSONObject repYes = new JSONObject();
          repYes.put("href", request.queryParams("lienAccept"));
          repYes.put("accept", true);
          repYes.put("label", request.queryParams("labelAccept"));
          JSONObject repNo = new JSONObject();
          repNo.put("href", request.queryParams("lienRefusal"));
          repNo.put("accept", false);
          repNo.put("label", request.queryParams("labelRefusal"));

        JSONArray responses = new JSONArray();
        responses.put(repYes);
        responses.put(repNo);

      demande.put("responses", responses);
      demande.put("type", request.queryParams("type"));
      demande.put("label", request.queryParams("label"));
      demande.put("origin", request.queryParams("origin"));

      route.obj.put("demande"+iddemande, demande);

      route.id++;
      return "demande ajoutée : voir GET /demandes";
    });

    get("/demandes/creation", (request, response) -> {
      return route.demandesCreation();
    });

    get("/demandes", (request, response) -> {
      return route.obj;
    });

		post("/demandes/:id", (request, response) -> {
			String vote = request.queryParams("vote");

			String json = "{'demandes':{'id':3}}";

			return "paramId=" + request.params(":id") + "; vote=" + vote + " ; json=" + json;
		});

		get("/demandes/:id", (request, response) -> {

			return "Test OK" + request.params(":id");
		});
	}
}
