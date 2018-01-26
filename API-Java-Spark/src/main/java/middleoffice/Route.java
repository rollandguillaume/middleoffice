package middleoffice;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import org.json.JSONObject;

import spark.Request;

import org.json.JSONArray;

public class Route {
	private JSONObject obj;
	private int id;
	
	public Route() {
		this.obj = new JSONObject();
		this.obj.put("demandes", new JSONArray());
		this.id = 1;
	}

	public String newHtml(String page) {
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader("Web/"+ page));
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

	public void createDemande(Request request) {
		JSONObject demande = new JSONObject();
		demande.put("id", this.id);

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
		demande.put("voted", false);
		
		JSONArray demandes = this.obj.getJSONArray("demandes");
		demandes.put(demande);

		this.obj.put("demandes", demandes);

		this.id++;
	}

	private JSONObject getDemande(int id) {
		JSONObject ret = null;
		int size = this.obj.length();
		JSONArray demandes = null;
		JSONObject tmpDemande = null;
		int i = 0;
		boolean find = false;
		
		demandes = this.obj.getJSONArray("demandes");
		for (Object obj : demandes) {
			tmpDemande = (JSONObject) obj;
			
			if (Integer.parseInt(tmpDemande.get("id").toString()) == id) {
				ret = tmpDemande;
			}
		}
		
		/*
		while (i < size && !find) {
			tmpDemande = (JSONObject) this.obj.get(i);
			if (Integer.parseInt(tmpDemande.get("id").toString()) == id) {
				ret = tmpDemande;
				find = true;
			}
			i++;
		}*/

		return ret;
	}

	public static void main(String[] args) {
		Route route = new Route();
		setPort(80);

		get("/", (request, response) -> {
			return route.newHtml("Documentation.html");
		});

		post("/demandes", (request, response) -> {
			route.createDemande(request);
			response.redirect("/demandes");
			return "demande ajoutée : voir GET /demandes";
		});

		get("/demandes/creation", (request, response) -> {
			return route.newHtml("demandesCreation.html");
		});

		get("/demandes", (request, response) -> {
			return route.obj;
		});

		post("/demandes/:id", (request, response) -> {
			String vote = request.queryParams("vote");

			JSONObject tmpDemande = route.getDemande(Integer.parseInt(request.params(":id")));

			tmpDemande.put("voted", true);

			// TODO redirection
			return tmpDemande;
		});

		get("/demandes/:id", (request, response) -> {
			JSONObject ret = route.getDemande(Integer.parseInt(request.params(":id")));

			if (ret != null) {
				return ret;
			} else {
				return "Erreur 404";
			}
		});
	}
}
