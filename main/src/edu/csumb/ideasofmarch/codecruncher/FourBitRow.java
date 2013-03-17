package edu.csumb.ideasofmarch.codecruncher;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
public class FourBitRow extends BinaryToDecimal implements GameRow {
	
	static int rowCount = 0;
	private LinearLayout pll,ll;
	private int solution;
	private TextView question;
	
	public FourBitRow(LinearLayout aLayout, Context aContext) {
		
		question = new TextView(aContext);
		ToggleButton t1 = new ToggleButton(aContext);
		/*ToggleButton t2 = new ToggleButton(this);
		ToggleButton t3 = new ToggleButton(this);
		ToggleButton t4 = new ToggleButton(this);*/
		
		ll = new LinearLayout(aContext);
		ll.addView(t1);
/*		ll.addView(t2); 
		ll.addView(t3); 
		ll.addView(t4);*/
		question.setText("test");
		ll.addView(question);
		aLayout.addView(ll);
	}

	@Override
	public boolean putNewRow() {
		
		if(rowCount < 8){
			
			rowCount++;
			return true;
			
		}else{
			
			return false;
		}
	}

	@Override
	public boolean checkProblem() {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRow() {
		if(rowCount > 1){
			
			
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
