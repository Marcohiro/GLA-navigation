package xml;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Algo.AEtoile;
import Autres.Calculs;
import Autres.TronconL;
import ModeleGPS.Parametres;
import ModeleGPS.Route;
import ModeleGPS.Trajet;
import ModeleGPS.Troncon;
import ModeleGPS.Ville;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class xmlEntree {
	
	private static ArrayList<Ville> villes;
	private static ArrayList<Route> routes;
	private static ArrayList<Troncon> troncons;
	
	private String nom;
	xmlEntree(String f) {
		this.nom = f;
	}
	
	public xmlEntree() {
		xmlEntree.villes = new ArrayList<>();
		xmlEntree.routes = new ArrayList<>();
		xmlEntree.troncons = new ArrayList<>();
	}
	
	public void exec(handler h) {
		String f1 = "exemple_carte.xml";
		xmlEntree en = new xmlEntree(f1);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
	         SAXParser saxParser = factory.newSAXParser();
	         handler userhandler = h;
	         saxParser.parse(en.nom, userhandler);
	         
	         villes = userhandler.getVilles();
	         routes = userhandler.getRoutes();
	         troncons = userhandler.getTroncons();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		for (Ville v : villes) {
	//		System.out.println(v.toString());
		}
		
		//System.out.println("=============================================================");
		
		for (Route r : routes) {
		//	System.out.println(r.toString());
		}
		
		//System.out.println("=============================================================");
		
		for (Troncon t : troncons) {
			//System.out.println(t.toString());
		}
		//System.out.println("=============================================================");
		
		Calculs c = new Calculs(villes);
		Parametres p = new Parametres(true, false);
		c.construireLesDonnees(troncons, p);
		ArrayList<TronconL>[][] mat = c.getMatrice();
		
		
		ArrayList<Integer>[] lis = c.getListe();
		AEtoile algo = new AEtoile();
		Trajet t = algo.chercherSolution(villes.get(0), villes.get(5), villes, mat, lis);
		ArrayList<Integer> res = t.getItineraire();
		
	}
	
	public ArrayList<Ville> read(Ville depart, Ville arrivee, handler h) {
		String f1 = "exemple_carte.xml";
		String f2 = "res_"+depart.getNom()+"_"+arrivee.getNom()+".xml";
		xmlEntree en = new xmlEntree(f1);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
	         SAXParser saxParser = factory.newSAXParser();
	         handler userhandler = h;
	         saxParser.parse(en.nom, userhandler);
	         
	         villes = userhandler.getVilles();
	         routes = userhandler.getRoutes();
	         troncons = userhandler.getTroncons();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		for (Ville v : villes) {
			System.out.println(v.toString());
		}
		
		System.out.println("=============================================================");
		
		for (Route r : routes) {
			System.out.println(r.toString());
		}
		
		System.out.println("=============================================================");
		
		for (Troncon t : troncons) {
			System.out.println(t.toString());
		}
		System.out.println("=============================================================");
		
		Calculs c = new Calculs(villes);
		Parametres p = new Parametres(true, false);
		c.construireLesDonnees(troncons, p);
		ArrayList<TronconL>[][] mat = c.getMatrice();
		
		for (int i = 0; i < villes.size(); i++) {
			for (int j = 0; j < villes.size(); j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(mat[0][3].get(0).getId());
		System.out.println("=============================================================");
		ArrayList<Integer>[] lis = c.getListe();
		AEtoile algo = new AEtoile();
		Trajet t = algo.chercherSolution(depart, arrivee, villes, mat, lis);
		ArrayList<Integer> res = t.getItineraire();
		for (int i = res.size()-1; i >= 0; i--) {
			System.out.println(	troncons.get(res.get(i)).getV1().getNom() + " => " + 
								troncons.get(res.get(i)).getV2().getNom() + " cout : " +
								troncons.get(res.get(i)).getLongueur());
			t.ajouteTroncon(troncons.get(res.get(i)));
			t.ajouteVille(troncons.get(res.get(i)).getV1());
			if (i == 0) t.ajouteVille(troncons.get(res.get(i)).getV2());
		}
		System.out.println(c.distanceAerienne(depart, arrivee));
		
		/*================================================================================*/
		
		String content = t.trajetToXML(troncons, routes);
		try (FileWriter writer = new FileWriter(f2);
			 BufferedWriter bw = new BufferedWriter(writer) ) {
	        	bw.write(content);
	    } 
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
	    }
		ArrayList<Ville> solution = t.villesAParcourir();
		return solution;
	}

}
