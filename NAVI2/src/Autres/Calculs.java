package Autres;

import java.util.ArrayList;
import java.util.Arrays;

import Autres.TronconL;
import ModelesGPS.Ville;
import ModelesGPS.Parametres;
import ModelesGPS.Route;
import ModelesGPS.Troncon;

public class Calculs {
	private ArrayList<TronconL>[][] matrice;
	private ArrayList<Integer>[] liste;
	
	
	public Calculs(ArrayList<Ville> villes) {
		this.matrice = new ArrayList[villes.size()][villes.size()];
		for (ArrayList<TronconL>[] a : matrice) {
			Arrays.setAll(a, ArrayList<TronconL> :: new);
		}
		
		this.liste = new ArrayList[villes.size()];
		Arrays.setAll(liste, ArrayList<Integer> :: new);
	}
	
	public static int distanceAerienne(Ville v1, Ville v2) {
		/* Formule d'Haversine */
		int rayon = 6371;
		
		double v1_lat = Math.toRadians(v1.getLatitude());
		double v2_lat = Math.toRadians(v2.getLatitude());
		double delta_lat = Math.toRadians(v2.getLatitude() - v1.getLatitude());
		double delta_long = Math.toRadians(v2.getLongitude() - v1.getLongitude());
		
		double a = Math.sin(delta_lat/2) * Math.sin(delta_lat/2) +
				   Math.cos(v1_lat) * Math.cos(v2_lat) *
				   Math.sin(delta_long/2) * Math.sin(delta_long/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return (int) Math.round(rayon * c);
	}
	
	public void construireLesDonnees(ArrayList<Troncon> troncons, Parametres p) {
		for (Troncon t : troncons) {
			//int tmp = this.distanceAerienne(t.getV1(), t.getV2());
			int tmp;
			if (p.getDistancePlusCourte()) tmp = t.getLongueur();
			else tmp = Math.round(t.getLongueur()/t.getVitesse());
			
			TronconL tl = new TronconL(t.getId(), tmp);
			this.matrice[t.getV1().getId()][t.getV2().getId()].add(tl);
			this.liste[t.getV1().getId()].add(t.getV2().getId());
		}
	}
	
	public ArrayList<TronconL>[][] getMatrice() {
		return this.matrice;
	}
	
	public ArrayList<Integer>[] getListe() {
		return this.liste;
	}
}
