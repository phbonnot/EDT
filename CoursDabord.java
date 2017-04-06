package edt;

import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelectorWithTies;
import org.chocosolver.solver.variables.IntVar;

public class CoursDabord implements VariableSelector<IntVar> {

	
	private Modele_EDT model;
	
	public CoursDabord(Modele_EDT model){
		this.model=model;
	}
	
	@Override
	public IntVar getVariable(IntVar[] variables) {
		/*IntVar var=null;
		if(variables.length>0){
			var=variables[0];
		
			
		}*/
		return null;
	}
	
	

}
