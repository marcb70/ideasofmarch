package edu.csumb.ideasofmarch.codecruncher;

import android.content.Context;
import android.graphics.Color;
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
		ToggleButton t2 = new ToggleButton(aContext);
		ToggleButton t3 = new ToggleButton(aContext);
		ToggleButton t4 = new ToggleButton(aContext);
		
		t1.setBackgroundResource(R.drawable.bluebutton);
		t1.setText(R.string.binaryInput); 
		t1.setTextOff("0");
		t1.setTextOn("1");
		t1.setTextColor(Color.BLACK);
		t1.setChecked(false);
		
		
		t2.setBackgroundResource(R.drawable.bluebutton);
		t2.setText(R.string.binaryInput); 
		t2.setTextOff("0");
		t2.setTextOn("1");
		t2.setTextColor(Color.BLACK);
		t2.setChecked(false);
		
		t3.setBackgroundResource(R.drawable.bluebutton);
		t3.setText(R.string.binaryInput); 
		t3.setTextOff("0");
		t3.setTextOn("1");
		t3.setTextColor(Color.BLACK);
		t3.setChecked(false);
		
		t4.setBackgroundResource(R.drawable.bluebutton);
		t4.setText(R.string.binaryInput); 
		t4.setTextOff("0");
		t4.setTextOn("1");
		t4.setTextColor(Color.BLACK);
		t4.setChecked(false);
/*		 android:layout_width="wrap_content"
	                android:layout_height="wrap_content"
	                android:background="@drawable/bluetoggle"
	                android:text="@string/binaryInput"
	                android:textOff="0"
	                android:textOn="1"*/
		//LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, LayoutParams.);
		ll = new LinearLayout(aContext);
		ll.addView(t1);
		ll.addView(t2); 
		ll.addView(t3); 
		ll.addView(t4);
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
