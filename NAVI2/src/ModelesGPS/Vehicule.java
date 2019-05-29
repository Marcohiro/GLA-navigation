package ModelesGPS;

public class Vehicule {
	public static enum Gabarit {citadine,
								monospace,
								smart,
								deuxRoues,
								camionnette,
								camion,
								toutTerrain};
	public static enum Carburant {essence, diesel};
	
	private Gabarit gabarit;
	private Carburant carburant;
	private String nom;
	
	public Vehicule(Gabarit g, Carburant c, String n){
		this.gabarit = g;
		this.carburant = c;
		this.nom = n;
	}

	//Methode qui permet de modifier le gabarit
	public void modifierGabarit(Gabarit g){
		this.gabarit = g;
	}
	//Methode qui permet de modifier le type de carburant
	public void modifierCarburant(Carburant c){
		this.carburant = c;
	}
	
	//Methode qui permet de modifier le nom
	public void modifierNom(String n){
		this.nom = n;
	}
	
	public Gabarit getGabarit() {
		return this.gabarit;
	}
	
	public Carburant getCarburant() {
		return this.carburant;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	//Methode qui facilite l'affichage
	public String toString(){
		return "Nom : " + this.nom + " Carburant : " + this.carburant + " Gabarit : "+this.gabarit;
	}
}
