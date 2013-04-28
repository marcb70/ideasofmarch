package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BinaryToDecimalHard extends Activity {
	private BinaryToDecimalHard instance = null;
	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	private BinaryRow ebr;
	private ArrayList <BinaryRow> rowArray = new ArrayList<BinaryRow>();
	private LinearLayout aLayout;
	private SoundHelper soundHelper;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.binary_to_decimal);
	    aLayout = (LinearLayout) findViewById(R.id.mainLayout);
	    instance = this;
	    ebr = new BinaryRow(aLayout, instance, 8, 0); // Final int is answer type: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal

	    this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});
		
		dingSound = soundPool.load(this, R.raw.ding, 1);
	 
	    ebr = new BinaryRow(aLayout, instance, 8, 0); // Final int is: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal
		soundHelper = new SoundHelper(instance);
		soundHelper.loadDing();
	    gameClock = new CountDownTimer(60000,1000){

			@Override
			public void onFinish() {
				ebr.resetRowCount();
				Intent intent = new Intent(instance, GameOver.class);
				intent.putExtra("GAME_TAG_CODE", CrunchConstants.BINARY_TO_DECIMAL_HARD);
				intent.putExtra("GAME_SCORE", score);
				startActivity(intent);   
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Decimal to Binary : " + millisUntilFinished / 1000);
				
			}
	    	
	    };
	    
	    moreTimer = new CountDownTimer(60000, 5000){

			@Override
			public void onFinish() {
				
			}

			@Override
			public void onTick(long millisUntilFinished) {
				ebr = new BinaryRow(aLayout, instance, 8, 0); // Final int is answer type: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal
				ebr.putNewRow();
				
				rowArray.add(ebr);
			}
	    	
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    gameClock.start();
	    moreTimer.start();
	    
	  }

	public void correctAnswer(BinaryRow br) {
		score += 5;
		
		if (rowArray.contains(br)) {
			rowArray.remove(br);
			if (rowArray.size() == 0) {
				ebr = new BinaryRow(aLayout, instance, 8, 0); // Final int is answer type: 0 - Decimal ; 1 - Binary ; 2 - Hexadecimal
				ebr.putNewRow();
				rowArray.add(ebr);
			}
		}
		
		soundHelper.playDing();
		
	}
}
