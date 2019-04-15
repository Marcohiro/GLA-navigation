import java.util.ArrayList;

public class Ville {
	
	private String nom;
	private TailleDeLaVille taille;
	private boolean touristique;
	private float longitude;
	private float latitude;
	private ArrayList<Troncon> troncons;
	
	public Ville(String n, TailleDeLaVille t, Boolean touristique, float lon, float lat){
		this.nom = n;
		this.taille = t;
		this.touristique = touristique;
		this.longitude = lon;
		this.latitude = lat;
		this.troncons = new ArrayList<>();
	}
	
	//Methode qui renvoie les troncons lies a la ville
	public ArrayList<Troncon> linkedSections(){
		return this.troncons;
	}
}