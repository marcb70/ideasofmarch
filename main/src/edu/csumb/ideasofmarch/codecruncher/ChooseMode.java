package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseMode extends Activity {
	Button binaryToDecimal;
	Button binaryToDecimalHard;
	Button decimalToBinary;
	Button hexToDecimal;
	Button decimalToHex;
	Button binaryToHex;
	Button binaryToHexHard;
	Button hexToBinary;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.choose_mode);
	    
	    binaryToDecimal = (Button) findViewById(R.id.BinaryToDecimal);
	    binaryToDecimal.setOnClickListener(new BinaryToDecimalButtonListener());
	    binaryToDecimalHard = (Button) findViewById(R.id.BinaryToDecimalHard);
	    binaryToDecimalHard.setOnClickListener(new BinaryToDecimalHardButtonListener());
	    
	   /* hexToDecimal = (Button) findViewById(R.id.HexToDecimal);
	    hexToDecimal.setOnClickListener(new HexToDecimalButtonListener());
	    
	    decimalToBinary = (Button) findViewById(R.id.DecimalToBinary);
	    decimalToBinary.setOnClickListener(new DecimalToBinaryButtonListener());
	    
	    decimalToHex = (Button) findViewById(R.id.DecimalToHex);
	    decimalToHex.setOnClickListener(new DecimalToHexButtonListener());*/
	    
	    binaryToHex = (Button) findViewById(R.id.BinaryToHex);
	    binaryToHex.setOnClickListener(new BinaryToHexButtonListener());
	    binaryToHexHard = (Button) findViewById(R.id.BinaryToHexHard);
	    binaryToHexHard.setOnClickListener(new BinaryToHexHardButtonListener());
	    
	   /* hexToBinary = (Button) findViewById(R.id.HexToBinary);
	    hexToBinary.setOnClickListener(new HexToBinaryButtonListener()); */
	}
	
	private class BinaryToDecimalButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToDecimal();
		}
    }
	
	public void startNewBinaryToDecimal() {
    	startActivity(new Intent(this, BinaryToDecimal.class));
    }
	
	private class BinaryToDecimalHardButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToDecimalHard();
		}
    }
    
    public void startNewBinaryToDecimalHard() {
    	startActivity(new Intent(this, BinaryToDecimalHard.class));
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
    
    private class BinaryToHexButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToHex();
		}
    }
    
    public void startNewBinaryToHex() {
    	startActivity(new Intent(this, BinaryToHex.class));
    }
    
    private class BinaryToHexHardButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewBinaryToHexHard();
		}
    }
    
    public void startNewBinaryToHexHard() {
    	startActivity(new Intent(this, BinaryToHexHard.class));
    }
    
    private class HexToBinaryButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewHexToBinary();
		}
    }
    
    public void startNewHexToBinary() {
    	startActivity(new Intent(this, HexToBinary.class));
    }
}
