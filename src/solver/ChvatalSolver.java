package solver;

import java.util.TreeSet;

import util.ElementSet;

public class ChvatalSolver extends GreedySolver {

	@Override
	public ElementSet nextBestSet() {
		ElementSet selected = new ElementSet();
		double previousRatio = Double.MAX_VALUE;
		int overlap = 0;
		
		for(ElementSet eachSet: _unusedSets) { //iterate through all sets in unused sets 
			for(int everyInt: eachSet.getElements() ) { //iterate through each integer in eachSet
				if(_elementsToCover.contains(everyInt)) { 
					overlap++; //count overlap of set and universe (elementstocover) 
				}
			}
			if (eachSet.getCost()/(double)overlap < previousRatio ) { 
				selected = eachSet; //update selected if current ratio better than previous ratio 
				previousRatio = eachSet.getCost()/(double)overlap; 
			}	
			overlap = 0; //reset overlap 
		}
		return selected;
	}
	
	//name constructor 
	public ChvatalSolver() {
		_name = "Chvatal";
	}
	
	
	
//	
//	public ElementSet nextBestSet() {
//		
//		
//		ElementSet selected = new ElementSet();
//		double cost = _unusedSets.first().getCost(); //initialize as cost of first just to have smthing to compare to 
//		
//		
//		//check if theres at least one element covered, then select lowest cost 
//		
//		
//		//System.out.println("this is the model" + _unusedSets);
//		
//		//selected = _unusedSets.first(); 
//		TreeSet<Integer> intersect = new TreeSet<Integer>();
//		
//		for(ElementSet each: _unusedSets) {
//			intersect.addAll(_elementsToCover);
//			
//			intersect.retainAll(each.getElements()); //intersect only retains elements they have in common 
//			
//			if(cost > each.getCost()) { //if current cost is greater than cost of each  
//				if(!intersect.isEmpty()) { //checks to see if at least one uncovered element will be covered 
//				selected = each; 
//				cost = each.getCost();
//			}
//		}
//			intersect.clear();
//		}
//		
//		//System.out.println("this is the next best set:" + selected);
//		
//		return selected;
//	}

}
