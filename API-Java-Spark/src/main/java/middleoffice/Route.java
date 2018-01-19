package middleoffice;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

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

	public static void main(String[] args) {
		Route routing = new Route();
		setPort(80);

		get("/", (request, response) -> {
			return "GET /";
		});

		get("/demandes", (request, response) -> {
			JSONObject obj = new JSONObject();

			obj.put("name", "foo");
			// obj.put("num", new Integer(100));
			// obj.put("balance", new Double(1000.21));
			// obj.put("is_vip", new Boolean(true));

			return obj;
		});

		// Creation formulaire
		get("/demandes/creation", (request, response) -> {
			return routing.demandesCreation();
		});
		
		post("/demandes", (request, response) -> {
			return "Test OK";
		});

		put("/demandes/:id", (request, response) -> {
			return "Test OK " + request.params(":id");
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
