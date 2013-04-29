package edu.csumb.ideasofmarch.codecruncher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressLint("UseSparseArrays")
public class MainActivity extends Activity {

	Button newGameButton;
	Button donateButton;
	Button highScoresButton;
	Button quitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		disabledPolicy();
		initLocalScores();

		// Get UserName now handles creating and reading in preferences map.
		// Preferences map is currently just current signed in user.
		// lots of current in that line.
		getUserName();
		// initLocalPreferences();

		newGameButton = (Button) findViewById(R.id.newGameButton);
		donateButton = (Button) findViewById(R.id.donateButton);
		highScoresButton = (Button) findViewById(R.id.highScoresButton);
		quitButton = (Button) findViewById(R.id.quitButton);

		newGameButton.setOnClickListener(new NewGameButtonListener());
		donateButton.setOnClickListener(new DonateButtonListener());
		highScoresButton.setOnClickListener(new HighScoresButtonListener());
		quitButton.setOnClickListener(new QuitButtonListener());
	}

	private void getUserName() {

		String name = "";
		List<String> nameList = new ArrayList<String>();
		nameList.add("");
		nameList.add("Anonymous");
		
		for (String key : CrunchConstants.myScoresMap.keySet()){
			nameList.add(key);
		}
		String[] nameArray = (String[]) nameList.toArray(new String[0]);
		final ArrayAdapter<String> adp = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_spinner_item,
				nameArray);

		// tx= (TextView)findViewById(R.id.txt1);
		final Spinner sp = new Spinner(MainActivity.this);
		sp.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		sp.setAdapter(adp);

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setView(sp);
		builder.setTitle("What is your username?");
		final AlertDialog dialog = builder.create();
		dialog.show();
		
//		builder.setPositiveButton(RESULT_OK, new AlertDialog.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				updateUserName(sp.getSelectedItem().toString());
//				
//			}});		
//		builder.setNegativeButton(RESULT_CANCELED, new AlertDialog.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				updateUserName("AnonymousCoward");
//				
//			}});		

		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				if(arg2 != 0){
					updateUserName(arg0.getSelectedItem().toString());
					dialog.dismiss();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void updateUserName(String name) {
		if (CrunchConstants.myPreferencesMap == null)
			CrunchConstants.myPreferencesMap = new HashMap<String, String>();
		if (CrunchConstants.myPreferencesMap
				.containsKey(CrunchConstants.JSON_NAME))
			CrunchConstants.myPreferencesMap.remove(CrunchConstants.JSON_NAME);

		CrunchConstants.myPreferencesMap.put(CrunchConstants.JSON_NAME, name);
		if (!CrunchConstants.myScoresMap
				.containsKey(CrunchConstants.myPreferencesMap
						.get(CrunchConstants.JSON_NAME))) {
			Map<Integer, Integer> myTemporaryMap = new HashMap<Integer, Integer>();
			myTemporaryMap.put(CrunchConstants.DECIMAL_TO_BINARY, 0);
			myTemporaryMap.put(CrunchConstants.DECIMAL_TO_HEX, 0);
			myTemporaryMap.put(CrunchConstants.HEX_TO_BINARY, 0);
			myTemporaryMap.put(CrunchConstants.HEX_TO_DECIMAL, 0);
			myTemporaryMap.put(CrunchConstants.BINARY_TO_DECIMAL, 0);
			myTemporaryMap.put(CrunchConstants.BINARY_TO_HEX, 0);
			myTemporaryMap.put(CrunchConstants.DECIMAL_TO_BINARY_HARD, 0);
			myTemporaryMap.put(CrunchConstants.DECIMAL_TO_HEX_HARD, 0);
			myTemporaryMap.put(CrunchConstants.HEX_TO_BINARY_HARD, 0);
			myTemporaryMap.put(CrunchConstants.HEX_TO_DECIMAL_HARD, 0);
			myTemporaryMap.put(CrunchConstants.BINARY_TO_DECIMAL_HARD, 0);
			myTemporaryMap.put(CrunchConstants.BINARY_TO_HEX_HARD, 0);

			CrunchConstants.myScoresMap.put(CrunchConstants.myPreferencesMap
					.get(CrunchConstants.JSON_NAME), myTemporaryMap);
		}

		try {
			FileOutputStream fos = openFileOutput(
					CrunchConstants.PREFERENCES_FILENAME, MODE_PRIVATE);

			fos.write(new Gson().toJson(CrunchConstants.myPreferencesMap)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_settings:
			startSettings();
			return true;
		case R.id.action_help:
			startHelp();
			return true;
		case R.id.action_about:
			startAbout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void startSettings() {
		startActivity(new Intent(this, Settings.class));
	}

	public void startHelp() {
		startActivity(new Intent(this, Help.class));
	}

	public void startAbout() {
		startActivity(new Intent(this, About.class));
	}

	private class NewGameButtonListener implements OnClickListener {
		public void onClick(View view) {
			startChooseMode();
		}
	}

	public void startChooseMode() {
		startActivity(new Intent(this, ChooseMode.class));
	}

	private class DonateButtonListener implements OnClickListener {
		public void onClick(View view) {
			startDonate();
		}
	}

	private void startDonate() {
		goToUrl("https://milcyber.org/#donate");
	}

	private void goToUrl(String url) {
		Uri uriUrl = Uri.parse(url);
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
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

	private void disabledPolicy() {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}

	/**
	 * Eats file saved local information
	 */
	private void initLocalScores() {
		try {

			InputStream is = openFileInput(CrunchConstants.SCORES_FILENAME);

			final Gson gson = new Gson();
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			CrunchConstants.myScoresMap = gson.fromJson(reader,
					new TypeToken<HashMap<String, Map<Integer, Integer>>>() {
					}.getType());

			is.close();

		} catch (Exception e) {

			e.printStackTrace();
			Log.v("error", "died in get assets init local high scores", e);

			HashMap<String, Map<Integer, Integer>> temporaryScoresMap = new HashMap<String, Map<Integer, Integer>>();
			CrunchConstants.myScoresMap = temporaryScoresMap;
		}

		try {
			FileOutputStream fos = openFileOutput(
					CrunchConstants.SCORES_FILENAME, MODE_PRIVATE);

			fos.write(new Gson().toJson(CrunchConstants.myScoresMap).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void initLocalPreferences() {

		try {

			InputStream is = openFileInput(CrunchConstants.PREFERENCES_FILENAME);
			final Gson gson = new Gson();
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			CrunchConstants.myPreferencesMap = gson.fromJson(reader,
					new TypeToken<Map<String, String>>() {
					}.getType());
		} catch (Exception e) {

			e.printStackTrace();
			Log.v("error", "died in get assets init local high scores", e);
			// Map<String, String> myTemporaryMap = new HashMap<String,
			// String>();
			// CrunchConstants.myPreferencesMap = myTemporaryMap;

		}

		// Successfully init

		try {
			FileOutputStream fos = openFileOutput(
					CrunchConstants.PREFERENCES_FILENAME, MODE_PRIVATE);

			fos.write(new Gson().toJson(CrunchConstants.myPreferencesMap)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
