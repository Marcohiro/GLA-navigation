package ModeleGPS;

import java.util.List;

public class Troncon {

	/* Attributs */
	private int id;

	private Ville ville1;
	private Ville ville2;
	String ville3, ville4;
	private int vitesse;
	private boolean touristique;
	private boolean radar;
	private boolean payant;
	private int longueur;
	private int route;
	Integer vitesse2, longueur2;
	String touristique2, radar2, payant2;
	

	/* Constructeur */
	public Troncon(int id, Ville ville1, Ville ville2, int vitesse, boolean t, boolean r, boolean p, int l) {
		this.id = id;
		this.ville1 = ville1;
		this.ville2 = ville2;
		this.vitesse = vitesse;
		this.touristique = t;
		this.radar = r;
		this.payant = p;
		this.longueur = l;
	}

	public Troncon(int id) {
		this.id = id;
		this.ville1 = null;
		this.ville2 = null;
		this.vitesse = 0;
		this.touristique = false;
		this.radar = false;
		this.payant = false;
		this.longueur = 0;
	}
	
	public Troncon(String ville1, String ville2, Integer vitesse, Integer longueur, String touristique, String radar, String payant) {
		this.ville3=ville1;
		this.ville4=ville2;
		this.vitesse=vitesse;
		this.longueur=longueur;
		this.touristique2=touristique;
		this.radar2=radar;
		this.payant2=payant;
	}

	/* Methodes */
	public void setV1(String v1, List<Ville> liste) {
		for (Ville v : liste) {
			if (v.getNom().equalsIgnoreCase(v1)) {
				this.ville1 = v;
				break;
			}
		}
	}

	public void setV2(String v2, List<Ville> liste) {
		for (Ville v : liste) {
			if (v.getNom().equalsIgnoreCase(v2)) {
				this.ville2 = v;
				break;
			}
		}
	}

	public void setTouristique(String bool) {
		if (bool.equalsIgnoreCase("oui"))
			this.touristique = true;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public void setRadar(String bool) {
		if (bool == "oui")
			this.radar = true;
	}

	public void setPayant(String bool) {
		if (bool == "oui")
			this.payant = true;
	}

	public void setLongueur(int x) {
		this.longueur = x;
	}

	public void setRoute(int r) {
		this.route = r;
	}

	public Ville getV1() {
		return this.ville1;
	}

	public Ville getV2() {
		return this.ville2;
	}

	public int getVitesse() {
		return this.vitesse;
	}

	public int getLongueur() {
		return this.longueur;
	}

	public int getId() {
		return this.id;
	}

	public int getRoute() {
		return this.route;
	}
	
	//Prix Abitraire
	public int prixAPayer() {
		if(!this.payant) return 0;
		else return 15;
	}


	public String toString() {
		String res = this.getId() + ". ";
		res += "[" + this.getV1().getNom() + ", " + this.getV2().getNom() + ", " + this.getLongueur() + "] "
				+ this.getRoute();
		return res;
	}

	//Retourne la distance a parcourir
	public int distanceAParcourir() {
		return this.longueur;
	}

	//Retourne la vitesse max du troncon
	public int vitesseMax() {
		return this.vitesse;
	}
	
	public boolean getTouristique () {
		return (this.touristique2=="oui");
	}
}