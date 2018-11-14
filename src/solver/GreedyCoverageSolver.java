package solver;

import java.util.TreeSet;

import util.ElementSet;

public class GreedyCoverageSolver extends GreedySolver {

	@Override
	public ElementSet nextBestSet() {
		ElementSet selected = new ElementSet();
		
		//int score = 0;
		int previous = 0;
		int overlap = 0;
		
		for(ElementSet eachSet: _unusedSets) {
			
			for(int everyInt: eachSet.getElements() ) { //iterate through each int in the current set
				if(_elementsToCover.contains(everyInt)) overlap++; //count overlap 
			}
			if(overlap > previous) { //if overlap greater than previous set then update selected 
				previous = overlap;
				selected = eachSet;
			}
			overlap = 0; //reset overlap 
		}
		return selected;
	}
		
		
	
//name constructor 
public GreedyCoverageSolver() {
	_name = "Coverage";
}

}
//THIS ONE MIGHT BE FASTER EVEN THOUGH IT ALLOCATES MEMORY, TEST IT OUT 
	/*	
		
		
		
		
		//System.out.println("this is the model" + _unusedSets);
		
		//selected = _unusedSets.first(); 
		
		//TreeSet<Integer> elements = new TreeSet<Integer>(_elementsToCover);
		TreeSet<Integer> intersect = new TreeSet<Integer>();
		
		//System.out.println("this should print universe" + intersect);
		
		for(ElementSet each: _unusedSets) {
			intersect.addAll(_elementsToCover);
			intersect.retainAll(each.getElements()); //intersect only retains elements they have in common 
			//System.out.println("This is the current set" + each);
			//System.out.println("This is their intersect" + intersect);
			if(score < intersect.size()) { //if intersect is greater than previous intersect 
				selected = each; 
				score = intersect.size();
			}
			intersect.clear();
		}
		
//		for(ElementSet each: _unusedSets) {
//			TreeSet<Integer> intersect = new TreeSet<Integer>(each.getElements());
//			intersect.retainAll(_elementsToCover); //intersect only retains elements they have in common 
//			if(score < intersect.size()) { //if intersect is greater than previous intersect 
//				selected = each; 
//				score = intersect.size();
//			}
//		}
		
		
		//System.out.println("this is the next best set:" + selected);
		
		return selected;
		
		//does this return null if there's no better set?
		
		
	}

	
}
*/