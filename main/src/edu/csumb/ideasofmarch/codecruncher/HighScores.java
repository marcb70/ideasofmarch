package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HighScores extends Activity {

	private static final int NUM_GLOBAL_SCORES_TO_DISPLAY = 10;
	
	private SharedPreferences localHighScores;
	private TextView localScoresTextView[];
	private TextView localTotalTextView;
	private int localTotal;
	private TextView globalHighRanking[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_scores);

		populateLocalScores();
		populateGlobalScores();

	}

	private void populateLocalScores() {
		localHighScores = getSharedPreferences(
				CrunchConstants.LOCAL_HIGH_SCORES, MODE_PRIVATE);

		localScoresTextView = new TextView[CrunchConstants.NUM_GAME_MODES];
		localScoresTextView[0] = (TextView) findViewById(R.id.mode1Score);
		localScoresTextView[1] = (TextView) findViewById(R.id.mode2Score);
		localScoresTextView[2] = (TextView) findViewById(R.id.mode3Score);
		localScoresTextView[3] = (TextView) findViewById(R.id.mode4Score);
		localScoresTextView[4] = (TextView) findViewById(R.id.mode5Score);
		localScoresTextView[5] = (TextView) findViewById(R.id.mode6Score);
		localTotalTextView = (TextView) findViewById(R.id.cumulativeScore);
		localTotal = 0;

		for (int i = 0; i < CrunchConstants.NUM_GAME_MODES; i++) {
			int temp = localHighScores.getInt("mode1HighScore", 0);
			localScoresTextView[i].setText("" + temp);
			localTotal += temp;
		}

		localTotalTextView.setText("" + localTotal);
	}
	
	private void populateGlobalScores() {
		globalHighRanking = new TextView[NUM_GLOBAL_SCORES_TO_DISPLAY];
		
		for(int i = 0; i < NUM_GLOBAL_SCORES_TO_DISPLAY; i++) {
			int resID = getResources().getIdentifier("globalHighRanking" + (i+1),
				    "id", getPackageName());
			globalHighRanking[i] = (TextView) findViewById(resID);
			globalHighRanking[i].setText("" + (i+1) + ".");
		}
	}
}
