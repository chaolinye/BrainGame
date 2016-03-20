package com.example.reversegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;

/**
 * �������࣬����ѡ����Ϸģʽ��������Ч���鿴��߷�
 * 
 * @author chaolin
 * @version 1.0
 * 
 */
public class MainActivity extends Activity {
	/* ����ģʽ��ť */
	ImageButton classics;
	/* ��սģʽ��ť */
	ImageButton challenge;
	/* ����ģʽ��ť */
	ImageButton breakthrough;

	SharedPreferences preferences;
	
	CheckBox sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* ��ʼ��������� */
		initData();
		sound=(CheckBox)findViewById(R.id.sound);
		sound.setChecked(GameData.isPlaySounds);
		sound.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				SharedPreferences preference1=getSharedPreferences("reverse", MODE_WORLD_READABLE);
				SharedPreferences.Editor editor=preference1.edit();
				GameData.isPlaySounds=arg1;
				editor.putBoolean("isPlaySounds",GameData.isPlaySounds);
				editor.commit();
			}
		});
		classics = (ImageButton) findViewById(R.id.classics);
		/* Ϊ����ģʽ��ť���ü������������ת������ģʽ��Ϸ���� */
		classics.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this,
							R.raw.welcome);
					mPlayer.start();
				}
				GameData.currentGameModel = GameData.CLASSISCMODEL;
				Intent intent = new Intent(MainActivity.this,
						GameActivity.class);
				startActivity(intent);
			}
		});
		challenge = (ImageButton) findViewById(R.id.challenge);
		/* Ϊ����ģʽ��ť���ü������������ת������ģʽ��Ϸ���� */
		challenge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this,
							R.raw.welcome);
					mPlayer.start();
				}
				GameData.currentGameModel = GameData.CHALLENGEMODEL;
				Intent intent = new Intent(MainActivity.this,
						GameActivity.class);
				startActivity(intent);
			}
		});
		breakthrough = (ImageButton) findViewById(R.id.breakthrough);
		/* Ϊ����ģʽ��ť���ü������������ת������ģʽ��Ϸ���� */
		breakthrough.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this,
							R.raw.welcome);
					mPlayer.start();
				}
				GameData.currentGameModel = GameData.BREAKTHROUGHMODEL;
				Intent intent = new Intent(MainActivity.this,
						GameActivity.class);
				startActivity(intent);
			}
		});

		((ImageButton) findViewById(R.id.maxGrade))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (GameData.isPlaySounds) {
							MediaPlayer mPlayer = MediaPlayer.create(
									MainActivity.this, R.raw.open);
							mPlayer.start();
						}
						startActivity(new Intent(MainActivity.this,
								MaxGradeActivity.class));
					}
				});
	}

	/**
	 * ��ʼ�����ݣ���ȡ��Ϸ��߷ּ�¼
	 */
	private void initData() {
		preferences = getSharedPreferences("reverse", MODE_WORLD_READABLE);
		if (preferences == null)
			return;

		/* ��ȡ����ģʽ��߷� */
		GameData.maxClassisc = preferences.getInt("classisc", 0);

		/* ��ȡ��սģʽ��߷� */
		GameData.maxChallenge = preferences.getInt("challenge", 0);

		/* ��ȡ����ģʽ��߷� */
		GameData.maxBreakthrough = preferences.getInt("breakthrough", 0);
	}
}
