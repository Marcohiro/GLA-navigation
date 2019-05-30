package ModelesGPS;

public class HistoriqueUnite {
	private Vehicule vehicule;
	private Trajet trajet;
	private Parametres param;
	
	public HistoriqueUnite(Vehicule v, Trajet t, Parametres p) {
		this.vehicule = v;
		this.trajet = t;
		this.param = p;
	}
	
	public Vehicule getVehicule() {
		return this.vehicule;
	}
	
	public Trajet getTrajet() {
		return this.trajet;
	}
	
	public Parametres getParametres() {
		return this.param;
	}
}
