package edu.csumb.ideasofmarch.codecruncher;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BinaryToDecimal extends Activity {
	
	private BinaryToDecimal instance = null;
	public static final int numDigits = 4;
	private CountDownTimer gameClock;
	private CountDownTimer moreTimer;
	private TextView clock;
	private int score = 0;
	private BinaryRow fbr;
	private ArrayList<BinaryRow> rowArray = new ArrayList<BinaryRow>();
	private LinearLayout aLayout;
	private SoundHelper soundHelper;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.binary_to_decimal);
		aLayout = (LinearLayout) findViewById(R.id.mainLayout);
		instance = this;
		fbr = new BinaryRow(aLayout, instance, 4);
		soundHelper = new SoundHelper(instance);
		soundHelper.loadDing();
		gameClock = new CountDownTimer(60000, 1000) {

			@Override
			public void onFinish() {
				fbr.resetRowCount();
				Intent intent = new Intent(getBaseContext(), GameOver.class);
				intent.putExtra("GAME_TAG_CODE",
						CrunchConstants.BINARY_TO_DECIMAL);
				intent.putExtra("GAME_SCORE", score);
				soundHelper.kill();
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

			}

			@Override
			public void onTick(long millisUntilFinished) {
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
		soundHelper.playDing();
	}
}
