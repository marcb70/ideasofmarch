package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BinaryToHexHard extends Activity {
	
	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private Button submitButton;
	private TextView hexSolution;
	private TextView clock;
	private int score = 0;
	private EightBitHexRow ebr;
	private ArrayList <EightBitHexRow> rowArray = new ArrayList<EightBitHexRow>();
	private LinearLayout aLayout;
	private Context context;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    context = this.getApplicationContext();
	    setContentView(R.layout.binary_to_decimal);
	    aLayout = (LinearLayout) findViewById(R.id.mainLayout);
	    ebr = new EightBitHexRow(aLayout, getBaseContext());
	    
	    gameClock = new CountDownTimer(60000,1000){

			@Override
			public void onFinish() {
				clock.setText("Game Over " + score + " points");
				String name = CrunchConstants.myPreferencesMap.get(CrunchConstants.JSON_NAME);
				if(score > CrunchConstants.myScoresMap.get(CrunchConstants.BINARY_TO_DECIMAL)){
					new ScoresHelper(context).putGlobalHighScore(name, score,CrunchConstants.BINARY_TO_DECIMAL);
				}
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
				ebr = new EightBitHexRow(aLayout, getBaseContext());
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
					}
				}
			}
			if(rowArray.size() == 0){
				ebr = new EightBitHexRow(aLayout, getBaseContext());
				ebr.putNewRow();

				rowArray.add(ebr);
			}
		}
    }
}
