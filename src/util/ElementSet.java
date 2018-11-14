package util;

import java.util.List;
import java.util.TreeSet;

public class ElementSet implements Comparable {

	private int _ID;
	private double _cost;
	private TreeSet<Integer> _elements; 
	
	
	public ElementSet() {
		_elements = new TreeSet<Integer>();
	}
	
	public ElementSet(int ID, double cost, List<Integer> elements) {
		_ID = ID;
		_cost = cost;
		_elements = new TreeSet<Integer>(elements);
	}
	
	
	public TreeSet<Integer> getElements(){
		return _elements;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ElementSet)) return false;
		ElementSet e = (ElementSet)o;
		return this._ID == e._ID && this._cost == e._cost && this._elements.equals(e._elements);
	}

	
	@Override
	public int compareTo(Object o) {
		if (!(o instanceof ElementSet)) return 0; //zero if not same object 
		ElementSet e = (ElementSet)o;
		if(this._ID == e._ID) return 0; //zero if equal 
		else return (this._ID - e._ID); //difference in ID if not equal 
	}
	
	
	@Override
	public String toString() {  
	StringBuilder sb = new StringBuilder();
	sb.append(String.format("Set ID: %3d   Cost: %6.2f   Element IDs: %s\n", _ID, _cost, _elements));
	return sb.toString();
	}


	public int getId() {
		return _ID;
	}
	
	public double getCost() {
		return _cost;
	}
}


