import java.util.ArrayList;

public class Historique {
	
	private ArrayList<Parametres> listeDeParametres;
	private ArrayList<Vehicule> listeDesVehicules;
	private ArrayList<Trajet> listeDesTrajets;
	
	public Historique(){
		this.listeDeParametres = new ArrayList<>();
		this.listeDesVehicules = new ArrayList<>();
		this.listeDesTrajets = new ArrayList<>();
	}
	
	//Methode qui facilite l'affichage
	public String toString(){
		String res = "Les parametres utilisés :";
		for(int i = 0;i<this.listeDeParametres.size(); i++) res+= this.listeDeParametres.get(i).toString()+"\n";
		res += "Liste des vehicules :";
		for(int i = 0;i<this.listeDesVehicules.size(); i++) res+= this.listeDesVehicules.get(i).toString()+"\n";
		res += "Liste des trajets :";
		for(int i = 0;i<this.listeDesTrajets.size(); i++) res+= this.listeDesTrajets.get(i).toString()+"\n";
		return res;
	}
	
	//Methode qui permet d'ajouter un vehicule
	public void ajouterVehicule(Vehicule v){
		this.listeDesVehicules.add(v);
	}
	//Methode qui permet d'ajouter des parametres
	public void ajouterParametres(Parametres p){
		this.listeDeParametres.add(p);
	}
	
	//Methode qui permet d'ajouter un Trajet
	public void ajouterTrajet(Trajet t){
		this.listeDesTrajets.add(t);
	}
	
	//Methode qui permet de modifier un vehicule
	public void modifierVehicule(Vehicule v){
		
	}
	
	//Methode qui permet de modifier un Trajet
	public void modifierTrajet(Trajet t){
		
	}
	
	//Methode qui permet de modifier des parametres
	public void modifierParametres(Parametres p){
		
	}
	
	//Methode qui permet de supprimer un Trajet de l'historique
	public void supprimerTrajet(Trajet t){
		this.listeDesTrajets.remove(t);
	}
	
	//Methode qui permet de supprimer un Vehicule de l'historique
	public void supprimerVehicule(Vehicule v){
		this.listeDesVehicules.remove(v);
	}
	
	//Methode qui permet de supprimer des parametres de l'historique
	public void supprimerParametres(Parametres p){
		this.listeDeParametres.remove(p);
	}
	
	//Methode qui permet de determiner si l'historique contient un trajet passe en parametres
	
	//Methode qui permet de determiner si l'historique contient des parametres passe en parametres
	public Boolean contientParametres(Parametres p){
		return this.listeDeParametres.contains(p);
	}
	//Methode qui permet de determiner si l'historique contient un vehicule passe en parametres
	public Boolean contientVehicule(Vehicule v){
		return this.listeDesVehicules.contains(v);
	}
}