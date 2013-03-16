package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BinaryToHex extends Activity {
	
	public static final int numDigits = 4;
	
	private ToggleButton digits[];
	private String binaryInput;
	private Button submitButton;
	private TextView hexSolution;
	private int solution;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.binary_to_hex);
	    
	    submitButton = (Button) findViewById(R.id.submitButton);
	    hexSolution = (TextView) findViewById(R.id.hexSolution);
	    digits = new ToggleButton[numDigits];

	    digits[0] = (ToggleButton) findViewById(R.id.digit1);
	    digits[1] = (ToggleButton) findViewById(R.id.digit2);
	    digits[2] = (ToggleButton) findViewById(R.id.digit3);
	    digits[3] = (ToggleButton) findViewById(R.id.digit4);
	    
	    solution = newSolution();
	    hexSolution.setText(Integer.toHexString(solution));
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	  }

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			binaryInput = getGuess();
			int decimalGuess = convertBinarytoDecimal(binaryInput);
			if(decimalGuess == solution) {
				hexSolution.setTextColor(Color.GREEN);
				solution = newSolution();
			    hexSolution.setText(Integer.toHexString(solution));
			    hexSolution.setTextColor(Color.BLACK);
			    digits[0].setChecked(false);
			    digits[1].setChecked(false);
			    digits[2].setChecked(false);
			    digits[3].setChecked(false);
			} else {
				hexSolution.setTextColor(Color.RED);
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
}