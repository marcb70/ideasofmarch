package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.NumberPicker;

public class DecimalToBinary extends Activity {
	
	private NumberPicker decimalInput;
	private Button submitButton;
	private TextView binarySolution;
	private String solution;
	
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_decimal_to_binary);
	    
	    submitButton = (Button) findViewById(R.id.submitButton);
	    decimalInput = (NumberPicker) findViewById(R.id.decimalInput);
	    decimalInput.setMaxValue(16);
	    decimalInput.setMinValue(0);
	    binarySolution = (TextView) findViewById(R.id.binarySolution);

	    solution = Integer.toBinaryString((int) Math.floor(Math.random()*16));
	    
	    binarySolution.setText(solution);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	  }

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// Get the string in binary of the user's guess
			String decimalGuess = getGuess();
			
			if(decimalGuess.equals(solution)) {
				binarySolution.setTextColor(Color.GREEN);
				solution = Integer.toBinaryString((int) Math.floor(Math.random()*16));
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
}
