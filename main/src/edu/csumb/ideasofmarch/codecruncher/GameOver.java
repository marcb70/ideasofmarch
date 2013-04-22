package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends Activity {
	private int score;
	private GameOver instance = null;
	public int GAME_TAG;
	Button tryAgain;
	Button mainMenu;
	private SoundHelper soundHelper;
	private CountDownTimer gameClock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		Intent intent = getIntent();
		instance = this;
		score = intent.getIntExtra("GAME_SCORE", 0);
		GAME_TAG = intent.getIntExtra("GAME_TAG_CODE", 0);
		soundHelper = new SoundHelper(instance);
		soundHelper.loadClap();
		
		TextView eText = (TextView) findViewById(R.id.MessageText);
		eText.setText("You scored: " + score);
		String name = CrunchConstants.myPreferencesMap
				.get(CrunchConstants.JSON_NAME);
		

		//Attempt to update the score with new name.
		new ScoresHelper(getBaseContext()).putGlobalHighScore(name, score,
				GAME_TAG);
		

		
		mainMenu = (Button) findViewById(R.id.MainMenu);
		mainMenu.setOnClickListener(new MainMenuButtonListener());
		
		tryAgain = (Button) findViewById(R.id.TryAgain);
		tryAgain.setOnClickListener(new TryAgainButtonListener());
		
		gameClock = new CountDownTimer(4000, 1000) {

			@Override
			public void onFinish() {
				soundHelper.playClap();
			}

			@Override
			public void onTick(long millisUntilFinished) {

			}
		};
		
		gameClock.start();
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
