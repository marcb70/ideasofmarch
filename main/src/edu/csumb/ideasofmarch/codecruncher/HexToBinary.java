package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hex_to_binary);
	    
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
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			
			if(hexInput.getValue() == solution) {
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
}
