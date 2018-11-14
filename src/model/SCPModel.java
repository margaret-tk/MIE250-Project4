package model;

import java.util.*;
import java.util.List;


import util.ElementSet;

public class SCPModel {
	
	private TreeSet<ElementSet> _model;

	
	public SCPModel() {
	_model = new TreeSet<ElementSet>(); 	
	}
	
	
	
	public TreeSet<ElementSet> getModel(){
		return _model;
	}
		
	public TreeSet<Integer> getUniverse(){
		TreeSet<Integer> elements = new TreeSet<Integer>();
		for(ElementSet each: _model) {
			elements.addAll(each.getElements());
		}
		return elements;
	}
	
	public int numSets() {
		return _model.size();
	}
	
	/**Add  
	 * 
	 * @param list 
	 * @param d 
	 * @param i 
	 * @return
	 */
	public void addSetToCover(int i, double d, List<Integer> list) {
		//ElementSet e = new ElementSet(i,d,list);
		_model.add(new ElementSet(i,d,list));
	}
	
	public void addSet(ElementSet set) {
		_model.add(set);
	}
	
	@Override
	public String toString() { //double check that this is identical 
		StringBuilder sb = new StringBuilder();
		sb.append("\nWeighted SCP model:\n---------------------\n");
		sb.append(String.format("Number of elements (n): %d\n", getUniverse().size()));
		sb.append(String.format("Number of sets (m): %d\n\n", _model.size()));
		sb.append("Set details:\r\n" + "----------------------------------------------------------\n");
		for(ElementSet each: _model) {sb.append(each);}
		return sb.toString();
		
		
		
		
	}
	
	
	
	
	
}
