package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends Activity {
	private SoundPool soundPool;
	private int clapSound;
	boolean loaded = false;
	private int score;
	public int GAME_TAG;
	Button tryAgain;
	Button mainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		Intent intent = getIntent();
		score = intent.getIntExtra("GAME_SCORE", 0);
		GAME_TAG = intent.getIntExtra("GAME_TAG_CODE", 0);
		
		TextView eText = (TextView) findViewById(R.id.MessageText);
		eText.setText("You scored: " + score);

		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});

		clapSound = soundPool.load(this, R.raw.clap, 1);

		String name = CrunchConstants.myPreferencesMap
				.get(CrunchConstants.JSON_NAME);

		//Attempt to update the score with new name.
		new ScoresHelper(getBaseContext()).putGlobalHighScore(name, score,
				GAME_TAG);

		//play sound if the score is better than personal best
		if (score > CrunchConstants.myScoresMap.get(GAME_TAG)) {
			
			AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
			float actualVolume = (float) audioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			float maxVolume = (float) audioManager
					.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			float volume = actualVolume / maxVolume;
			// Is the sound loaded already?
			if (loaded) {
				soundPool.play(clapSound, volume, volume, 1, 0, 1f);
			}
		}
		mainMenu = (Button) findViewById(R.id.MainMenu);
		mainMenu.setOnClickListener(new MainMenuButtonListener());
		
		tryAgain = (Button) findViewById(R.id.TryAgain);
		tryAgain.setOnClickListener(new TryAgainButtonListener());
	}
	
	private class MainMenuButtonListener implements OnClickListener {
		public void onClick(View view) {
			startNewMainMenu();
		}
    }
	
	public void startNewMainMenu() {
    	startActivity(new Intent(this, MainActivity.class));
    	finish();
    }
	
	private class TryAgainButtonListener implements OnClickListener {
		public void onClick(View view) {
			startTryAgain();
		}
    }
    
    public void startTryAgain() {
    	switch (GAME_TAG) {
    	case CrunchConstants.BINARY_TO_DECIMAL:
    		startActivity(new Intent(this, BinaryToDecimal.class));
    		finish();
    		break;
    		
    	case CrunchConstants.BINARY_TO_DECIMAL_HARD:
    		startActivity(new Intent(this, BinaryToDecimalHard.class));
    		finish();
    		break;
    		
    	case CrunchConstants.HEX_TO_DECIMAL:
    		startActivity(new Intent(this, HexToDecimal.class));
    		finish();
    		break;
    		
    	case CrunchConstants.DECIMAL_TO_HEX:
    		startActivity(new Intent(this, DecimalToHex.class));
    		finish();
    		break;
    		
    	case CrunchConstants.DECIMAL_TO_BINARY:
    		startActivity(new Intent(this, DecimalToBinary.class));
    		finish();
    		break;
    		
    	case CrunchConstants.BINARY_TO_HEX:
    		startActivity(new Intent(this, BinaryToHex.class));
    		finish();
    		break;
    		
    	case CrunchConstants.BINARY_TO_HEX_HARD:
    		startActivity(new Intent(this, BinaryToHexHard.class));
    		finish();
    		break;
    		
    	case CrunchConstants.HEX_TO_BINARY:
    		startActivity(new Intent(this, HexToBinary.class));
    		finish();
    		break;
    		
    	default:
    		startActivity(new Intent(this, MainActivity.class));
    		finish();
    		break;
    	}
    		
    		
    }
}
