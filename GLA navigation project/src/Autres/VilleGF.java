package Autres;

public class VilleGF implements Comparable<VilleGF> {
	
	private int id;
	private int g;
	private int f;
	/* Constructeur */
	public VilleGF(int id, int g, int f) {
		this.id = id;
		this.g = g;
		this.f = f;
	}

	/* Methodes */
	@Override
	public int compareTo(VilleGF v) {
		if (v.getF() == this.getF()) return 0;
		else if (this.getF() < v.getF()) return -1;
		else return 1;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getF() {
		return this.f;
	}
	
	public int getG() {
		return this.g;
	}
	
	public String toString() {
		String res = this.getId() + ". [";
		res+= this.getG() + " | ";
		res+= this.getF() + "]";
		return res;
	}
}

