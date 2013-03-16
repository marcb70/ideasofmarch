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
	Button hexToDecimal;
	Button decimalToHex;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.choose_mode);
	    
	    binaryToDecimal = (Button) findViewById(R.id.BinaryToDecimal);
	    binaryToDecimal.setOnClickListener(new BinaryToDecimalButtonListener());
	    
	    hexToDecimal = (Button) findViewById(R.id.HexToDecimal);
	    hexToDecimal.setOnClickListener(new HexToDecimalButtonListener());
	    
	    decimalToBinary = (Button) findViewById(R.id.DecimalToBinary);
	    decimalToBinary.setOnClickListener(new DecimalToBinaryButtonListener());
	    
	    decimalToHex = (Button) findViewById(R.id.DecimalToHex);
	    decimalToHex.setOnClickListener(new DecimalToHexButtonListener());
	  }
	
	private class BinaryToDecimalButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToDecimal();
		}
    }
    
    public void startNewBinaryToDecimal() {
    	startActivity(new Intent(this, BinaryToDecimal.class));
    }
    
    private class HexToDecimalButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewHexToDecimal();
		}
    }
    
    public void startNewHexToDecimal() {
    	startActivity(new Intent(this, HexToDecimal.class));
    }
    
    private class DecimalToBinaryButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewDecimalToBinary();
		}
    }
    
    public void startNewDecimalToBinary() {
    	startActivity(new Intent(this, DecimalToBinary.class));
    }
    
    private class DecimalToHexButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewDecimalToHex();
		}
    }
    
    public void startNewDecimalToHex() {
    	startActivity(new Intent(this, DecimalToHex.class));
    }
}
