public class Vehicule {
	
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
	
	//Methode qui facilite l'affichage
	public String toString(){
		return "Nom : " + this.nom + " Carburant : " + this.carburant + " Gabarit : "+this.gabarit;
	}
	
	//Methode qui permet l'enregistrement dans l'Historique
	public void enregistrerDansLHistorique(Historique h){
		h.ajouterVehicule(this);
	}
	
	public Gabarit getGabarit() {
		return this.gabarit;
	}

	public Carburant getCarburant() {
		return this.carburant;
	}
	
	public String getName() {
		return this.nom;
	}
}