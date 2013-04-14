package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BinaryToHex extends Activity {
	
	public static final int numDigits = 4;
	private BinaryToHex instance = null;
	private ToggleButton digits[];
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private String binaryInput;
	private Button submitButton;
	private TextView hexSolution;
	private TextView clock;
	private int solution;
	private int score = 0;
	private HexRow fbr;
	private ArrayList <HexRow> rowArray = new ArrayList<HexRow>();
	private LinearLayout aLayout;
	private Context context;
	
	private SoundPool soundPool;
	private int clapSound, dingSound;
	boolean loaded = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.binary_to_hex);
	    context = this.getApplicationContext();
	    aLayout = (LinearLayout) findViewById(R.id.mainLayout);
	    instance = this;
	    fbr = new HexRow(aLayout, instance, 4);
	    
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
	    
		clapSound = soundPool.load(this, R.raw.clap, 1);
		dingSound = soundPool.load(this, R.raw.ding, 1);
		
	    gameClock = new CountDownTimer(60000,1000){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub				
			}
			@Override
			public void onTick(long millisUntilFinished) {
				fbr = new HexRow(aLayout, instance, 4);
				fbr.putNewRow();
				
				rowArray.add(fbr);			
			}	    	
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    gameClock.start();
	    moreTimer.start();
	}

	public void correctAnswer(HexRow hr) {
		score += 5;
		
		if (rowArray.contains(hr)) {
			rowArray.remove(hr);
			if (rowArray.size() == 0) {
				fbr = new HexRow(aLayout, instance, 4);
				fbr.putNewRow();
				rowArray.add(fbr);
			}
		}
		
		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		float actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		// Is the sound loaded already?
		
		if (loaded) {
			soundPool.play(dingSound, volume, volume, 1, 0, 1f);
		}
	}
}