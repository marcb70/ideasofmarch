package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.NumberPicker;

public class HexToDecimal extends Activity {

	private NumberPicker hexInput;
	private Button submitButton;
	private TextView decimalSolution;
	private int solution;
	private String [] hexValues = new String[16];
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hex_to_decimal);
	    
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
	    
	    decimalSolution = (TextView) findViewById(R.id.decimalSolution);

	    solution = newSolution();
	    
	    decimalSolution.setText("" + solution);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// Get the string in binary of the user's guess
			String hexGuess = Integer.toHexString(hexInput.getValue());
			
			if(hexGuess.equals(convertDecimaltoHex(solution))) {
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
}
