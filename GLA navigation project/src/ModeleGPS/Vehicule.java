package ModeleGPS;

public class Vehicule {
	
	private Gabarit gabarit;
	private Carburant carburant;
	private String nom;
	
	public String getNom() {
		return nom;
	}

	public Vehicule(Gabarit g, Carburant c, String n){
		this.gabarit = g;
		this.carburant = c;
		this.nom = n;
	}

	//Methode qui permet de modifier le gabarit
	public void setGabarit(Gabarit g) {
		this.gabarit = g;
	}
	//Methode qui permet de modifier le type de carburant
	public void setCarburant(Carburant c) {
		this.carburant = c;
	}
	
	//Methode qui permet de modifier le nom
	public void setNom(String n) {
		this.nom = n;
	}
	
	//Methode qui facilite l'affichage
	public String toString(){
		return "Nom : " + this.nom + " Carburant : " + this.carburant + " Gabarit : "+this.gabarit;
	}
	
	//Methode qui permet l'enregistrement dans l'Historique
	public void enregistrerDansLHistorique(Historique h) {
		h.ajouterVehicule(this);
	}
	
	public Gabarit getGabarit() {
		return this.gabarit;
	}

	public Carburant getCarburant() {
		return this.carburant;
	}
	
	
}