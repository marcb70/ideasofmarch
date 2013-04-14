package edu.csumb.ideasofmarch.codecruncher;

public abstract interface GameRow {
	
	public abstract boolean putNewRow();
	
	public abstract boolean checkProblem();
		
	public abstract boolean removeRow();
}
