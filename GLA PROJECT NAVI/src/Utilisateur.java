public class Utilisateur {
	
	private String pseudo;
	private String password;
	private Ville adresse;
	private Historique historique;
	
	public Utilisateur(String n, String pass, Ville addresse, Historique h){
		this.pseudo = n;
		this.password = pass;
		this.adresse = addresse;
		this.historique = h;
	}
	
	//Methode permettant la saisie d'un nouveau vehicule
	public Vehicule saisirVehicule(Gabarit g, Carburant c, String nom){
		Vehicule v = new Vehicule(g, c, nom);
		if(this.historique.contientVehicule(v)){
			System.out.println("Vehicule deja existant, inutile de resaisir");
			return null;
		}else{
			this.historique.ajouterVehicule(v);
			return v;
		}
	}
	
	//Methode permettant la saisie de parametres
	public Parametres saisirParametres(int p, int volCO2, boolean distancePlusCourte, boolean rapideDuree){
		Parametres param = new Parametres(p, volCO2, distancePlusCourte, rapideDuree);
		if(this.historique.contientParametres(param)){
			System.out.println("Parametres deja existatns, inutile de les resaisir");
			return null;
		} else {
			this.historique.ajouterParametres(param);
			return param;
		}
	}
	
	//Methode permettant la saisie d'un trajet
	public Trajet saisirTrajet(Ville d, Ville a, Parametres p){
		return null;
	}
	
	//Methode permettant de consulter l'Historique
	public String consulterHistorique(){
		return this.historique.toString();
	}
	
}