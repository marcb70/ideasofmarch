package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BinaryToDecimal extends Activity {
	
	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private ToggleButton digits1[];
	private String binaryInput;
	private Button submitButton;
	private TextView decimalSolution1;
	private TextView clock;
	private int solution1;
	private int score = 0;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.binary_to_decimal);
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
	    clock = (TextView) findViewById(R.id.title);
	    submitButton = (Button) findViewById(R.id.submitButton);
	    decimalSolution1 = (TextView) findViewById(R.id.decimalSolution1);
	    digits1 = new ToggleButton[numDigits];

	    digits1[0] = (ToggleButton) findViewById(R.id.digit1);
	    digits1[1] = (ToggleButton) findViewById(R.id.digit2);
	    digits1[2] = (ToggleButton) findViewById(R.id.digit3);
	    digits1[3] = (ToggleButton) findViewById(R.id.digit4);
	    
	    solution1 = (int) Math.floor(Math.random()*16);
	    decimalSolution1.setText("" + solution1);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	    gameClock.start();
	  }

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			binaryInput = getGuess();
			int decimalGuess = convertBinarytoDecimal(binaryInput);
			if(decimalGuess == solution1) {
				decimalSolution1.setTextColor(Color.GREEN);
				solution1 = (int) Math.floor(Math.random()*16);
			    decimalSolution1.setText("" + solution1);
			    decimalSolution1.setTextColor(Color.BLACK);
			    digits1[0].setChecked(false);
			    digits1[1].setChecked(false);
			    digits1[2].setChecked(false);
			    digits1[3].setChecked(false);
			    score += decimalGuess;
			} else {
				decimalSolution1.setTextColor(Color.RED);
			}
		}
    }
   
    public String getGuess() {
    	String binaryInput = "";
    	
    	for(int i = numDigits-1; i >= 0; i--) {
    		if(digits1[i].isChecked()) {
    			binaryInput += "1";
    		} else {
    			binaryInput += "0";
    		}
    			
    	}
    	
    	return binaryInput;
    }
    
    public int convertBinarytoDecimal(String binaryInput) {
    	return Integer.parseInt(binaryInput, 2);
    }
}
