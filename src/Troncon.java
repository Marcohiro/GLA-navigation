public class Troncon {
	
	private int vitesse;
	private Ville villeDeDepart;
	private Ville villeArrivee;
	private int longueur;
	private Boolean radar;
	private Boolean payant;
	private int prix;
	private Boolean touristique;
	
	public Troncon(int v, Ville d, Ville a, int l, Boolean r, Boolean p, int prix, Boolean t){
		this.vitesse = v;
		this.villeDeDepart = d;
		this.villeArrivee = a;
		this.longueur = l;
		this.radar = r;
		this.payant = p;
		if(p) this.prix = prix;
		else this.prix = 0;
		this.touristique = t;	
	}
	
	//Methode qui facilite l'affichage
	public String toString(){
		return "Vitesse Max = "+ this.vitesse + " avec radar?" + this.radar+" allant de " + this.villeDeDepart + " a " + this.villeArrivee + " de longueur " + this.longueur + "est payante?"+this.payant+ "de prix " + this.prix + "est touristique" + this.touristique; 
	}
	
	//Methode qui determine si la ville est evitable
	public Boolean estEvitable(Trajet t){
		return false;
	}
	
	//Methode qui renvoie le prix du troncon
	public int prixAPayer(){
		return this.prix;
	}
	
	//Methode qui renvoie la distance du troncon
	public int distanceAParcourir(){
		return this.longueur;
	}
	
	//Methode qui renvoie la vitesse maximale du troncon
	public int vitesseMax(){
		return this.vitesse;
	}
}