package edt;

import java.util.ArrayList;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.unary.PropNotMemberBound;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;

public class Lanceur_EDT {
	
	public enum GroupeType {C,TD,TP};
	public enum Groupe {C,TDA,TDB,TDC,TPA1,TPA2,TPB1,TPB2,TPC1,TPC2,APP};

	
	public static void main(String[] args) {
		
		Modele_EDT model=new Modele_EDT();
		
		Initialisation_EDT nouveauCalcul=new Initialisation_EDT(model);
		nouveauCalcul.creationModules();
		nouveauCalcul.creationProfs();
		nouveauCalcul.creationPlages();
		
		ArrayList<Seance> listePlagesS1=new ArrayList<Seance>();
		ArrayList<Seance> listePlagesS3=new ArrayList<Seance>();
		for(Seance p:model.getListePlages()){
			if(p.getSemestre()==1)listePlagesS1.add(p);
			else listePlagesS3.add(p);
		}
		
		/*
		 *-------------------- AJOUT DES CONTRAINTES AU MODÈLE
		 */
		//toutes les plages d'un même prof sont disjointes
		for(Professeur p:model.getListeProfs()){
			for(Constraint c:p.ctr_plagesDisjointes()){
				model.post(c);
			}
		}
		//on ne peut pas mettre de S1 en parallèle avec une plage du groupe C
		//idem en S3
		for(int i=0;i<model.getListeCours().size();i++){
			Cours p=(Cours)model.getListeCours().get(i);
			for(int j=i+1;j<model.getListePlages().size();j++){
				Seance q=model.getListePlages().get(j);
				if(!p.equals(q) && p.getSemestre()==q.getSemestre()){
					model.post(p.ctr_plagesDisjointes(q));
				}
			}
		}
		//on met les cours avant les tds et les tds avant les tps pour un module donné
		
		/*for(Module m:model.getListeModules()){
			for(Cours p1:m.getListePlages()){
				GroupeType x=p1.getGroupeType();
				if(x==GroupeType.C){
					for(Cours p2:m.getListePlages()){
						if(p2.getGroupeType()==GroupeType.TD){
							model.post(p1.precede(p2));
						}
					}
				}
				/*else{
					/*if(x==GroupeType.TD){
						for(Cours p2:m.getPlages()){
							if(p2.getGroupeType()==GroupeType.TP){
								model.post(p1.precede(p2));
							}
						}
					}
				}
				
			}
		}*/
		
		
		//sur du S1 on ne peut mettre sur une plage que 1 C,
		//3 TD ou 6 TP ou toute combi 
		IntVar[] vars=new IntVar[model.getListePlages().size()];
		for(int i=0;i<vars.length;i++){
			vars[i]=model.getListePlages().get(i).getIntVarDebut();
		}
		
		Solver solver=model.getSolver();
		
		//On demande au solveur de trouver une solution
		solver.findSolution();
		solver.printStatistics();
		for(Seance p:model.getListePlages()){
			System.out.println(p);
		}
		//Operations.afficheTab(model.getOuvertures());
	}
}
	
