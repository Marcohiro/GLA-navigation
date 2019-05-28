import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class fileManager {

	private String filePath;
	private String fileName;
	Boolean existe = false;

	public fileManager() {
		this.fileName = "User.txt";
		this.filePath = "src/usersData/" + this.fileName;
	}

	// Methode pour sauvegarder le dessin dans le fichier source
	public void saveAccount(String username, String passWord) {
		// On ouvre le fichier contenant les données de tous les utilisateurs
		BufferedWriter writter;
		File f = new File(this.filePath);
		try {
			// Si le nom d'utilisateur n'existe pas et qu'on arrive à la fin du fichier,
			// alors
			writter = new BufferedWriter(new FileWriter(f, true));
			writter.newLine();
			writter.write("Username:" + username + "-Password:" + passWord + "-Adress:");
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
						if(passWord[1].equals(MDP)) {
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
	
	//Pour que l'IDE ne pollue pas avec des warning
	@SuppressWarnings("unchecked")
	//Sauvegarde le fichier JSON de l'utilisateur
	public void saveHistorique(String username, String filepath, Historique h) {
		JSONObject obj = new JSONObject();
		JSONArray vehicules = new JSONArray();
		for(int i = 0; i<h.getVehicules().size(); i++) {
			vehicules.add(h.getVehicules().get(i));
		}
		JSONArray parametres = new JSONArray();
		for(int i = 0; i<h.getParametres().size(); i++) {
			parametres.add(h.getParametres().get(i));
		}
		JSONArray trajets = new JSONArray();
		for(int i = 0; i<h.getTrajets().size(); i++) {
			trajets.add(h.getTrajets().get(i));
		}
		obj.put("vehicules", vehicules);
		obj.put("parametres", parametres);
		obj.put("trajets", trajets);
	
		try(FileWriter file = new FileWriter(filepath+".json"))
		{
			file.write(obj.toString());
			file.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	//Décode le fichier JSON de l'utilisateur
	public void decodeHistorique(String username, String filepath, Historique h) {
		JSONParser jp = new JSONParser();
		
		try {
			Object obj = jp.parse(filepath);
			JSONObject json = (JSONObject) obj;
			//Boucles
			JSONArray vehicules = (JSONArray) json.get("vehicules");
			Iterator<Vehicule> itV = vehicules.iterator();
			while(itV.hasNext()) {
				h.ajouterVehicule(itV.next());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
