package ModeleGPS;

import java.util.ArrayList;

public class Route {

public static enum TRoute {nationale, autoroute};
	
	private int id;
	private String nom;
	private TRoute type;
	private ArrayList<Troncon> Troncons;
	
	public Route(int id) {
		this.id = id;
		this.nom = null;
		this.type = null;
		this.Troncons = new ArrayList<Troncon>();
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setType(String type) {
		this.type = TRoute.valueOf(type);
	}
	
	public void ajouteTroncon(Troncon t) {
		this.Troncons.add(t);
	}
	
	public void ajouteTroncons(ArrayList<Troncon> t) {
		this.Troncons = t;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public TRoute getType() {
		return this.type;
	}
	
	public ArrayList<Troncon> getTroncons() {
		return this.Troncons;
	}
	
	public String toString() {
		String res = "";
		res+= this.getNom() + " | ";
		res+= this.getType() + " | ";
		for (Troncon x : this.Troncons) {
			res+= "[" + x.getV1().getNom() + ", " + x.getV2().getNom() + ", " + x.getLongueur() + "]";
		}
		return res;
}
}
