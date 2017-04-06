package edt;

import java.util.ArrayList;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

public class Professeur {

	private String nom;
	private ArrayList<Seance> listeCours;
	
	
	public Professeur(String nom){
		this.nom=nom;
		this.listeCours=new ArrayList<Seance>();
	}
	
	
	
	public ArrayList<Seance> getListeCours(){
		return this.listeCours;
	}
	
	public void ajouterPlage(Seance p){
		this.listeCours.add(p);
	}
	
	public String toString(){
		return this.nom;
	}
	
	public boolean equals(Professeur p){
		return this.nom.equals(p.toString());
	}
	
	public IntVar[] intVarTab(){
		IntVar[] tab=new IntVar[this.listeCours.size()];
		for(int i=0;i<tab.length;i++){
			tab[i]=this.listeCours.get(i).debut;
		}
		return tab;
	}
	
	public ArrayList<Constraint> ctr_plagesDisjointes(){
		ArrayList<Constraint> ctr_plagesDisjointes=new ArrayList<Constraint>();
		for(int i=0;i<this.listeCours.size();i++){
			Seance p1=this.listeCours.get(i);
			for(int j=i+1;j<this.listeCours.size();j++){
				Seance p2=this.listeCours.get(j);
				ctr_plagesDisjointes.add(p1.ctr_plagesDisjointes(p2));
			}
		}
		return ctr_plagesDisjointes;
	}
}
