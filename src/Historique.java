import java.util.ArrayList;

public class Historique {

	private ArrayList<Parametres> listeDeParametres;
	private ArrayList<Vehicule> listeDesVehicules;
	private ArrayList<Trajet> listeDesTrajets;

	public Historique() {
		this.listeDeParametres = new ArrayList<Parametres>();
		this.listeDesVehicules = new ArrayList<Vehicule>();
		this.listeDesTrajets = new ArrayList<Trajet>();
	}

	// Methodes qui facilitent l'affichage
	public String toString() {
		String res = "Les parametres utilises :";
		for (int i = 0; i < this.listeDeParametres.size(); i++)
			res += this.listeDeParametres.get(i).toString() + "\n";
		res += "Liste des vehicules :";
		for (int i = 0; i < this.listeDesVehicules.size(); i++)
			res += this.listeDesVehicules.get(i).toString() + "\n";
		res += "Liste des trajets :";
		for (int i = 0; i < this.listeDesTrajets.size(); i++)
			res += this.listeDesTrajets.get(i).toString() + "\n";
		return res;
	}

	public String vehiculesToString() {
		String res = "";
		for (int i = 0; i < this.listeDesVehicules.size(); i++) res += this.listeDesVehicules.get(i).toString() + "\n";
		System.out.println(res);
		return res;
	}

	public String parametresToString() {
		String res = "";
		if (this.listeDeParametres.isEmpty())
			res = "La liste des parametres est vide";
		else
			for (int i = 0; i < this.listeDeParametres.size(); i++)
				res += this.listeDeParametres.get(i).toString() + "\n";
		return res;
	}

	public String trajetsToString() {
		String res = "";
		if (this.listeDesTrajets.isEmpty())
			res = "La liste des trajets est vide";
		else
			for (int i = 0; i < this.listeDesTrajets.size(); i++)
				res += this.listeDesTrajets.get(i).afficherDA() + "\n";
		return res;
	}

	// Methode qui permet d'ajouter un vehicule
	public void ajouterVehicule(Vehicule v) {
		this.listeDesVehicules.add(v);
	}

	// Methode qui permet d'ajouter des parametres
	public void ajouterParametres(Parametres p) {
		this.listeDeParametres.add(p);
	}

	// Methode qui permet d'ajouter un Trajet
	public void ajouterTrajet(Trajet t) {
		this.listeDesTrajets.add(t);
	}

	// Methode qui permet de modifier un vehicule
	public void modifierVehicule(Vehicule v) {

	}

	// Methode qui permet de modifier un Trajet
	public void modifierTrajet(Trajet t) {

	}

	// Methode qui permet de modifier des parametres
	public void modifierParametres(Parametres p) {

	}

	// Methode qui permet de supprimer un Trajet de l'historique
	public void supprimerTrajet(Trajet t) {
		this.listeDesTrajets.remove(t);
	}

	// Methode qui permet de supprimer un Vehicule de l'historique
	public void supprimerVehicule(Vehicule v) {
		this.listeDesVehicules.remove(v);
	}

	// Methode qui permet de supprimer des parametres de l'historique
	public void supprimerParametres(Parametres p) {
		this.listeDeParametres.remove(p);
	}

	// Methode qui permet de determiner si l'historique contient un trajet passe en
	// parametres
	public Boolean contientTrajet(Trajet t) {
		return this.listeDesTrajets.contains(t);
	}

	// Methode qui permet de determiner si l'historique contient des parametres
	// passe en parametres
	public Boolean contientParametres(Parametres p) {
		return this.listeDeParametres.contains(p);
	}

	// Methode qui permet de determiner si l'historique contient un vehicule passe
	// en parametres
	public Boolean contientVehicule(Vehicule v) {
		for(int i = 0; i<this.listeDesVehicules.size(); i++) {
			if(v.getName().equals(this.listeDesVehicules.get(i).getName()) && v.getCarburant() == this.listeDesVehicules.get(i).getCarburant() && v.getGabarit() == this.listeDesVehicules.get(i).getGabarit()) {
				return true;
			}
		}
		return false;
	}

	// Methode qui renvoie l'arraylist des véhicules
	public ArrayList<Vehicule> getVehicules() {
		return this.listeDesVehicules;
	}

	// Methode qui renvoie l'arraylist des parametres
	public ArrayList<Parametres> getParametres() {
		return this.listeDeParametres;
	}

	// Methode qui renvoie l'arraylist des trajets
	public ArrayList<Trajet> getTrajets() {
		return this.listeDesTrajets;
	}
}