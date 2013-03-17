package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BinaryToHex extends Activity {
	
	public static final int numDigits = 4;
	
	private ToggleButton digits[];
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private String binaryInput;
	private Button submitButton;
	private TextView hexSolution;
	private TextView clock;
	private int solution;
	private int score = 0;
	private FourBitHexRow fbr;
	private ArrayList <FourBitHexRow> rowArray = new ArrayList<FourBitHexRow>();
	private LinearLayout aLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.binary_to_hex);
	    aLayout = (LinearLayout) findViewById(R.id.mainLayout);
	    fbr = new FourBitHexRow(aLayout, getBaseContext());
	    
	    gameClock = new CountDownTimer(60000,1000){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				clock.setText("Game Over " + score + " points");
			}
			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Binary to Hex: " + millisUntilFinished / 1000);				
			}    	
	    };
	    
	    moreTimer = new CountDownTimer(60000, 5000){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub				
			}
			@Override
			public void onTick(long millisUntilFinished) {
				fbr = new FourBitHexRow(aLayout, getBaseContext());
				fbr.putNewRow();
				
				rowArray.add(fbr);			
			}	    	
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    
	    submitButton = (Button) findViewById(R.id.submitButton);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	    gameClock.start();
	    moreTimer.start();
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			if(rowArray.size() != 0){
				for (int i = 0; i < rowArray.size();i++){
					if(rowArray.get(i).checkProblem()){
						score += 10;
						rowArray.remove(i);
					}
				}
			}
			if(rowArray.size() == 0){
				fbr = new FourBitHexRow(aLayout, getBaseContext());
				fbr.putNewRow();
				
				rowArray.add(fbr);
			}
		}
    }
   
    public String getGuess() {
    	String binaryInput = "";
    	for(int i = numDigits-1; i >= 0; i--) {
    		if(digits[i].isChecked()) {
    			binaryInput += "1";
    		}
    		else {
    			binaryInput += "0";
    		}
    	}
    	return binaryInput;
    }
    
    public int convertBinarytoDecimal(String binaryInput) {
    	return Integer.parseInt(binaryInput, 2);
    }
    
    public int newSolution() {
    	return (int) Math.floor(Math.random()*16);
    }
    
    public void increaseScore(int points){
    	score += points;
    }
}