package com.example.reversegame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MaxGradeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maxgrade);
		((TextView) findViewById(R.id.maxClassics))
				.setText(GameData.maxClassisc + "");
		((TextView) findViewById(R.id.maxChallenge))
				.setText(GameData.maxChallenge + "");
		((TextView) findViewById(R.id.maxBreakthrough))
				.setText(GameData.maxBreakthrough + "");
		((ImageButton) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (GameData.isPlaySounds) {
							MediaPlayer mPlayer = MediaPlayer.create(
									MaxGradeActivity.this, R.raw.open);
							mPlayer.start();
						}
						startActivity(new Intent(MaxGradeActivity.this,
								MainActivity.class));
						finish();
					}
				});
	}

}
