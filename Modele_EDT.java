package edt;

import java.util.ArrayList;

import org.chocosolver.solver.Model;

public class Modele_EDT extends Model {

	private int[] ouverture;
	private ArrayList<Module> listeModules;
	private ArrayList<Professeur> listeProfs;
	private ArrayList<Seance> listeSeances;
	private ArrayList<Seance> listeCours;
	private ArrayList<Seance> listeTDs;
	private ArrayList<Seance> listeTPs;
	
	
	public Modele_EDT(){
		super();
		this.ouverture=new int[90];
		for(int i=0;i<90;i++){
			this.ouverture[i]=i;
		}
		this.listeModules=new ArrayList<Module>();
		this.listeProfs=new ArrayList<Professeur>();
		this.listeSeances=new ArrayList<Seance>();
		this.listeCours=new ArrayList<Seance>();
		this.listeTDs=new ArrayList<Seance>();
		this.listeTPs=new ArrayList<Seance>();
	}

	public ArrayList<Module> getListeModules() {
		return listeModules;
	}

	public ArrayList<Professeur> getListeProfs() {
		return listeProfs;
	}

	public ArrayList<Seance> getListePlages() {
		return listeSeances;
	}
	
	public ArrayList<Seance> getListeCours() {
		return listeCours;
	}

	public int[] getOuvertures(){
		return this.ouverture;
	}
	
	public void ajouter(Seance... args){
		for(Seance c:args){
			this.listeSeances.add(c);
		}
	}
	
	public void ajouter(Professeur... args){
		for(Professeur p:args){
			this.listeProfs.add(p);
		}
	}
	
	public void ajouter(Module... modules ){
		for(Module m:modules){
			this.listeModules.add(m);
		}
	}
	
	public void ajouterCours(Cours...cours ){
		for(Cours c:cours){
			this.listeCours.add(c);
		}
	}
	
	public void ajouterTD(TD...cours ){
		for(TD c:cours){
			this.listeTDs.add(c);
		}
	}
	
	public void ajouterTP(TP...cours ){
		for(TP c:cours){
			this.listeTPs.add(c);
		}
	}
}
