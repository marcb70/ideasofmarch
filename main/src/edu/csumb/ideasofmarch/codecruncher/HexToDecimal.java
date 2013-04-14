package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.NumberPicker;

public class HexToDecimal extends Activity {

	private NumberPicker hexInput;
	private TextView decimalSolution;
	private int solution;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hex_to_decimal);
	    
	    gameClock = new CountDownTimer(60000,1000){
			@Override
			public void onFinish() {
				Intent intent = new Intent(getBaseContext(), GameOver.class);
				intent.putExtra("GAME_TAG_CODE", CrunchConstants.HEX_TO_DECIMAL);
				intent.putExtra("GAME_SCORE", score);
				startActivity(intent);
				finish();
			}
			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Decimal to Binary : " + millisUntilFinished / 1000);				
			}    	
	    };
	    
	    moreTimer = new CountDownTimer(60000, 5000){
			@Override
			public void onFinish() {		
			}
			@Override
			public void onTick(long millisUntilFinished) {		
			}	    	
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    
	    
	    String [] hexValues = new String[16];
	    for (int i = 0; i < 10; i ++) {
	    	hexValues[i] = Integer.toString(i);
	    }
	    hexValues[10] = "A";
	    hexValues[11] = "B";
	    hexValues[12] = "C";
	    hexValues[13] = "D";
	    hexValues[14] = "E";
	    hexValues[15] = "F";
	    
	    hexInput = (NumberPicker) findViewById(R.id.hexInput);
	    hexInput.setMaxValue(15);
	    hexInput.setMinValue(0);
	    hexInput.setDisplayedValues(hexValues);
	    
	    decimalSolution = (TextView) findViewById(R.id.decimalSolution);

	    solution = newSolution();
	    
	    decimalSolution.setText("" + solution);
	    gameClock.start();
	    moreTimer.start();
	}

    @SuppressWarnings("unused")
	private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// Get the string in binary of the user's guess
			String hexGuess = Integer.toHexString(hexInput.getValue());
			
			if(hexGuess.equals(convertDecimaltoHex(solution))) {
				increaseScore(solution);
				decimalSolution.setTextColor(Color.GREEN);
				solution = newSolution();
				decimalSolution.setText("" + solution);
				decimalSolution.setTextColor(Color.BLACK);
			} else {
				decimalSolution.setTextColor(Color.RED);
			}
		}
    }
    
    public String convertDecimaltoHex(int decInput) {
    	return Integer.toHexString(decInput);
    }
    
    public int newSolution() {
    	return (int) Math.floor(Math.random()*16);
    }
    
    public void increaseScore(int points){
    	score += points;
    }
}
