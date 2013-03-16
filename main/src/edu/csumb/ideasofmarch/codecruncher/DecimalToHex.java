package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.decimal_to_hex);
	    
	    submitButton = (Button) findViewById(R.id.submitButton);
	    decimalInput = (NumberPicker) findViewById(R.id.decimalInput);
	    decimalInput.setMaxValue(15);
	    decimalInput.setMinValue(0);
	    hexSolution = (TextView) findViewById(R.id.hexSolution);

	    solution = newSolution();
	    
	    hexSolution.setText(solution);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// Get the string in binary of the user's guess
			String decimalGuess = getGuess();
			
			if(decimalGuess.equals(solution)) {
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
    
}
