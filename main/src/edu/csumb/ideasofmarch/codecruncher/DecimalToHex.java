package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.NumberPicker;

public class DecimalToHex extends Activity {
	
	private NumberPicker decimalInput;
	private Button submitButton;
	private TextView hexSolution;
	private String solution;
	
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.decimal_to_hex);
	    
	    gameClock = new CountDownTimer(60000,1000){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				clock.setText("Game Over " + score + " points");
			}
			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Decimal to Binary : " + millisUntilFinished / 1000);				
			}    	
	    };
	    
	    moreTimer = new CountDownTimer(60000, 5000){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub				
			}
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub				
			}	    	
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    
	    submitButton = (Button) findViewById(R.id.submitButton);
	    decimalInput = (NumberPicker) findViewById(R.id.decimalInput);
	    decimalInput.setMaxValue(15);
	    decimalInput.setMinValue(0);
	    hexSolution = (TextView) findViewById(R.id.hexSolution);

	    solution = newSolution();
	    
	    hexSolution.setText(solution);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	    gameClock.start();
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// Get the string in binary of the user's guess
			String decimalGuess = getGuess();
			
			if(decimalGuess.equals(solution)) {
				increaseScore(decimalInput.getValue());
				hexSolution.setTextColor(Color.GREEN);
				solution = newSolution();
				hexSolution.setText(solution);
				hexSolution.setTextColor(Color.BLACK);
			    decimalInput.setValue(0);
			} else {
				hexSolution.setTextColor(Color.RED);
			}
		}
    }
   
    public String getGuess() {
    	String binaryInput = Integer.toHexString(decimalInput.getValue());
    	return binaryInput;
    }
    
    public String newSolution(){
    	return Integer.toHexString((int) Math.floor(Math.random()*16));
    }
    
    public void increaseScore(int points){
    	score += points;
    }
}
