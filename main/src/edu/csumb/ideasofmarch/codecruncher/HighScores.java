package edu.csumb.ideasofmarch.codecruncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HighScores extends Activity {

	private static final int NUM_GLOBAL_SCORES_TO_DISPLAY = 10;

	private SharedPreferences localHighScores;
	private TextView localScoresTextView[];
	private TextView localTotalTextView;
	private int localTotal;
	private TextView globalHighRanking[];
	private TextView globalNickname[];
	private TextView globalHighScoreNum[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_scores);

		populateLocalScores();
		populateGlobalScores();

		putGlobalHighScore("KKK", 222);
	}

	private void populateGlobalScores() {
		globalHighRanking = new TextView[NUM_GLOBAL_SCORES_TO_DISPLAY];
		globalNickname = new TextView[NUM_GLOBAL_SCORES_TO_DISPLAY];
		globalHighScoreNum = new TextView[NUM_GLOBAL_SCORES_TO_DISPLAY];
		//Map <String, String> globalHighScores = getGlobalHighScores();
		
		for (int i = 0; i < NUM_GLOBAL_SCORES_TO_DISPLAY; i++) {
			int resID = getResources().getIdentifier(
					"globalHighRanking" + (i + 1), "id", getPackageName());
			globalHighRanking[i] = (TextView) findViewById(resID);
			globalHighRanking[i].setText("" + (i + 1) + ".");
			
			resID = getResources().getIdentifier(
					"globalNickname" + (i + 1), "id", getPackageName());
			globalNickname[i] = (TextView) findViewById(resID);
			globalNickname[i].setText("AAA");
			
			resID = getResources().getIdentifier(
					"globalHighScoreNum" + (i + 1), "id", getPackageName());
			globalHighScoreNum[i] = (TextView) findViewById(resID);
			globalHighScoreNum[i].setText("0");
		}
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

	public void putGlobalHighScore(String name, int score) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(CrunchConstants.backendURL + "/?name="
				+ name + "&score=" + score);

		try {
			httpclient.execute(httpget);

		} catch (Exception e) {
			Log.v("error",
					"Exception thrown! Check out calls to 'getGlobalHighScores().'");
		}
	}

	public static Map<String,String> getGlobalHighScores() {

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet globalScores = new HttpGet(CrunchConstants.backendURL
				+ "/?hs=1");
		Map<String, String> globalHighScores = new HashMap<String, String>();

		HttpResponse response;

		try {
			response = httpclient.execute(globalScores);

			Log.i("getGlobalHighScores", response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();

			if (entity != null) {

				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				globalHighScores = new Gson().fromJson(result,
						new TypeToken<Map<String, String>>() {
						}.getType());
				instream.close();
			}

		} catch (Exception e) {
			Log.v("error",
					"Exception thrown! Check out calls to 'getGlobalHighScores().'");
		}

		return globalHighScores;

	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
