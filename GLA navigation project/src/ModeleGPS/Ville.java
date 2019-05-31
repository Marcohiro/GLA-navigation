package ModeleGPS;
import java.util.ArrayList;

public class Ville {
	
	/* Attributs */
	private String nom;
	private int id;
	private VTaille type;
	private boolean touristique;
	private float[] coordonnees;
	private ArrayList<Troncon> troncons;
	private Float longitude, latitude;
	private String type2, touristique2;

	public static enum VTaille {petite, moyenne, grande};
	
	/* Constructeur */
	public Ville(int id, String nom, VTaille type, boolean touristique, float[] coordonnees) {
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.touristique = touristique;
		this.coordonnees = new float[2];
		this.coordonnees[0] = coordonnees[0];
		this.coordonnees[1] = coordonnees[1];
	}
	
	public Ville(String nom, String type,String touristique, Float longitude, Float latitude) {
        this.nom = nom;
        this.type2=type;
        this.touristique2=touristique;
        this.longitude=longitude;
        this.latitude=latitude;
}
	
	public Ville(int id) {
		this.id = id;
		this.nom = null;
		this.type = null;
		this.touristique = false;
		this.coordonnees = new float[2];
}
	
	//Methode qui renvoie les troncons lies a la ville
	public ArrayList<Troncon> linkedSections(){
		return this.troncons;
	}
	
	//Methode qui renvoie le nom de la ville
	public String getName() {
		return this.nom;
	}
		
	/* Methodes d'entree */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setType(String type) {
		this.type = Ville.VTaille.valueOf(type);
	}
	
	public void setTouristique(String bool) {
		if (bool.equalsIgnoreCase("oui")) this.touristique = true;
	}
	
	public void setLatitude(float x) {
		this.coordonnees[0] = x;
	}
	
	public void setLongitude(float x) {
		this.coordonnees[1] = x;
	}
	
	/* Methodes de sortie */
	
	public String getNom() {
		return this.nom;
	}
	
	public VTaille getType() {
		return this.type;
	}
	
	public boolean getTouristique() {
		return this.touristique;
	}
	
	public float getLatitude() {
		return this.coordonnees[0];
	}
	
	public float getLongitude() {
		return this.coordonnees[1];
	}
	
	public int getId() {
		return this.id;
	}
	
	public Float getLongitude2() {
		return this.longitude;
	}
	public Float getLatitude2() {
		return this.latitude;
	}
	
	/* */
	
	/* L'affiche */
	public String toString() {
		String res = this.getId() + ". ";
		res+= this.getNom() + " | ";
		res+= this.getType() + " | ";
		if (this.touristique) {
			res+= "touristique | ";
		}
		res+= "[" + this.getLatitude() + ", " + this.getLongitude() + "]";
		return res;
}
}