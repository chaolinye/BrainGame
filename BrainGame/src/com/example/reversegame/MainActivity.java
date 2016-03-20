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
 * 主界面类，用于选择游戏模式，设置音效，查看最高分
 * 
 * @author chaolin
 * @version 1.0
 * 
 */
public class MainActivity extends Activity {
	/* 经典模式按钮 */
	ImageButton classics;
	/* 挑战模式按钮 */
	ImageButton challenge;
	/* 闯关模式按钮 */
	ImageButton breakthrough;

	SharedPreferences preferences;
	
	CheckBox sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* 初始化相关数据 */
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
		/* 为经典模式按钮设置监听器，点击跳转到经典模式游戏界面 */
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
		/* 为经典模式按钮设置监听器，点击跳转到经典模式游戏界面 */
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
		/* 为经典模式按钮设置监听器，点击跳转到经典模式游戏界面 */
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
	 * 初始化数据，获取游戏最高分纪录
	 */
	private void initData() {
		preferences = getSharedPreferences("reverse", MODE_WORLD_READABLE);
		if (preferences == null)
			return;

		/* 获取经典模式最高分 */
		GameData.maxClassisc = preferences.getInt("classisc", 0);

		/* 获取挑战模式最高分 */
		GameData.maxChallenge = preferences.getInt("challenge", 0);

		/* 获取闯关模式最高分 */
		GameData.maxBreakthrough = preferences.getInt("breakthrough", 0);
	}
}
