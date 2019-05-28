import java.util.ArrayList;

public class Route {

	private String nom;
	private TypeDeRoute type;
	private ArrayList<Troncon> troncons;
	
	public Route(String n, TypeDeRoute t, ArrayList<Troncon> ts){
		this.nom = n;
		this.type = t;
		this.troncons = ts;
	}
	
	//Methode qui facilite l'affichage
	public String toString(){
		String res = "Le nom de la route est : " + this.nom + " Le type est : " + this.type + " dont les troncons sont : ";
		for(int i = 0; i<this.troncons.size(); i++){
			res+= this.troncons.get(i).toString()+"\n";
		}
		return res;
	}
	
	//Methode qui renvoie le prix total de la route
	public int prixRouteTotal(){
		int res = 0;
		for(int i = 0; i<this.troncons.size(); i++){
			res += this.troncons.get(i).prixAPayer();
		}
		return res;
	}
}
