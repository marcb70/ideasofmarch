package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BinaryToHex extends Activity {
	
	public static final int numDigits = 4;
	private BinaryToHex instance = null;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	private BinaryRow fbr;
	private ArrayList <BinaryRow> rowArray = new ArrayList<BinaryRow>();
	private LinearLayout aLayout;
	private SoundHelper soundHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.binary_to_hex);
	    aLayout = (LinearLayout) findViewById(R.id.mainLayout);
	    instance = this;
	    fbr = new BinaryRow(aLayout, instance, 4, 2); // Final int is answer type: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal
	    soundHelper = new SoundHelper(instance);
	    soundHelper.loadDing();
	    
	    gameClock = new CountDownTimer(60000,1000){
			@Override
			public void onFinish() {
				fbr.resetRowCount();
				Intent intent = new Intent(instance, GameOver.class);
				intent.putExtra("GAME_TAG_CODE", CrunchConstants.BINARY_TO_HEX);
				intent.putExtra("GAME_SCORE", score);
				startActivity(intent); 
				finish();
			}
			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Binary to Hex: " + millisUntilFinished / 1000);				
			}    	
	    };
	    
	    moreTimer = new CountDownTimer(60000, 5000){
			@Override
			public void onFinish() {
							
			}
			
			@Override
			public void onTick(long millisUntilFinished) {
				fbr = new BinaryRow(aLayout, instance, 4, 2); // Final int is answer type: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal
				fbr.putNewRow();
				
				rowArray.add(fbr);			
			}	    	
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    gameClock.start();
	    moreTimer.start();
	}

	public void correctAnswer(BinaryRow hr) {
		score += 5;
		
		if (rowArray.contains(hr)) {
			rowArray.remove(hr);
			if (rowArray.size() == 0) {
				fbr = new BinaryRow(aLayout, instance, 4, 2); // Final int is answer type: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal
				fbr.putNewRow();
				rowArray.add(fbr);
			}
		}
		soundHelper.playDing();
	}
}