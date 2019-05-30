package xml;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ModeleGPS.Route;
import ModeleGPS.Troncon;
import ModeleGPS.Ville;	

public class Reader {

	//Charger les villes
	public ArrayList<Ville> listeVilles() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ArrayList<Ville> listVilles = new ArrayList<Ville>();
		try {
			
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			//ErrorHandler errHandler = new ErrorHandler();
			File fileXML = new File("exemple_carte.xml");
			Document  xml = builder.parse(fileXML);
			Element root = xml.getDocumentElement();
			XPathFactory xpf = XPathFactory.newInstance();
			XPath path = xpf.newXPath();
			String expression  = "//ville";
			NodeList list = (NodeList) path.evaluate(expression, root, XPathConstants.NODESET);
			int nodeLenght = list.getLength();
			//Parcours de la boucle
			for(int i = 0; i<nodeLenght;i++) {
				
				NodeList name = (NodeList) path.evaluate("//ville/nom", root, XPathConstants.NODESET) ;
				NodeList type = (NodeList) path.evaluate("//ville/type", root, XPathConstants.NODESET);
				NodeList touristique = (NodeList) path.evaluate("//ville/touristique", root, XPathConstants.NODESET);
				NodeList longitude = (NodeList) path.evaluate("//ville/coordonnees/longitude", root, XPathConstants.NODESET);
				NodeList latitude = (NodeList) path.evaluate("//ville/coordonnees/latitude", root, XPathConstants.NODESET);
				String n = name.item(i).getTextContent(), t = type.item(i).getTextContent(), to = touristique.item(i).getTextContent(), lo = longitude.item(i).getTextContent(), la = latitude.item(i).getTextContent();
				listVilles.add(new Ville(n,t,to,Float.parseFloat(lo),Float.parseFloat(la)));
				System.out.println(listVilles.get(i).toString());
				
			}
			
			//String str = path.evaluate(expression, root);
			//System.out.print(str);
			return listVilles;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listVilles; 
	}
	
	//Charger les routes
	public void listeRoute() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			//ErrorHandler errHandler = new ErrorHandler();
			File fileXML = new File("exemple_carte.xml");
			Document  xml = builder.parse(fileXML);
			Element root = xml.getDocumentElement();
			XPathFactory xpf = XPathFactory.newInstance();
			XPath path = xpf.newXPath();
			String expression  = "//route";
			NodeList list = (NodeList) path.evaluate(expression, root, XPathConstants.NODESET);
			int nodeLenght = list.getLength();
			//Parcours de la boucle
			Node n = null;
			ArrayList<Route> routes =new ArrayList<Route>();			
			for(int i = 0; i<nodeLenght;i++) {
				 NodeList nom = (NodeList) path.evaluate("//route/nom", root, XPathConstants.NODESET);
				 NodeList type = (NodeList) path.evaluate("//route/type", root, XPathConstants.NODESET);
				 String no = nom.item(i).getTextContent();
				 String ty = type.item(i).getTextContent();
				 
			}
			
			//String str = path.evaluate(expression, root);
			//System.out.print(str);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//Charger les tronçons
	public void listeTroncon() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			//ErrorHandler errHandler = new ErrorHandler();
			File fileXML = new File("exemple_carte.xml");
			Document  xml = builder.parse(fileXML);
			Element root = xml.getDocumentElement();
			XPathFactory xpf = XPathFactory.newInstance();
			XPath path = xpf.newXPath();
			String expression  = "//route/troncon";
			NodeList list = (NodeList) path.evaluate(expression, root, XPathConstants.NODESET);
			int nodeLenght = list.getLength();
			//Parcours de la boucle
			Node n = null;
			ArrayList<Troncon> listeTroncon = new ArrayList<Troncon>();
			
			for(int i = 0; i<nodeLenght;i++) {
				 n = list.item(i);
				 NodeList ville1 = (NodeList) path.evaluate("//route/troncon/ville1", root, XPathConstants.NODESET);
				 NodeList ville2 = (NodeList) path.evaluate("//route/troncon/ville2", root, XPathConstants.NODESET);
				 NodeList vitesse = (NodeList) path.evaluate("//route/troncon/vitesse", root, XPathConstants.NODESET);
				 NodeList touristique = (NodeList) path.evaluate("//route/troncon/touristique", root, XPathConstants.NODESET);
				 NodeList radar = (NodeList) path.evaluate("//route/troncon/radar", root, XPathConstants.NODESET);
				 NodeList payant = (NodeList) path.evaluate("//route/troncon/payant", root, XPathConstants.NODESET);
				 NodeList longueur = (NodeList) path.evaluate("//route/troncon/longueur", root, XPathConstants.NODESET);
				 String v1=ville1.item(i).getTextContent(), v2=ville2.item(i).getTextContent(), to=touristique.item(i).getTextContent(), ra=radar.item(i).getTextContent();
				 String pa = payant.item(i).getTextContent(), vi = vitesse.item(i).getTextContent(), lo = longueur.item(i).getTextContent();
				 
				 listeTroncon.add(new Troncon(v1,v2,Integer.parseInt(vi),Integer.parseInt(lo),to,ra,pa));
				 System.out.print(listeTroncon.get(i).getTouristique());
			}
			
			//String str = path.evaluate(expression, root);
			//System.out.print(str);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}