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

public class BinaryToDecimal extends Activity {

	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	// private ToggleButton digits1[];
	// private String binaryInput;
	private Button submitButton;
	// private TextView decimalSolution1;
	private TextView clock;
	// private int solution1;
	private int score = 0;
	private FourBitRow fbr;
	private ArrayList<FourBitRow> rowArray = new ArrayList<FourBitRow>();
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
		fbr = new FourBitRow(aLayout, getBaseContext());
		// rowArray.add(fbr);

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
				intent.putExtra("GAME_TAG_CODE", CrunchConstants.BINARY_TO_DECIMAL);
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
				fbr = new FourBitRow(aLayout, getBaseContext());
				fbr.putNewRow();

				rowArray.add(fbr);
			}

		};

		clock = (TextView) findViewById(R.id.title);
		submitButton = (Button) findViewById(R.id.submitButton);
		/*
		 * decimalSolution1 = (TextView) findViewById(R.id.decimalSolution1);
		 * digits1 = new ToggleButton[numDigits];
		 * 
		 * digits1[0] = (ToggleButton) findViewById(R.id.digit1); digits1[1] =
		 * (ToggleButton) findViewById(R.id.digit2); digits1[2] = (ToggleButton)
		 * findViewById(R.id.digit3); digits1[3] = (ToggleButton)
		 * findViewById(R.id.digit4);
		 * 
		 * solution1 = (int) Math.floor(Math.random()*16);
		 * decimalSolution1.setText("" + solution1);
		 */
		submitButton.setOnClickListener(new SubmitButtonListener());
		gameClock.start();
		moreTimer.start();

	}

	private class SubmitButtonListener implements OnClickListener {
		public void onClick(View view) {
			// binaryInput = getGuess();
			// int decimalGuess = convertBinarytoDecimal(binaryInput);

			if (rowArray.size() != 0) {
				for (int i = 0; i < rowArray.size(); i++) {
					if (rowArray.get(i).checkProblem()) {
						score += 5;
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
			if (rowArray.size() == 0) {
				fbr = new FourBitRow(aLayout, getBaseContext());
				fbr.putNewRow();

				rowArray.add(fbr);
			}
		}
	}
	/*
	 * public String getGuess() { String binaryInput = "";
	 * 
	 * for(int i = numDigits-1; i >= 0; i--) { if(digits1[i].isChecked()) {
	 * binaryInput += "1"; } else { binaryInput += "0"; }
	 * 
	 * }
	 * 
	 * return binaryInput; }
	 * 
	 * public int convertBinarytoDecimal(String binaryInput) { return
	 * Integer.parseInt(binaryInput, 2); }
	 */

}
