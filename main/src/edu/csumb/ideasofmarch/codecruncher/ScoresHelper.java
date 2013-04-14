package edu.csumb.ideasofmarch.codecruncher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;

public class ScoresHelper {

	Context mContext = null;

    public ScoresHelper (Context context) {
        mContext = context;
    }

	@SuppressWarnings("deprecation")
	public void putGlobalHighScore(String name, int score, int constantGameType) {
		
//		int current = CrunchConstants.myScoresMap.get(constantGameType);
//		if (score <= current){
//			return;
//		}
		
		
		updateLocalScores(score, constantGameType);
		
		int totalScores = 0;
		
		for(int i = 0; i < CrunchConstants.NUM_GAME_MODES; i++){
			if(CrunchConstants.myScoresMap.containsKey(i+1))
				totalScores += CrunchConstants.myScoresMap.get(i+1);
		}
		
		HttpClient httpclient = new DefaultHttpClient();
		String restUrl = "";
		restUrl = CrunchConstants.backendURL + "/?name=" + URLEncoder.encode(name) + "&score=" + totalScores;
		
		HttpGet httpget = new HttpGet(restUrl);

		try {
			httpclient.execute(httpget);
		} catch (Exception e) {
			Log.v("error",
					"Exception thrown! Check out calls to 'getGlobalHighScores().'");
		}
		
	}
	
	private void updateLocalScores(int score, int constantGameType){
		int current = CrunchConstants.myScoresMap.get(constantGameType);
		if (score > current){
			CrunchConstants.myScoresMap.remove(constantGameType);
			CrunchConstants.myScoresMap.put(constantGameType, score);
		} else {
			return;
		}
		
		
		try {
			FileOutputStream fos = mContext.openFileOutput(CrunchConstants.SCORES_FILENAME, Context.MODE_PRIVATE);
			
			fos.write(new Gson().toJson(CrunchConstants.myScoresMap).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
