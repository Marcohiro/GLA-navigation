package ModelesGPS;

import java.util.ArrayList;


public class Historique {
	private ArrayList<HistoriqueUnite> h;
	
	public Historique() {
		this.h = new ArrayList<HistoriqueUnite>();
	}
	
	public void ajouteTrajet(HistoriqueUnite hu) {
		this.h.add(hu);
	}
	
	public static ArrayList<HistoriqueUnite> trajetEnregistre(Trajet t, Historique h) {
		ArrayList<HistoriqueUnite> res = new ArrayList<HistoriqueUnite>();
		for (HistoriqueUnite x : h.getHistorique()) {
			if (	x.getTrajet().getVilleDepart() == t.getVilleDepart() && 
					x.getTrajet().getVilleArrivee() == t.getVilleArrivee()	) {
				res.add(x);
			}
		}
		return res;
	}
	
	public static ArrayList<HistoriqueUnite> rechercheParVehicule(Vehicule v, Historique h) {
		ArrayList<HistoriqueUnite> res = new ArrayList<HistoriqueUnite>();
		for (HistoriqueUnite x : h.getHistorique()) {
			if (v.equals(x.getVehicule())) {
				res.add(x);
			}
		}
		return res;
	}
	
	public static ArrayList<HistoriqueUnite> rechercheParTrajet(Trajet t, Historique h) {
		ArrayList<HistoriqueUnite> res = new ArrayList<HistoriqueUnite>();
		for (HistoriqueUnite x : h.getHistorique()) {
			if (t.equals(x.getTrajet())) {
				res.add(x);
			}
		}
		return res;
	}
	
	public static ArrayList<HistoriqueUnite> rechercheParParametres(Parametres p, Historique h) {
		ArrayList<HistoriqueUnite> res = new ArrayList<HistoriqueUnite>();
		for (HistoriqueUnite x : h.getHistorique()) {
			if (p.estEgal(x.getParametres())) {
				res.add(x);
			}
		}
		return res;
	}
	
	public ArrayList<HistoriqueUnite> getHistorique() {
		return this.h;
	}
}
