package solver;
import java.util.SortedSet;

import java.util.TreeSet;

import model.SCPModel;
import util.ElementSet;

/** This is the main method that all solvers inherit from.  It is important
 *  to note how this solver calls nextBestSet() polymorphically!  Subclasses
 *  should *not* override solver(), they need only override nextBestSet().
 * 
 *  We'll assume models are well-defined here and do not specify Exceptions
 *  to throw.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public abstract class GreedySolver {
	
	protected String _name;			  // name of algorithm type
	protected double _alpha;          // minimum required coverage level in range [0,1]
	protected SCPModel _model;        // the SCP model we're currently operating on
	protected double _objFn;          // objective function value (*total cost sum* of all sets used)
	protected double _coverage;       // actual coverage fraction achieved
	protected long _compTime;         // computation time (ms)
	protected TreeSet<ElementSet> _solnSets; 
	protected TreeSet<ElementSet> _unusedSets;
	protected TreeSet<Integer> _elementsToCover;
	
	// Basic setter (only one needed)
	public void setMinCoverage(double alpha) { _alpha = alpha; }
	public void setModel(SCPModel model) { _model = model; }
	//public void setUnusedSets() {
	//	_unusedSets = new TreeSet<ElementSet>(_model.getModel());
	//}
	
	// Basic getters
	public double getMinCoverage() { return _alpha; }
	public double getObjFn() { return _objFn; }
	public double getCoverage() { return _coverage; }
	public long getCompTime() { return _compTime; }
	public String getName() { return _name; } //algorithm type name 
		
	// TODO: Add any helper methods you need
	
//	public void setName() {
//		if (this instanceof GreedyCostSolver) {
//			_name = "Cost";
//		}
//		else if(this instanceof GreedyCoverageSolver){
//			_name = "Coverage";
//		}
//		else if(this instanceof ChvatalSolver) {
//			_name = "Chvatal";
//		}
//		//how do we find out what object is calling greedy solver? Which type of solver? 
//	}
	
	//probably need reset method 
	
	
	public void reset() {
		_coverage = 0;
		_compTime = 0;
		_objFn = 0;
		//_solnSets = null;
		//_unusedSets = null;
		//this should reset stuff but what stuff and how and why -- especially if I initialize everything after 
	}
	
	
	/** Run the simple greedy heuristic -- add the next best set until either
	 *  (1) The coverage level is reached, or 
	 *  (2) There is no set that can increase the coverage.
	 */
	public void solve() {
		
		// Reset the solver
				reset();
				
				
				// TODO: Preliminary initializations
				
				_elementsToCover = new TreeSet<Integer>(_model.getUniverse());
				_solnSets = new TreeSet<ElementSet>();
				_unusedSets = new TreeSet<ElementSet>(_model.getModel());
			
				// Begin the greedy selection loop
				long start = System.currentTimeMillis();
				System.out.println("Running '" + getName() + "'...");

				//
				// NOTE: In order to match the solution, pay attention to the following
				//       calculations (where you have to replace ALL-CAPS parts)
				//
				 int num_to_cover = (int)Math.ceil(_alpha * _model.getUniverse().size());
				 int num_can_leave_uncovered = _model.getUniverse().size() - num_to_cover;
				
				
				ElementSet selected;
				while (_elementsToCover.size() > num_can_leave_uncovered && !_unusedSets.isEmpty()) {
					
				
					selected = nextBestSet();
					if(selected != null) 
					System.out.print("- Selected: " + selected);
					_objFn += selected.getCost();
					_solnSets.add(selected); //adds selected set to _solnSets
					_unusedSets.remove(selected); //removes selected set from unused sets 
					_elementsToCover.removeAll(selected.getElements()); //removes covered elements 
				
				}
				 
				 
				 //System.out.println("this is the size of the original model" +_model.getModel().size());
				 // Record final set coverage, compTime and print warning if applicable
				_coverage = 1- ((double)_elementsToCover.size() / (double)_model.getUniverse().size()); // TODO: Correct this, should be coverage of solution found
				//System.out.println("this is the coverage" + _coverage);
				_compTime = System.currentTimeMillis() - start;
				if (_coverage < _alpha)
					System.out.format("\nWARNING: Impossible to reach %.2f%% coverage level.\n", 100*_alpha);
				System.out.println("Done.");
	}
	
	/** Returns the next best set to add to the solution according to the heuristic being used.
	 * 
	 *  NOTE 1: This is the **only** method to be implemented in child classes.
	 *  
	 *  NOTE 2: If no set can improve the solution, returns null to allow the greedy algorithm to terminate.
	 *  
	 *  NOTE 3: This references an ElementSet class which is a tuple of (Set ID, Cost, Integer elements to cover)
	 *          which you must define.
	 * 
	 * @return
	 */
	public abstract ElementSet nextBestSet(); // Abstract b/c it must be implemented by subclasses
	
	/** Print the solution
	 * 
	 */
	public void print() {
		System.out.println("\n'" + getName() + "' results:");
		System.out.format("'" + getName() + "'   Time to solve: %dms\n", _compTime);
		System.out.format("'" + getName() + "'   Objective function value: %.2f\n", _objFn);
		System.out.format("'" + getName() + "'   Coverage level: %.2f%% (%.2f%% minimum)\n", 100*_coverage, 100*_alpha);
		System.out.format("'" + getName() + "'   Number of sets selected: %d\n", _solnSets.size());
		System.out.format("'" + getName() + "'   Sets selected: ");
		for (ElementSet s : _solnSets) {
			System.out.print(s.getId() + " "); //i made this, not sure if getID shoudl return int or string 
		}
		System.out.println("\n");
	}
	
	/** Print the solution performance metrics as a row
	 * 
	 */
	public void printRowMetrics() {
		System.out.format("%-25s%12d%15.4f%17.2f\n", getName(), _compTime, _objFn, 100*_coverage);
	}	
}
