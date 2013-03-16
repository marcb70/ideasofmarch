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

public class HexToBinary extends Activity {

	private NumberPicker hexInput;
	private Button submitButton;
	private TextView binarySolution;
	private int solution;
	private String [] hexValues = new String[16];
	
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hex_to_binary);
	    
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
	    
	    binarySolution = (TextView) findViewById(R.id.binarySolution);

	    solution = newSolution();
	    
	    binarySolution.setText(Integer.toBinaryString(solution));
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	    gameClock.start();
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			
			if(hexInput.getValue() == solution) {
				increaseScore(solution);
				binarySolution.setTextColor(Color.GREEN);
				solution = newSolution();
				binarySolution.setText(Integer.toBinaryString(solution));
				binarySolution.setTextColor(Color.BLACK);
			} else {
				binarySolution.setTextColor(Color.RED);
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
