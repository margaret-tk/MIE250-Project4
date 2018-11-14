package solver;

import java.io.IOException;
import java.util.TreeSet;

import model.SCPModel;
import util.ElementSet;

public class ChooseFirstUnselectedSet extends GreedySolver {
	
	@Override
	public ElementSet nextBestSet() {
		ElementSet selected = new ElementSet();
		System.out.println("this is the model" + _unusedSets);
		selected = _unusedSets.first();
		System.out.println("this is the next best set:" + selected);
		return selected;
}
}

