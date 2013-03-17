package edu.csumb.ideasofmarch.codecruncher;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class EightBitHexRow extends BinaryToHex implements GameRow {
	
	static int rowCount = 0;
	private LinearLayout pll,ll;
	private int randNum;
	private TextView question;
	private ToggleButton t1,t2,t3,t4,t5,t6,t7,t8;
	
	public EightBitHexRow(LinearLayout aLayout, Context aContext) {
		pll = aLayout;
		randNum = (int) Math.floor(Math.random()*256);	
		question = new TextView(aContext);
		t1 = new ToggleButton(aContext);
		t2 = new ToggleButton(aContext);
		t3 = new ToggleButton(aContext);
		t4 = new ToggleButton(aContext);
		t5 = new ToggleButton(aContext);
		t6 = new ToggleButton(aContext);
		t7 = new ToggleButton(aContext);
		t8 = new ToggleButton(aContext);
		
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
		
		t5.setBackgroundResource(resID);
		t5.setText(R.string.binaryInput); 
		t5.setTextOff("0");
		t5.setTextOn("1");
		t5.setTextColor(Color.WHITE);
		t5.setChecked(false);
		
		t6.setBackgroundResource(resID);
		t6.setText(R.string.binaryInput); 
		t6.setTextOff("0");
		t6.setTextOn("1");
		t6.setTextColor(Color.WHITE);
		t6.setChecked(false);
		
		t7.setBackgroundResource(resID);
		t7.setText(R.string.binaryInput); 
		t7.setTextOff("0");
		t7.setTextOn("1");
		t7.setTextColor(Color.WHITE);
		t7.setChecked(false);
		
		t8.setBackgroundResource(resID);
		t8.setText(R.string.binaryInput); 
		t8.setTextOff("0");
		t8.setTextOn("1");
		t8.setTextColor(Color.WHITE);
		t8.setChecked(false);
		
		ll = new LinearLayout(aContext);
		ll.addView(t1);
		ll.addView(t2); 
		ll.addView(t3); 
		ll.addView(t4);
		ll.addView(t5);
		ll.addView(t6);
		ll.addView(t7);
		ll.addView(t8);
		question.setText(Integer.toHexString(randNum));
		question.setBackgroundResource(R.drawable.textbox);
		question.setTextColor(Color.WHITE);
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
    	
    	if(t5.isChecked()){
    		binaryInput += "1";
    	}else{
    		binaryInput += "0";
    	}
    	
    	if(t6.isChecked()){
    		binaryInput += "1";
    	}else{
    		binaryInput += "0";
    	}
    	
    	if(t7.isChecked()){
    		binaryInput += "1";
    	}else{
    		binaryInput += "0";
    	}
    	
    	if(t8.isChecked()){
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
			randNum = (int) Math.floor(Math.random()*256);
			question.setText("" + randNum);
			return false;
		}
	}
	
	public int score(){
		return randNum;
	}

	public void resetRowCount(){
		rowCount = 0;
	}
}
