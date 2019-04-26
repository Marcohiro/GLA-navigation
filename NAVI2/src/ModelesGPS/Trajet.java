package ModelesGPS;
import java.util.ArrayList;

public class Trajet {
	private int distance;
	private String date;
	private int duree;
	private Ville villeDepart;
	private Ville villeArrivee;
	private ArrayList<Ville> villeIntermediaires;
	private ArrayList<Troncon> tronconAParcourir;
	private ArrayList<Integer> tronconParId;
	//private ArrayList<Route> routesAParcourir;
	//private Historique historique;
	
	public Trajet(int distance, String date, int duree, Ville d, Ville a){
		this.distance = distance;
		this.date = date;
		this.duree = duree;
		this.villeDepart = d;
		this.villeArrivee = a;
		this.villeIntermediaires = new ArrayList<>();
		this.tronconAParcourir = new ArrayList<>();
		//this.routesAParcourir = new ArrayList<>();
		//this.historique = h;
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
	
	public ArrayList<Integer> getItineraire() {
		return this.tronconParId;
	}
}
