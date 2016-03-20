package com.example.reversegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * ��Ϸ����Activity
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
			((TextView)findViewById(R.id.over_model)).setText("����ģʽ");
			if(GameData.currentGrade>GameData.maxClassisc){
				/*�����ǰ��������߷����ߣ�������߷���*/
				GameData.maxClassisc=GameData.currentGrade;
			}
			((TextView)findViewById(R.id.over_max_grade)).setText("��߷�"+GameData.maxClassisc);
			break;
		case GameData.CHALLENGEMODEL:
			((TextView)findViewById(R.id.over_model)).setText("��սģʽ");
			if(GameData.currentGrade>GameData.maxChallenge){
				/*�����ǰ��������߷����ߣ�������߷���*/
				GameData.maxChallenge=GameData.currentGrade;
			}
			((TextView)findViewById(R.id.over_max_grade)).setText("��߷�"+GameData.maxChallenge);
			break;
		case GameData.BREAKTHROUGHMODEL:
			((TextView)findViewById(R.id.over_model)).setText("����ģʽ");
			if(GameData.currentGrade>GameData.maxBreakthrough){
				/*�����ǰ��������߷����ߣ�������߷���*/
				GameData.maxBreakthrough=GameData.currentGrade;
			}
			((TextView)findViewById(R.id.over_max_grade)).setText("��߷�"+GameData.maxBreakthrough);
			break;
		}
		TextView grade=(TextView)findViewById(R.id.grade);
		grade.setText(""+GameData.currentGrade);
		if(GameData.currentGrade>=70){
			((TextView)findViewById(R.id.desc)).setText("����Ҫ��Ϊ������������!!");
		}else if(GameData.currentGrade>=40){
			((TextView)findViewById(R.id.desc)).setText("��ϲ�㣬������̸���ȫ��90%����!");
		}else if(GameData.currentGrade>=20){
			((TextView)findViewById(R.id.desc)).setText("��ʵ֤������һ��������.");
		}else{
			((TextView)findViewById(R.id.desc)).setText("ש��˵���ǳ�����֮��������ߵĶ��");
		}
		/*��¼��Ϸ�����ڱ���*/
		SharedPreferences preference=getSharedPreferences("reverse", MODE_WORLD_READABLE);
		SharedPreferences.Editor editor=preference.edit();
		editor.putInt("classisc", GameData.maxClassisc);
		editor.putInt("challenge", GameData.maxChallenge);
		editor.putInt("breakthrough", GameData.maxBreakthrough);
		editor.commit();
	}
	
}
