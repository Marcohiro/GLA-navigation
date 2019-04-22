package Autres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ModelesGPS.Ville;
import ModelesGPS.Route;
import ModelesGPS.Troncon;

public class Calculs {
	private int[][] matrice;
	private ArrayList<Integer>[] liste;
	
	public Calculs(ArrayList<Ville> villes, ArrayList<Route> routes) {
		this.matrice = new int[villes.size()][villes.size()];
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				matrice[i][j] = Integer.MAX_VALUE;
			}
		}
		this.liste = new ArrayList[villes.size()];
		Arrays.setAll(liste, ArrayList :: new);
	}
	
	public int distanceAerienne(Ville v1, Ville v2) {
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
	
	public void construireLesDonnees(ArrayList<Ville> villes, ArrayList<Route> routes) {
		for (Route r : routes) {
			
		}
	}
}
