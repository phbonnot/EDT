package edt;

import edt.Lanceur_EDT.Groupe;
import edt.Lanceur_EDT.GroupeType;

public class TP extends Seance {

	
	public TP(Modele_EDT model, int mod, int duree, String p,Groupe gr) {
		super(model, mod, duree, p,gr);
		model.ajouterTP(this);
	}

	@Override
	public Groupe getGroupe() {
		return super.getGroupe();
	}

	@Override
	public GroupeType getGroupeType() {
		return GroupeType.TP;
	}

}
