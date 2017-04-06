package edt;

public class ProfesseurFactory {

	public ProfesseurFactory(Modele_EDT model,String...strings){
		for(String s:strings){
			model.ajouter(new Professeur(s));
		}
	}
}
