package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class Settings extends Activity {
	
	ToggleButton muteSound;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings);
	    muteSound = (ToggleButton) findViewById(R.id.muteSoundToggle);
	    muteSound.setOnClickListener(new MuteSoundToggleListener());
	  }
	
	private class MuteSoundToggleListener implements OnClickListener {
		public void onClick(View view) {
			setPreference(muteSound.isChecked());
		}
	}
	
	private void setPreference(boolean setting) {
		//Store info in preference file?

		CrunchConstants.SOUND_MUTED = setting;
	}
}
