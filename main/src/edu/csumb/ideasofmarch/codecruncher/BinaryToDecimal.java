package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BinaryToDecimal extends Activity {
	
	private BinaryToDecimal instance = null;
	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private Button submitButton;
	private TextView clock;
	private int score = 0;
	private BinaryRow fbr;
	private ArrayList<BinaryRow> rowArray = new ArrayList<BinaryRow>();
	private LinearLayout aLayout;
	private Context context;
	private SoundPool soundPool;
	private int dingSound;
	boolean loaded = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		context = this.getApplicationContext();
		setContentView(R.layout.binary_to_decimal);
		aLayout = (LinearLayout) findViewById(R.id.mainLayout);
		instance = this;
		fbr = new BinaryRow(aLayout, instance, 4);

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

		gameClock = new CountDownTimer(60000, 1000) {

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				fbr.resetRowCount();
				Intent intent = new Intent(getBaseContext(), GameOver.class);
				intent.putExtra("GAME_TAG_CODE",
						CrunchConstants.BINARY_TO_DECIMAL);
				intent.putExtra("GAME_SCORE", score);
				startActivity(intent);
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				clock.setText("Decimal to Binary : " + millisUntilFinished
						/ 1000);

			}

		};

		moreTimer = new CountDownTimer(60000, 5000) {

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				fbr = new BinaryRow(aLayout, instance, 4);
				fbr.putNewRow();

				rowArray.add(fbr);
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
				fbr = new BinaryRow(aLayout, instance, 4);
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
