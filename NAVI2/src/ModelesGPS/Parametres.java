package ModelesGPS;

public class Parametres {
	
	private int prixMax;
	private int volumeCO;
	private Boolean distancePlusCourte;
	private Boolean rapideDuree;	
	
	public Parametres(int p, int v, boolean dc, boolean rd){
		this.prixMax = p;
		this.volumeCO = v;
		this.distancePlusCourte = dc;
		this.rapideDuree = rd;
	}
	
	public Parametres(boolean dc, boolean rd){
		this.distancePlusCourte = dc;
		this.rapideDuree = rd;
	}
	
	//Affiche les parametres sous forme de String
	public boolean getDistancePlusCourte() {
		return this.distancePlusCourte;
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