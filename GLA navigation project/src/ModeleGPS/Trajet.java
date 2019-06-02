package ModeleGPS;

import java.util.ArrayList;

public class Trajet {

	private int distance;
	private String date;
	private int duree;
	private Ville villeDepart;
	private Ville villeArrivee;
	private ArrayList<Ville> villeIntermediaires;
	private ArrayList<Troncon> tronconAParcourir;
	// private ArrayList<Route> routesAParcourir;
	private Historique historique;
	private ArrayList<Integer> tronconParId;

	public Trajet(int distance, String date, int duree, Ville d, Ville a, Historique h) {
		this.distance = distance;
		this.date = date;
		this.duree = duree;
		this.villeDepart = d;
		this.villeArrivee = a;
		this.villeIntermediaires = new ArrayList<>();
		this.tronconAParcourir = new ArrayList<>();
		// this.routesAParcourir = new ArrayList<>();
		this.historique = h;
	}

	// Methode qui affiche uniquement la ville de départ et d'arrivée
	public String afficherDA() {
		return this.villeDepart + "-" + this.villeArrivee;
	}

	// Methode qui calcule la distance du trajet
	public int calculerDistance() {
		int res = 0;
		for (int i = 0; i < this.tronconAParcourir.size(); i++) {
			res += this.tronconAParcourir.get(i).distanceAParcourir();
		}
		this.distance = res;
		return res;
	}

	// Methode qui calcule la duree estimee du trajet
	public int calculerTemps() {
		int res = 0;
		for (int i = 0; i < this.tronconAParcourir.size(); i++) {
			res += this.tronconAParcourir.get(i).distanceAParcourir() * this.tronconAParcourir.get(i).vitesseMax();
		}
		return res;
	}

	// Methode qui calcule le prix a payer du trajet
	public int calculerPrix() {
		int res = 0;
		for (int i = 0; i < this.tronconAParcourir.size(); i++) {
			res += this.tronconAParcourir.get(i).prixAPayer();
		}
		return res;
	}

	// Methode qui determine si le trajet courrant est l'inverse d'un trajet passÃ©
	// en parametres
	public boolean estLInverse(Trajet t) {
		if (this.villeDepart == t.getvilleA() && this.villeArrivee == t.getVilleD())
			return true;
		return false;
	}

	// Methode qui permet l'enregistrement dans l'Historique
	public void enregistrerDansLHistorique() {
		this.historique.ajouterTrajet(this);
	}

	// Methode qui permet de retracer un chemin
	public void retracer() {
		System.out.println("Work In Progress");
	}

	// Accesseurs
	public Ville getVilleD() {
		return this.villeDepart;
	}

	public Ville getvilleA() {
		return this.villeArrivee;
	}

	//Ajoute ville a this.villeIntermediaires
	public void ajouteVille(Ville x) {
		this.villeIntermediaires.add(x);
	}
	
	// Methode qui determine les villes intermediaires
	public ArrayList<Ville> villesAParcourir() {
		return this.villeIntermediaires;
	}

	public Trajet(int distance, String date, int duree, Ville d, Ville a) {
		this.distance = distance;
		this.date = date;
		this.duree = duree;
		this.villeDepart = d;
		this.villeArrivee = a;
		this.villeIntermediaires = new ArrayList<>();
		this.tronconAParcourir = new ArrayList<>();
		// this.routesAParcourir = new ArrayList<>();
		// this.historique = h;
	}

	public Trajet(Ville d, Ville a) {
		this.villeDepart = d;
		this.villeArrivee = a;
		this.duree = 0;
		this.villeIntermediaires = new ArrayList<>();
		this.tronconAParcourir = new ArrayList<>();
		this.tronconParId = new ArrayList<>();
	}

	public void ajouteTroncon(Troncon x) {
		this.tronconAParcourir.add(x);
	}

	public void ajouteTronconParId(int x) {
		this.tronconParId.add(x);
	}

	public void setDistance(int x) {
		this.distance = x;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public int getDuree() {
		return this.duree;
	}


	public ArrayList<Integer> getItineraire() {
		return this.tronconParId;
	}

	public String trajetToXML(ArrayList<Troncon> troncons, ArrayList<Route> routes) {
		int count = 0;
		String content = "<trajet>" + "\n";

		content += "<ville-depart>" + this.villeDepart.getNom() + "</ville-depart>" + "\n";
		for (int i = this.tronconParId.size() - 1; i>=0; i--) {
			Troncon tmp = troncons.get(this.tronconParId.get(i));
			count++;
			content += "<etape>" + "\n";
			content += "<numero>" + count + "</numero>" + "\n";
			content += "<route>" + routes.get(tmp.getRoute()).getNom() + "</route>" + "\n";
			content += "<destination>" + tmp.getV2().getNom() + "</destination>" + "\n";
		}
		/*for (int x : this.tronconParId) {
			count++;
			content += "<etape>" + "\n";
			content += "<numero>" + count + "</numero>" + "\n";
			content += "<route>" + routes.get(troncons.get(x).getRoute()).getNom() + "</route>" + "\n";
			content += "<destination>" + troncons.get(x).getV2().getNom() + "</destination>" + "\n";
			content += "</etape>" + "\n";
		}*/
		content += "<ville-arrivee>" + this.villeArrivee.getNom() + "</ville-arrivee>" + "\n";
		content += "</trajet>" + "\n";
		return content;
	}
}