package xml;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ModelesGPS.Ville;
import ModelesGPS.Route;


public class xmlEntree {
	
	private static ArrayList<Ville> villes;
	private static ArrayList<Route> routes;
	
	private String nom;
	xmlEntree(String f) {
		this.nom = f;
	}
	
	public static void main(String args[]) {
		String f1 = "exemple_carte.xml";
		xmlEntree en = new xmlEntree(f1);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
	         SAXParser saxParser = factory.newSAXParser();
	         handler userhandler = new handler();
	         saxParser.parse(en.nom, userhandler);
	         
	         villes = userhandler.getVilles();
	         routes = userhandler.getRoutes();
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
	}
}
