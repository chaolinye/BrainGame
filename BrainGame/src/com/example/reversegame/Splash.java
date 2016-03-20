package com.example.reversegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // —”≥Ÿ»˝√Î

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		SharedPreferences preferences = getSharedPreferences("reverse",
				MODE_WORLD_READABLE);
		if (preferences != null) {
			GameData.isPlaySounds = preferences.getBoolean("isPlaySounds",
					false);
			if (GameData.isPlaySounds) {
				MediaPlayer mPlayer = MediaPlayer.create(Splash.this,
						R.raw.boot);
				mPlayer.start();
			}
		}
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent mainIntent = new Intent(Splash.this, MainActivity.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
	}

}
