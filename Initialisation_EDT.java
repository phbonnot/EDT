package edt;


import edt.Lanceur_EDT.Groupe;

public class Initialisation_EDT {

	private Modele_EDT model;
	
	public Initialisation_EDT(Modele_EDT model){
		this.model=model;
	}
	
	public void  creationModules(){
		Module m1101=new Module(1101,"Introduction aux systèmes");
		Module m1102=new Module(1102,"Algorithmique, programmation");
		Module m1104=new Module(1104,"Introduction aux bases de données");
		Module m1105=new Module(1105,"Conception de documents et d'interfaces numériques");
		Module m1201=new Module(1201,"Mathématiques discrètes");
		Module m1203=new Module(1203,"Environnement économique");
		Module m1205=new Module(1205,"communication");
		Module m1206=new Module(1206,"Anglais et informatique");
		this.model.ajouter(m1101,m1102,m1104,m1105,m1201,m1203,m1205,m1206);
	}
	
	
	public void creationProfs(){
		
		ProfesseurFactory pf=new ProfesseurFactory(this.model,"JHR","CLD","MyL","MH","PB","NN",
				"AR","GD","GaN","JT","MaC","AzA","SL","FK","PP","CyL");
	}

	public void creationPlages(){
		Seance sys=new Cours(this.model,1101,60,"CLD",Groupe.C);
		Seance maths=new Cours(this.model,1201,60,"MH",Groupe.C);
		Seance ap=new Cours(this.model,1102,60,"JHR",Groupe.C);
		
		Seance sysTDA=new TD(this.model,1101,90,"NN",Groupe.TDA);
		Seance sysTDB=new TD(this.model,1101,90,"CyL",Groupe.TDB);
		Seance sysTDC=new TD(this.model,1101,90,"MH",Groupe.TDC);
		
		Seance mathsTDA=new TD(this.model,1201,90,"MH",Groupe.TDA);
		Seance mathsTDB=new TD(this.model,1201,90,"MH",Groupe.TDB);
		Seance mathsTDC=new TD(this.model,1201,90,"PB",Groupe.TDC);
		
		this.model.ajouter(sys,maths,ap,sysTDA,sysTDB,sysTDC,mathsTDA,mathsTDB,mathsTDC);
		model.post(sys.precede(sysTDA));
		model.post(sys.precede(sysTDB));
		model.post(sys.precede(sysTDC));
		model.post(maths.precede(mathsTDA));
		model.post(maths.precede(mathsTDB));
		model.post(maths.precede(mathsTDC));
		//model.post(sys.ctr_plagesDisjointes(maths));
		//model.post(sys.ctr_plagesDisjointes(ap));
		//model.post(maths.ctr_plagesDisjointes(ap));
	}
	
	
}
