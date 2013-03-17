package edu.csumb.ideasofmarch.codecruncher;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
public class FourBitHexRow extends BinaryToHex implements GameRow {
	
	static int rowCount = 0;
	private LinearLayout pll,ll;
	private int solution, randNum;
	private TextView question;
	private ToggleButton t1,t2,t3,t4;
	public FourBitHexRow(LinearLayout aLayout, Context aContext) {
		pll = aLayout;
		randNum = (int) Math.floor(Math.random()*16);	
		question = new TextView(aContext);
		t1 = new ToggleButton(aContext);
		t2 = new ToggleButton(aContext);
		t3 = new ToggleButton(aContext);
		t4 = new ToggleButton(aContext);
		
		int resID = (int) Math.floor(Math.random()*3);
		if(resID == 0) {
			resID = R.drawable.redbutton;
		} else if(resID == 1) {
			resID = R.drawable.yellowbutton;
		}else {
			resID = R.drawable.bluebutton;
		}
		
		t1.setBackgroundResource(resID);
		t1.setText(R.string.binaryInput); 
		t1.setTextOff("0");
		t1.setTextOn("1");
		t1.setTextColor(Color.WHITE);
		t1.setChecked(false);
		
		
		t2.setBackgroundResource(resID);
		t2.setText(R.string.binaryInput); 
		t2.setTextOff("0");
		t2.setTextOn("1");
		t2.setTextColor(Color.WHITE);
		t2.setChecked(false);
		
		t3.setBackgroundResource(resID);
		t3.setText(R.string.binaryInput); 
		t3.setTextOff("0");
		t3.setTextOn("1");
		t3.setTextColor(Color.WHITE);
		t3.setChecked(false);
		
		t4.setBackgroundResource(resID);
		t4.setText(R.string.binaryInput); 
		t4.setTextOff("0");
		t4.setTextOn("1");
		t4.setTextColor(Color.WHITE);
		t4.setChecked(false);
		
		ll = new LinearLayout(aContext);
		ll.addView(t1);
		ll.addView(t2); 
		ll.addView(t3); 
		ll.addView(t4);
		question.setText(Integer.toHexString(randNum));
		question.setTextColor(Color.BLACK);
		ll.addView(question);
		
	}

	@Override
	public boolean putNewRow() {
		
		if(rowCount < 8){
			
			rowCount++;
			pll.addView(ll);
			return true;
			
		}else{
			
			return false;
		}
	}

	@Override
	public boolean checkProblem() {
		String answer = getGuess();
		int intAnswer = Integer.parseInt(answer, 2);
		
		if(intAnswer == randNum){
			//add sound here
			removeRow();
			return true;
		}else{
			
			question.setTextColor(Color.RED);
			return false;
		}
	}
		
	
    public String getGuess() {
    	String binaryInput = "";
    	
    	if(t1.isChecked()){
    		
    		binaryInput += "1";
    		
    	}else{
    		
    		binaryInput += "0";
    	}
    	
    	if(t2.isChecked()){
    		
    		binaryInput += "1";
    		
    	}else{
    		
    		binaryInput += "0";
    	}
    	
    	if(t3.isChecked()){
    		
    		binaryInput += "1";
    		
    	}else{
    		
    		binaryInput += "0";
    	}
    	
    	if(t4.isChecked()){
    		
    		binaryInput += "1";
    		
    	}else{
    		
    		binaryInput += "0";
    	}
    	
    	return binaryInput;
    }

	@Override
	public boolean removeRow() {
		if(rowCount > 0){
			ll.setVisibility(View.GONE);
			((ViewGroup) ll.getParent()).removeView(ll);
			rowCount--;
			return true;
		}else{
			randNum = (int) Math.floor(Math.random()*16);
			question.setText("" + randNum);
		return false;
		}
	}
	
	public int score(){
		return randNum;
	}

}