package ModelesGPS;

public class Parametres {
	
	private int prixMax;
	private int volumeCO;
	private boolean enregistre; 
	private boolean distancePlusCourte;
	private boolean rapideDuree;	
	
	public Parametres(int p, int v, boolean enr, boolean dc, boolean rd){
		this.prixMax = p;
		this.volumeCO = v;
		this.enregistre = enr;
		this.distancePlusCourte = dc;
		this.rapideDuree = rd;
	}
	
	public Parametres(boolean dc, boolean rd){
		this.distancePlusCourte = dc;
		this.rapideDuree = rd;
		this.enregistre = false;
	}
	
	//Affiche les parametres sous forme de String
	public boolean getDistancePlusCourte() {
		return this.distancePlusCourte;
	}
	
	public void echangeHistorique() {
		if (this.enregistre == false) this.enregistre = true;
		else this.enregistre = false;
	}
	
	public boolean getEnregistre() {
		return this.enregistre;
	}
	
	public String toString(){
		return this.prixMax + " " + this.volumeCO + " " + this.distancePlusCourte + " " + this.rapideDuree;
	}

	//Echange si l'utilisateur souhaite ou une distance plus courte ou un trajet plus rapide.
	public void echanger(){
		if(this.distancePlusCourte){this.rapideDuree = true; this.distancePlusCourte = false;}
		else{this.rapideDuree = false; this.distancePlusCourte = true;}
	}
}