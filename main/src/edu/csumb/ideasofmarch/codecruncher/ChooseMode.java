package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseMode extends Activity {
	Button binaryToDecimal;
	Button decimalToBinary;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.choose_mode);
	    
	    binaryToDecimal = (Button) findViewById(R.id.BinaryToDecimal);
	    binaryToDecimal.setOnClickListener(new BinaryToDecimalButtonListener());
	    
	    decimalToBinary = (Button) findViewById(R.id.DecimalToBinary);
	    decimalToBinary.setOnClickListener(new DecimalToBinaryButtonListener());
	  }
	
	private class BinaryToDecimalButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToDecimal();
		}
    }
    
    public void startNewBinaryToDecimal() {
    	startActivity(new Intent(this, BinaryToDecimal.class));
    }
    
    private class DecimalToBinaryButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToDecimal();
		}
    }
    
    public void startNewDecimalToBinary() {
    	startActivity(new Intent(this, DecimalToBinary.class));
    }
}
