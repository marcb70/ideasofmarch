package edu.csumb.ideasofmarch.codecruncher;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HexToDecimal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hex_to_decimal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hex_to_decimal, menu);
		return true;
	}

}
