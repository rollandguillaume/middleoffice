package middleoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import spark.Request;

public class Controller {
	private JSONObject obj;
	private int id;
	
	public Controller() {
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
			System.err.println("Controller::demandesCreation Exception : ");
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

	public JSONObject getDemande(int id) {
		JSONObject ret = null;
		JSONArray demandes;
		JSONObject tmpDemande;
		
		demandes = this.obj.getJSONArray("demandes");
		for (Object obj : demandes) {
			tmpDemande = (JSONObject) obj;
			
			if (Integer.parseInt(tmpDemande.get("id").toString()) == id) {
				ret = tmpDemande;
			}
		}

		return ret;
	}
	
	public JSONObject getObj() {
		return obj;
	}

}
