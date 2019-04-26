package ModelesGPS;

public class Ville {
	
	public static enum VTaille {petite, moyenne, grande};
	
	/* Attributs */
	private int id;
	private String nom;
	private VTaille type;
	private boolean touristique;
	private float[] coordonnees;
	
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
	
	public Ville(int id) {
		this.id = id;
		this.nom = null;
		this.type = null;
		this.touristique = false;
		this.coordonnees = new float[2];
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
