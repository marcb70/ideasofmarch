package edu.csumb.ideasofmarch.codecruncher;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log; 

public class SoundHelper {
	Activity mActivity = null;
	private SoundPool soundPool;
	private int dingSound, clapSound;
	private boolean loaded = false;
	private AudioManager audioManager;
	private float actualVolume;
	private float maxVolume;
	private float volume;

	public SoundHelper(Activity anActivity) {
		mActivity = anActivity;
		Context activityContext = mActivity.getBaseContext();
		mActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				
				if (status == 0) {
					loaded = true;
				}
			}	
		});


		audioManager = (AudioManager) activityContext
				.getSystemService(Context.AUDIO_SERVICE);
		actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volume = actualVolume / maxVolume;

	}
	
	public void loadDing(){
		dingSound = soundPool.load(mActivity, R.raw.ding, 1);
	}

	public void playDing() {
		if (loaded&&CrunchConstants.SOUND_MUTED) {
			soundPool.play(dingSound, volume, volume, 1, 0, 1f);
		}
	}

	public void loadClap(){
		clapSound = soundPool.load(mActivity, R.raw.clap, 1);
	}
	public void playClap() {
		Log.v("playing clap", "yeah! " + loaded);
		if (loaded&&CrunchConstants.SOUND_MUTED) {
			soundPool.play(clapSound, volume, volume, 1, 0, 1f);
		}
	}
	
	public void kill(){

		soundPool.release();
		soundPool = null;
	}
}
