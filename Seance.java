package edt;


import edt.Lanceur_EDT.Groupe;
import edt.Lanceur_EDT.GroupeType;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.binary.PropGreaterOrEqualX_YC;

import org.chocosolver.solver.variables.IntVar;


public abstract class Seance {

	private Modele_EDT model;
	private Module module;
	public int duree;
	private Professeur prof;
	public IntVar debut;
	private String[] jours={"lundi","mardi","mercredi","jeudi","vendredi"};
	private Groupe groupe;
	
	public Seance(Modele_EDT model,int mod,int duree,String p,Groupe gr){
		this.model=model;
		this.duree=duree/30;
		this.debut=model.intVar(0,90);
		for(Module m:model.getListeModules()){
			if(m.getNum()==mod)this.module=m;
		}
		for(Professeur pr:model.getListeProfs()){
			if(pr.toString().equals(p))this.prof=pr;
		}
		this.groupe=gr;
		
	}
		
	
	public String toString(){
		int heureDebut=this.getHeure(this.debut.getValue());
		int minDebut=this.getMinutes(this.debut.getValue());
		int heureFin=this.getHeure(this.debut.getValue()+duree);
		int minFin=this.getMinutes(this.debut.getValue()+duree);
		return "module "+this.module+" : \n groupe "+this.getGroupe()+"   Professeur : "+this.prof+"\n\t"+
			this.getJour(this.debut.getValue())+"\n\t   début : "+
			heureDebut+" h"+minDebut+"\n\t   fin : "+heureFin+" h"+minFin+" "+this.debut.getUB();
		//return this.getGroupe()+" "+String.valueOf(this.debut.getValue())+" "+this.debut.getUB();
	}
	
	public Module getModule(){
		return this.module;
	}
	
	public int getHeure(int n){
		int dh=(n%18)/2;
		return dh+9;
		
	}
	
	public int getMinutes(int n){
		int dh=(n%18)%2;
		return 30*dh;
	}
	
	public String getJour(int n){
		return this.jours[n/18];
	}
	
	public int getSemestre(){
		return this.module.getSemestre();
	}
	
	public IntVar getIntVarDebut(){
		return this.debut;
	}
	
	
	public  Groupe getGroupe(){
		return this.groupe;
	}
	
	public abstract GroupeType getGroupeType();
	
	public Professeur getProf(){
		return this.prof;
	}
	
	public boolean aMemeProfQue(Seance p){
		return this.prof.equals(p.getProf());
	}
	
	public boolean plagesDisjointes(Seance p){
		return (this.debut.getValue()+this.duree<p.debut.getValue()
				|| p.debut.getValue()+p.duree<this.debut.getValue());
	}
	
	public Constraint ctr_plagesDisjointes(Seance p){
		IntVar[] intVarTab;
		int c;
		
		if(this.debut.getValue()<p.debut.getValue()){
			intVarTab=new IntVar[]{p.debut,this.debut};
			c=this.duree;
		}
		else{
			intVarTab=new IntVar[]{this.debut,p.debut};
			c=p.duree;
		}
		return new Constraint("",new PropGreaterOrEqualX_YC(intVarTab,c));
	}
	
	public Constraint precede(Seance p){
		IntVar[] intVarTab=new IntVar[]{p.debut,this.debut};
		return new Constraint("",new PropGreaterOrEqualX_YC(intVarTab,this.duree));
	}
	
	public boolean equals(Seance p){
		return this.module==p.module && this.duree==p.duree &&
				this.prof.equals(p.prof) && this.getGroupe()==p.getGroupe();
	}
	
}
