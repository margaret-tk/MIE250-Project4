package solver;

import util.ElementSet;

public class GreedyCostSolver extends GreedySolver {

	public ElementSet nextBestSet() {
		
		ElementSet selected = new ElementSet();
		//double cost = _unusedSets.first().getCost(); //initialize as cost of first just to have smthing to compare to
		double cost = Double.MAX_VALUE;
		int overlap = 0;
		
		
		for(ElementSet eachSet: _unusedSets) {
			for(int everyInt: eachSet.getElements() ) {
				if(_elementsToCover.contains(everyInt)) overlap++;
			}
			if(overlap> 0) { //only if there's at least one element of overlap 
				if(cost > eachSet.getCost()) { //if current cost is greater than cost of each   
					selected = eachSet; //update selected 
					cost = eachSet.getCost(); //update cost to compare next loop 
				}
			}
			overlap = 0; //reset overlap
		}
		
		return selected;
	}

	
public GreedyCostSolver() {
	_name = "Cost";
}

}

//THIS VERSION MIGHT WORK BETTER!!- CHECK WITH BIG CASE 
	
/*	
	
	@Override
	public ElementSet nextBestSet() {
		
			ElementSet selected = new ElementSet();
			double cost = _unusedSets.first().getCost(); //initialize as cost of first just to have smthing to compare to 
			
			
			//check if theres at least one element covered, then select lowest cost 
			
			
			//System.out.println("this is the model" + _unusedSets);
			
			//selected = _unusedSets.first(); 
			TreeSet<Integer> intersect = new TreeSet<Integer>();
			
			for(ElementSet each: _unusedSets) {
				intersect.addAll(_elementsToCover);
				
				intersect.retainAll(each.getElements()); //intersect only retains elements they have in common 
				
				if(cost > each.getCost()) { //if current cost is greater than cost of each  
					if(!intersect.isEmpty()) { //checks to see if at least one uncovered element will be covered 
					selected = each; 
					cost = each.getCost();
				}
			}
				intersect.clear();
			}
			
			//System.out.println("this is the next best set:" + selected);
			
			return selected;
		}

}

*/
