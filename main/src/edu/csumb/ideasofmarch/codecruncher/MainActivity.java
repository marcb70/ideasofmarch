package edu.csumb.ideasofmarch.codecruncher;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button newGameButton;
	Button continueButton;
	Button optionsButton;
	Button highScoresButton;
	Button quitButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        newGameButton = (Button) findViewById(R.id.newGameButton);
        continueButton = (Button) findViewById(R.id.continueButton);
        optionsButton = (Button) findViewById(R.id.optionsButton);
        highScoresButton = (Button) findViewById(R.id.highScoresButton);
        quitButton = (Button) findViewById(R.id.quitButton);
        
        newGameButton.setOnClickListener(new NewGameButtonListener());
        continueButton.setOnClickListener(new ContinueButtonListener());
        optionsButton.setOnClickListener(new OptionsButtonListener());
        highScoresButton.setOnClickListener(new HighScoresButtonListener());
        quitButton.setOnClickListener(new QuitButtonListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private class NewGameButtonListener implements OnClickListener {
		public void onClick(View view) {
			startChooseMode();
		}
    }
    
    public void startChooseMode() {
    	startActivity(new Intent(this, ChooseMode.class));
    }
    
    private class ContinueButtonListener implements OnClickListener {
		public void onClick(View view) {
			startGame();
		}
    }
    
    public void startGame() {
    	startActivity(new Intent(this, BinaryToDecimal.class));
    }
    
    private class OptionsButtonListener implements OnClickListener {
		public void onClick(View view) {
			startOptions();
		}
    }
    
    public void startOptions() {
    	startActivity(new Intent(this, Options.class));
    }
    
    private class HighScoresButtonListener implements OnClickListener {
		public void onClick(View view) {
			startHighScores();
		}
    }
    
    public void startHighScores() {
    	startActivity(new Intent(this, HighScores.class));
    }
    
    private class QuitButtonListener implements OnClickListener {
		public void onClick(View view) {
			System.exit(0);
		}
    }
    
}
