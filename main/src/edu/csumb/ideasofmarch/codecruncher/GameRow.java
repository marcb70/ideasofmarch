package edu.csumb.ideasofmarch.codecruncher;

import android.widget.LinearLayout;
import android.widget.TextView;

public abstract interface GameRow {
	
	public abstract boolean putNewRow();
	
	public abstract boolean checkProblem();
		
	public abstract boolean removeRow();
}
