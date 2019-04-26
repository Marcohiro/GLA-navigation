package Algo;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Autres.Calculs;
import Autres.TronconL;
import Autres.VilleGF;
import ModelesGPS.Route;
import ModelesGPS.Trajet;
import ModelesGPS.Ville;

public class AEtoile {
	
	private int eval(Ville a, Ville v) {
		return Calculs.distanceAerienne(a, v);
	}
	
	private int minTroncon(Ville v1, Ville v2, ArrayList<Integer>[][] matrice) {
		int res = Integer.MAX_VALUE;
		for (int x : matrice[v1.getId()][v2.getId()]) {
			if (res > x) res = x;
		}
		return res;
	}
	
	private TronconL minTroncon(ArrayList<TronconL> tls) {
		TronconL res = tls.get(0);
		for (TronconL tl : tls) {
			if (tl.getL() < res.getL()) res = tl;
		}
		return res;
	}
	
	private void update(VilleGF v, VilleGF[] heap, int[] pos, int[] nHeap) {
		int pos_v = pos[v.getId() + 1];
		System.out.println("Pos v : " + pos_v);
		if (pos_v == 0) {
			nHeap[0]++;
			pos_v = nHeap[0];
			System.out.println("nHeap increased because we add new mem : " + nHeap[0]);
		}
		int parent = pos_v/2;
		System.out.println("Parent : " + parent);
		while (parent > 0 && heap[parent].getF() > v.getF()) {
			heap[pos_v] = heap[parent];
			pos[heap[pos_v].getId() + 1] = pos_v;
			pos_v = parent;
			parent = parent/2;
		}
		heap[pos_v] = v;
		pos[v.getId() + 1] = pos_v;
		
		for (int i = 1; i < pos.length; i++) {
			System.out.print(pos[i] + " ");
		}
		System.out.println("End of update : =================================================");
	}
	
	private VilleGF pop(VilleGF[] heap, int[] pos, int[] nHeap) {
		VilleGF v = heap[nHeap[0]];
		System.out.println("Last element of heap : " + (v.getId()+1));
		VilleGF res = heap[1];
		nHeap[0]--;
		int pos_v = 1;
		while (pos_v * 2 <= nHeap[0]) {
			System.out.println("We've gone this far !!");
			int fils = pos_v * 2;
			System.out.println("fils : " + fils );
			if (fils < nHeap[0]) {
				if (heap[fils].getF() > heap[fils+1].getF() ) {
					fils++;
				}
			}
			
			System.out.println("fils :: " + fils );
			System.out.println("v : " + (v.getId() + 1) );
			if (heap[fils].getF() >= heap[pos[v.getId()+1]].getF() ) break;
			heap[pos_v] = heap[fils];
			pos[heap[pos_v].getId() + 1] = pos_v;
			pos_v = fils;
		}
		heap[pos_v] = v;
		pos[v.getId() + 1] = pos_v;
		
		for (int i = 1; i < pos.length; i++) {
			System.out.print(pos[i] + " ");
		}
		System.out.println("Current heap size aaaaaaaaaaaaaaaaaaa " + nHeap[0]);
		System.out.println("End of pop : =================================================");
		return res;
	}
	
	public Trajet chercherSolution(Ville d, Ville a, ArrayList<Ville> villes, ArrayList<TronconL>[][] matrice, ArrayList<Integer>[] liste) {
		Trajet res = null;
		int[] trace = new int[villes.size()];
		boolean[] dejaDev = new boolean[villes.size()];
		int[] dejaDevCout = new int[villes.size()];
		VilleGF[] frontieres = new VilleGF[villes.size()+1];
		int[] pos = new int[villes.size()+1];
		
		VilleGF dgf = new VilleGF(d.getId(), 0, this.eval(a, d));
		int[] nFrontieres = new int[1];
		
		//nFrontieres[0] = 1;
		//frontieres[nFrontieres[0]] = dgf;
		//pos[dgf.getId() + 1] = 1;
		this.update(dgf, frontieres, pos, nFrontieres);
		while (nFrontieres[0] > 0) {
			System.out.println("We need to pop this out !!!!!!" + (frontieres[1].getId() + 1));
			VilleGF tmp = this.pop(frontieres, pos, nFrontieres);
			System.out.println("Current heap size aaaaaaaaaaaaaaaaaaa " + nFrontieres[0]);
			System.out.println("Current heap : ");
			for (int i = 0; i < frontieres.length; i++) {
				if (frontieres[i] != null) {
					System.out.print(frontieres[i].toString() +  " ");
				}
				else System.out.print("null ");
			}
			System.out.println();
			System.out.println("Check voisins of : " + (tmp.getId()+1));
			if (tmp.getId() == a.getId()) {
				System.out.println("Construire l'itineraire ...");
				res = this.construireSol(d, a, trace, villes, matrice);
				nFrontieres[0] = 0;
			}
			else {
				dejaDev[tmp.getId()] = true;
				dejaDevCout[tmp.getId()] = tmp.getG();
				for (int x : liste[tmp.getId()]) {
					if (!dejaDev[x]) {
						int g = tmp.getG() + this.minTroncon(matrice[tmp.getId()][x]).getL();
						VilleGF succ = new VilleGF(x, g, g + eval(a, villes.get(x)));
						trace[x] = tmp.getId();
						System.out.println("Need to update : " + (succ.getId()+1));
						this.update(succ, frontieres, pos, nFrontieres);
						
						System.out.println("Current heap : ");
						for (int i = 0; i < frontieres.length; i++) {
							if (frontieres[i] != null) {
								System.out.print(frontieres[i].toString() +  " ");
							}
							else System.out.print("null ");
						}
						System.out.println();
					}
					else {
						if (dejaDevCout[x] > tmp.getG() + this.minTroncon(matrice[tmp.getId()][x]).getL()) {
							dejaDevCout[x] = tmp.getG() + this.minTroncon(matrice[tmp.getId()][x]).getL();
							this.update(frontieres[pos[x+1]], frontieres, pos, nFrontieres);
							trace[x] = tmp.getId();
						}
					}
				}
			}
		}
		
		return res;
	}
	
	private Trajet construireSol(Ville d, Ville a, int[] trace, ArrayList<Ville> villes, ArrayList<TronconL>[][] matrice) {
		Trajet t = new Trajet(d, a);
		Ville tmp = a;
		while (tmp.getId() != d.getId()) {
			Ville tmp2 = villes.get(trace[tmp.getId()]);
			t.ajouteTronconParId(this.minTroncon(matrice[tmp2.getId()][tmp.getId()]).getId() );
			tmp = tmp2;
		}
		return t;
	}
}
