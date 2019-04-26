package xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ModelesGPS.Ville;
import ModelesGPS.Route;
import ModelesGPS.Troncon;

public class handler extends DefaultHandler{
	private ArrayList<Ville> villeListe = null;
	private ArrayList<Route> routeListe = null;
	private ArrayList<Troncon> tronconListe = null;
	private ArrayList<Troncon> tronconListeComplete = null;
	private Ville ville;
	private Route route;
	private Troncon troncon;
	
	private String data;
	
	public ArrayList<Ville> getVilles() {
		return villeListe;
	}
	
	public ArrayList<Route> getRoutes() {
		return routeListe;
	}
	
	public ArrayList<Troncon> getTroncons() {
		return tronconListeComplete;
	}
	
	boolean hVille = false;
	boolean hRoute = false;
	boolean hTroncon = false;
	
	boolean hNom = false;
	boolean hType = false;
	boolean hTouristique = false;
	boolean hLatitude = false;
	boolean hLongitude = false;
	
	boolean hVille1 = false;
	boolean hVille2 = false;
	boolean hVitesse = false;
	boolean hRadar = false;
	boolean hPayant = false;
	boolean hLongueur = false;
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (villeListe == null) {
			villeListe = new ArrayList<Ville>();
		}
		
		if (routeListe == null) {
			routeListe = new ArrayList<Route>();
		}
		
		if (tronconListeComplete == null) {
			tronconListeComplete = new ArrayList<Troncon>();
		}
		
		if (qName.equalsIgnoreCase("ville")) {
			int idv = this.villeListe.size();
			ville = new Ville(idv);
			hVille = true;
		}
		if (qName.equalsIgnoreCase("route")) {
			int idr = this.routeListe.size();
			route = new Route(idr);
			tronconListe = new ArrayList<Troncon>();
			hRoute = true;
		}
		if (qName.equalsIgnoreCase("troncon")) {
			int idt = this.tronconListeComplete.size();
			troncon = new Troncon(idt);
			hTroncon = true;
		}
		
		if (hRoute) {
			if (tronconListe == null) {
				tronconListe = new ArrayList<Troncon>();
			}
		}
		
		if (qName.equalsIgnoreCase("nom")) hNom = true;
		if (qName.equalsIgnoreCase("type")) hType = true;
		if (qName.equalsIgnoreCase("touristique")) hTouristique = true;
		if (qName.equalsIgnoreCase("latitude")) hLatitude = true;
		if (qName.equalsIgnoreCase("longitude")) hLongitude = true;
		if (qName.equalsIgnoreCase("vitesse")) hVitesse = true;
		if (qName.equalsIgnoreCase("radar")) hRadar = true;
		if (qName.equalsIgnoreCase("payant")) hPayant = true;
		if (qName.equalsIgnoreCase("longueur")) hLongueur = true;
		
		data = new String();
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
				
		if (qName.equalsIgnoreCase("nom")) {
			if (hVille) {
				ville.setNom(data);
			}
			else {
				route.setNom(data);
			}
			hNom = false;
		}
		
		if (qName.equalsIgnoreCase("type")) {
			if (hVille) ville.setType(data);
			else route.setType(data);
			hType = false;
		}
		
		if (qName.equalsIgnoreCase("touristique")) {
			if (hVille) ville.setTouristique(data);
			else troncon.setTouristique(data);
			hType = false;
		}		
		
		if (qName.equalsIgnoreCase("latitude")) {
			ville.setLatitude(Float.parseFloat(data));
			hLatitude = false;
		}
		
		if (qName.equalsIgnoreCase("longitude")) {
			ville.setLongitude(Float.parseFloat(data));
			hLongitude = false;
		}
		
		if (qName.equalsIgnoreCase("ville1")) {
			troncon.setV1(data, villeListe);
			hVille1 = false;
		}
		
		if (qName.equalsIgnoreCase("ville2")) {
			troncon.setV2(data, villeListe);
			hVille2 = false;
		}
		
		if (qName.equalsIgnoreCase("vitesse")) {
			troncon.setVitesse(Integer.parseInt(data));
			hVitesse = false;
		}
		
		if (qName.equalsIgnoreCase("longueur")) {
			troncon.setLongueur(Integer.parseInt(data));
			hLongueur = false;
		}
		
		if (qName.equalsIgnoreCase("radar")) {
			troncon.setRadar(data);
			hRadar = false;
		}
		
		if (qName.equalsIgnoreCase("payant")) {
			troncon.setPayant(data);
			hPayant = false;
		}
		
		if (qName.equalsIgnoreCase("ville")) {
			villeListe.add(ville);
			hVille = false;
		}
		
		if (qName.equalsIgnoreCase("route")) {
			route.ajouteTroncons(tronconListe);
			routeListe.add(route);
			hRoute = false;
		}
		
		if (qName.equalsIgnoreCase("troncon")) {
			troncon.setRoute(routeListe.size());
			tronconListe.add(troncon);
			tronconListeComplete.add(troncon);
			hTroncon = false;
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		data = new String(ch, start, length);
	}
}
