package ModeleGPS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class fileManager {

	private String filePath;
	private String fileName;
	Boolean existe = false;

	public fileManager() {
		this.fileName = "User.txt";
		this.filePath = "src/usersData/" + this.fileName;
	}

	// Methode pour sauvegarder le dessin dans le fichier source
	public void saveAccount(String username, String passWord, String a) {
		// On ouvre le fichier contenant les données de tous les utilisateurs
		BufferedWriter writter;
		File f = new File(this.filePath);
		try {
			// Si le nom d'utilisateur n'existe pas et qu'on arrive à la fin du fichier,
			// alors
			writter = new BufferedWriter(new FileWriter(f, true));
			writter.newLine();
			writter.write("Username:" + username + "-Password:" + passWord + "-Adress:" + a);
			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getExiste() {
		return this.existe;
	}

	// Vérifie dans la base de données que l'utilisateur existe
	public Boolean utilisateurExistant(String username) {
		BufferedReader reader;
		try {
			// On ouvre le fichier
			File f = new File(this.filePath);
			FileReader fr = new FileReader(f);
			reader = new BufferedReader(fr);
			// On lit ligne par ligne
			String line;
			while ((line = reader.readLine()) != null) {
				// On sépare la ligne en 3 parties
				String[] parts = line.split("\\-");
				String[] user = parts[0].split(":");
				// Si le nom d'utilisateur saisi existe déjà dans la base de données
				if (user[1].equals(username)) {
					fr.close();
					return true;
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Vérifie que le mot de passe correspond bien avec le mot de passe
	// Renvoie true si l'id et le MDP correspondent, faux sinon
	public Boolean verifieMDPUtilisateur(String username, String MDP) {
		if (utilisateurExistant(username)) {
			BufferedReader reader;
			try {
				// On ouvre le fichier
				File f = new File(this.filePath);
				FileReader fr = new FileReader(f);
				reader = new BufferedReader(fr);
				// On lit ligne par ligne
				String line;
				while ((line = reader.readLine()) != null) {
					// On sépare la ligne en 3 parties
					String[] parts = line.split("\\-");
					String[] user = parts[0].split(":");
					String[] passWord = parts[1].split(":");
					// Si le nom d'utilisateur saisi existe déjà dans la base de données
					if (user[1].equals(username)) {
						if (passWord[1].equals(MDP)) {
							fr.close();
							return true;
						}
					}
				}
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return false;
	}

	// Pour que l'IDE ne pollue pas avec des warning
	@SuppressWarnings("unchecked")
	// Sauvegarde le fichier JSON de l'utilisateur
	public void saveHistorique(String username, Historique h) {
		JSONObject obj = new JSONObject();
		JSONArray vehicules = new JSONArray();
		for (int i = 0; i < h.getVehicules().size(); i++) {
			JSONObject ve = new JSONObject();
			ve.put("nom", h.getVehicules().get(i).getNom());
			ve.put("gabarit", h.getVehicules().get(i).getGabarit().toString());
			ve.put("carburant", h.getVehicules().get(i).getCarburant().toString());
			vehicules.add(ve);
		}
		JSONArray parametres = new JSONArray();
		for (int i = 0; i < h.getParametres().size(); i++) {
			JSONObject pe = new JSONObject();
			pe.put("prix", Integer.toString(h.getParametres().get(i).getPrix()));
			pe.put("volume", Integer.toString(h.getParametres().get(i).getVolume()));
			pe.put("last", h.getParametres().get(i).getLast());
		}
		JSONArray trajets = new JSONArray();
		for (int i = 0; i < h.getTrajets().size(); i++) {
			JSONObject te = new JSONObject();
			te.put("ville de depart", h.getTrajets().get(i).getVilleD().getNom());
			te.put("ville arrivée", h.getTrajets().get(i).getvilleA().getNom());
			te.put("distance", Integer.toString(h.getTrajets().get(i).getDistance()));
			te.put("duree", Integer.toString(h.getTrajets().get(i).getDuree()));
		}
		obj.put("vehicules", vehicules);
		obj.put("parametres", parametres);
		obj.put("trajets", trajets);

		try (FileWriter file = new FileWriter("src/usersData/" + username + ".json")) {
			file.write(obj.toString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Décode le fichier JSON de l'utilisateur
	public void decodeHistorique(String username, Historique h) {
		JSONParser jp = new JSONParser();
		try {
			Object obj = jp.parse(new FileReader("src/usersData/" + username + ".json"));
			JSONObject json = (JSONObject) obj;
			// Boucles
			JSONArray parametres = (JSONArray) json.get("parametres");
			if (parametres.size() > 0) {
				String jsonString = parametres.toJSONString();
				Gson gson = new Gson();
				Parametres[] param = gson.fromJson(jsonString, Parametres[].class);
				for (int i = 0; i < param.length; i++) {
					h.ajouterParametres(param[i]);
				}
			}
			JSONArray vehicules = (JSONArray) json.get("vehicules");
			if (vehicules.size() > 0) {
				String jsonString = vehicules.toJSONString();
				Gson gson = new Gson();
				Vehicule[] vehi = gson.fromJson(jsonString, Vehicule[].class);
				for (int i = 0; i < vehi.length; i++) {
					h.ajouterVehicule(vehi[i]);
				}
			}
			JSONArray trajets = (JSONArray) json.get("trajets");
			if (trajets.size() > 0) {
				String jsonString = trajets.toJSONString();
				Gson gson = new Gson();
				Trajet[] tr = gson.fromJson(jsonString, Trajet[].class);
				for (int i = 0; i < tr.length; i++) {
					h.ajouterTrajet(tr[i]);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
