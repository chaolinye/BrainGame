package com.example.reversegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 游戏结束Activity
 * @author chaolin
 *
 */
public class OverActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_over);
		((TextView)findViewById(R.id.backTv)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(OverActivity.this,MainActivity.class));
				finish();
			}
		});
		switch(GameData.currentGameModel){
		case GameData.CLASSISCMODEL:
			((TextView)findViewById(R.id.over_model)).setText("经典模式");
			if(GameData.currentGrade>GameData.maxClassisc){
				/*如果当前分数比最高分数高，更新最高分数*/
				GameData.maxClassisc=GameData.currentGrade;
			}
			((TextView)findViewById(R.id.over_max_grade)).setText("最高分"+GameData.maxClassisc);
			break;
		case GameData.CHALLENGEMODEL:
			((TextView)findViewById(R.id.over_model)).setText("挑战模式");
			if(GameData.currentGrade>GameData.maxChallenge){
				/*如果当前分数比最高分数高，更新最高分数*/
				GameData.maxChallenge=GameData.currentGrade;
			}
			((TextView)findViewById(R.id.over_max_grade)).setText("最高分"+GameData.maxChallenge);
			break;
		case GameData.BREAKTHROUGHMODEL:
			((TextView)findViewById(R.id.over_model)).setText("闯关模式");
			if(GameData.currentGrade>GameData.maxBreakthrough){
				/*如果当前分数比最高分数高，更新最高分数*/
				GameData.maxBreakthrough=GameData.currentGrade;
			}
			((TextView)findViewById(R.id.over_max_grade)).setText("最高分"+GameData.maxBreakthrough);
			break;
		}
		TextView grade=(TextView)findViewById(R.id.grade);
		grade.setText(""+GameData.currentGrade);
		if(GameData.currentGrade>=70){
			((TextView)findViewById(R.id.desc)).setText("你是要成为海贼王的男人!!");
		}else if(GameData.currentGrade>=40){
			((TextView)findViewById(R.id.desc)).setText("恭喜你，你的智商高于全国90%的人!");
		}else if(GameData.currentGrade>=20){
			((TextView)findViewById(R.id.desc)).setText("事实证明你是一个正常人.");
		}else{
			((TextView)findViewById(R.id.desc)).setText("砖家说猪是除了人之外智商最高的动物。");
		}
		/*记录游戏分数在本地*/
		SharedPreferences preference=getSharedPreferences("reverse", MODE_WORLD_READABLE);
		SharedPreferences.Editor editor=preference.edit();
		editor.putInt("classisc", GameData.maxClassisc);
		editor.putInt("challenge", GameData.maxChallenge);
		editor.putInt("breakthrough", GameData.maxBreakthrough);
		editor.commit();
	}
	
}
