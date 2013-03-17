package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.NumberPicker;

public class DecimalToBinary extends Activity {
	
	private NumberPicker decimalInput;
	private Button submitButton;
	private TextView binarySolution;
	private String solution;
	
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_decimal_to_binary);
	    
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
				Intent intent = new Intent(getBaseContext(), GameOver.class);
				intent.putExtra("GAME_TAG_CODE", CrunchConstants.DECIMAL_TO_BINARY);
				intent.putExtra("GAME_SCORE", score);
				startActivity(intent);		
				finish();
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
	    binarySolution = (TextView) findViewById(R.id.binarySolution);

	    solution = newSolution();
	    
	    binarySolution.setText(solution);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	    gameClock.start();
	  }

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// Get the string in binary of the user's guess
			String decimalGuess = getGuess();
			
			if(decimalGuess.equals(solution)) {
				increaseScore(decimalInput.getValue());
				binarySolution.setTextColor(Color.GREEN);
				solution = newSolution();
				binarySolution.setText(solution);
				binarySolution.setTextColor(Color.BLACK);
			    decimalInput.setValue(0);
			} else {
				binarySolution.setTextColor(Color.RED);
			}
		}
    }
   
    public String getGuess() {
    	String binaryInput = Integer.toBinaryString(decimalInput.getValue());
    	
    	return binaryInput;
    }
    
    public String newSolution() {
    	return Integer.toBinaryString((int) Math.floor(Math.random()*16));
    }
    
    public void increaseScore(int points){
    	score += points;
    }
}
