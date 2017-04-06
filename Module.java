package edt;

import java.util.ArrayList;

public class Module {

	private int num;
	private String titre;
	private ArrayList<Seance> listePlages;
	
	public Module(int num, String titre){
		this.num=num;
		this.titre=titre;
		this.listePlages=new ArrayList<Seance>();
	}
	
	public void ajouterUnePlage(Seance p){
		this.listePlages.add(p);
	}
	
	public int getSemestre(){
		return this.num/1000;
	}
	
	
	public ArrayList<Seance> getListePlages(){
		return this.listePlages;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public String toString(){
		return this.titre;
	}
	
	public boolean equals(Module m){
		return this.num==m.num;
	}
}
