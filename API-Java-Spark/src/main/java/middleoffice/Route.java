package middleoffice;

import static spark.Spark.*;
import org.json.JSONObject;

public class Route {	
	public static void main(String[] args) {
		Controller controller = new Controller();
		setPort(80);

		get("/", (request, response) -> {
			return controller.newHtml("Documentation.html");
		});

		post("/demandes", (request, response) -> {
			controller.createDemande(request);
			response.redirect("/demandes");
			return "demande ajoutée : voir GET /demandes";
		});

		get("/demandes/creation", (request, response) -> {
			return controller.newHtml("demandesCreation.html");
		});

		get("/demandes", (request, response) -> {
			return controller.getObj();
		});

		post("/demandes/:id", (request, response) -> {
			String vote = request.queryParams("vote");

			JSONObject tmpDemande = controller.getDemande(Integer.parseInt(request.params(":id")));
			tmpDemande.put("voted", true);

			// TODO redirection
			return tmpDemande;
		});

		get("/demandes/:id", (request, response) -> {
			JSONObject ret = controller.getDemande(Integer.parseInt(request.params(":id")));

			if (ret != null) {
				return ret;
			} else {
				return "Demande introuvable.";
			}
		});
	}
}
