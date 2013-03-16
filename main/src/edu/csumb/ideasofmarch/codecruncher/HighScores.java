package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HighScores extends Activity {
	
	private static final int NUM_GAME_MODES = 5;
	
	private SharedPreferences localHighScores;
	private TextView localScoresTextView[];
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.high_scores);
	    
	    localHighScores = getSharedPreferences("localHighScores", MODE_PRIVATE);
	    
	    localScoresTextView = new TextView[NUM_GAME_MODES];
	    localScoresTextView[0] = (TextView) findViewById(R.id.mode1Score);
	    localScoresTextView[1] = (TextView) findViewById(R.id.mode2Score);
	    localScoresTextView[2] = (TextView) findViewById(R.id.mode3Score);
	    localScoresTextView[3] = (TextView) findViewById(R.id.mode4Score);
	    localScoresTextView[4] = (TextView) findViewById(R.id.mode5Score);

	    for(int i = 0; i < NUM_GAME_MODES; i++) {
	    	localScoresTextView[i].setText("" + localHighScores.getInt("mode1HighScore", 0));
	    }
	    
	  }
}
