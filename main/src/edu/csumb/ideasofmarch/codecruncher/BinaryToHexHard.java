package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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


public class BinaryToHexHard extends Activity {
	private BinaryToHexHard instance = null;
	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private Button submitButton;
	private TextView hexSolution;
	private TextView clock;
	private int score = 0;
	private BinaryRow ebr;
	private ArrayList <BinaryRow> rowArray = new ArrayList<BinaryRow>();
	private LinearLayout aLayout;
	private Context context;
	
	private SoundPool soundPool;
	private int clapSound, dingSound;
	boolean loaded = false;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    context = this.getApplicationContext();
	    setContentView(R.layout.binary_to_decimal);
	    aLayout = (LinearLayout) findViewById(R.id.mainLayout);
	    instance = this;
	    ebr = new BinaryRow(aLayout, instance, 8);
	    
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
				ebr.resetRowCount();
				Intent intent = new Intent(instance, GameOver.class);
				intent.putExtra("GAME_TAG_CODE", CrunchConstants.BINARY_TO_HEX_HARD);
				intent.putExtra("GAME_SCORE", score);
				startActivity(intent); 
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Binary to Hex : " + millisUntilFinished / 1000);
			}
	    };
	    
	    moreTimer = new CountDownTimer(60000, 5000){
			@Override
			public void onFinish() {	}

			@Override
			public void onTick(long millisUntilFinished) {
				ebr = new BinaryRow(aLayout, instance, 8);
				ebr.putNewRow();
				
				rowArray.add(ebr);
			}
	    };
	    
	    clock = (TextView) findViewById(R.id.title);
	    submitButton = (Button) findViewById(R.id.submitButton);
	    hexSolution = (TextView) findViewById(R.id.hexSolution);
	    
	    submitButton.setOnClickListener(new SubmitButtonListener());
	    gameClock.start();
	    moreTimer.start();    
	}

    private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			if(rowArray.size() != 0){
				for (int i = 0; i < rowArray.size();i++){
					if(rowArray.get(i).checkProblem()){
						score += 25;
						rowArray.remove(i);
						
						AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
						float actualVolume = (float) audioManager
								.getStreamVolume(AudioManager.STREAM_MUSIC);
						float maxVolume = (float) audioManager
								.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
						float volume = actualVolume / maxVolume;
						// Is the sound loaded already?
						if (loaded) {
							soundPool.play(dingSound, volume, volume, 1, 0, 1f);
							Log.e("Test", "Played sound");
						}
						
					}
				}
			}
			if(rowArray.size() == 0){
				ebr = new BinaryRow(aLayout, instance, 8);
				ebr.putNewRow();

				rowArray.add(ebr);
			}
		}
    }
}
