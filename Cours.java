package edt;

import edt.Lanceur_EDT.Groupe;
import edt.Lanceur_EDT.GroupeType;

public class Cours extends Seance {

	
	public Cours(Modele_EDT model, int mod, int duree, String p,Groupe gr) {
		super(model, mod, duree, p,gr);
		model.ajouterCours(this);
	}

	@Override
	public GroupeType getGroupeType() {
		return GroupeType.C;
	}

	@Override
	public Groupe getGroupe() {
		// TODO Auto-generated method stub
		return super.getGroupe();
	}

}
