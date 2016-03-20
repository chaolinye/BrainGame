package com.example.reversegame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.ProgressBar;

/**
 * ����ģʽ��ϷActivity
 * 
 * @author chaolin
 * 
 */
public class GameActivity extends Activity implements Callbacks {
	/* ÿ������Fragment������������Ҫ���ǵ�Fragment�ĵ������� */
	/* ������ϷFragment */
	NumberFragment fragment1;
	NumberFragment fragment2;
	/* ������ϷFragment */
	GestureFragment fragment3;
	GestureFragment fragment4;
	/* ��ɫ��ϷFragment */
	ColorFragment fragment5;
	ColorFragment fragment6;
	/* ���ڿ��ƽ���������������NumberFragment */
	boolean number;
	/* ���ڿ��ƽ���������������GestureFragment */
	boolean gesture;
	/* ���ڿ��ƽ���������������ColorFragment */
	boolean color;

	/* ����� ,ѡ��Fragment���� */
	int style;

	/* ʱ������� */
	ProgressBar timeBar;
	/* ��������ʱ�����ڿ���ʱ��������ļ��� */
	Timer timer;
	TimerTask timerTask;
	/* Handler��Ϣ���ݻ��ƣ����ڿ��ƺͼ��ʱ��������ļ��� */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x1314) {
				/* �����Ϸʱ��Ϊ�㣬ת����Ϸ�������� */
				if (timeBar.getProgress() == 0) {
					Intent intent = new Intent(GameActivity.this,
							OverActivity.class);
					startActivity(intent);
					if (timerTask != null) {
						timerTask.cancel();
						timerTask = null;
					}
					if (timer != null) {
						timer.cancel();
						timer.purge();
						timer = null;
					}
					finish();
				}
				/* ʱ�����������1 */
				timeBar.setProgress(timeBar.getProgress() - 1);

			}
		}

	};

	// ��Ϊandroidû�и�fragment�ṩ�����¼��ļ�������Ϊ�˷�ֹ����ϣ��������ù۲���ģʽ��
	// �����activity�ж���һ���ڲ��ӿ�
	private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
			10);

	// dispatchTouchEvent������¼������ڷ��� MotionEvent��
	// ����dispatchTouchEvent������������԰Ѵ����¼����䵽ÿһ��onTouchListeners
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		for (MyOnTouchListener listener : onTouchListeners) {
			listener.onTouch(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
		onTouchListeners.add(myOnTouchListener);
	}

	public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
		onTouchListeners.remove(myOnTouchListener);
	}

	// һ���ڲ��ӿڣ���ʵ�ֽ���GestureFragment
	public interface MyOnTouchListener {
		public boolean onTouch(MotionEvent ev);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		timeBar = (ProgressBar) findViewById(R.id.timeBar);
		/* ������Ϸ��ǰ����Ϊ�� */
		GameData.currentGrade = 0;
		/* ��ʼ����ؿؼ� */
		init();
	}

	/**
	 * ��ʼ��Fragment�������õ�һ��Fragment
	 * 
	 * ����ʱ����������ٵ�ʱ������
	 */
	public void init() {
		fragment1 = new NumberFragment();
		fragment2 = new NumberFragment();
		fragment3 = new GestureFragment();
		fragment4 = new GestureFragment();
		fragment5 = new ColorFragment();
		fragment6 = new ColorFragment();
		/* ���õ�һ��Fragment */
		getFragmentManager().beginTransaction()
				.replace(R.id.container, fragment1).commit();
		selectModel();
	}

	public void selectModel() {
		timer = new Timer();
		timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x1314);
			}
		};
		switch (GameData.currentGameModel) {
		case GameData.CLASSISCMODEL:
			timer.schedule(timerTask, 0, 100);
			break;
		case GameData.CHALLENGEMODEL:
			timer.schedule(timerTask, 0, 8);
			break;
		case GameData.BREAKTHROUGHMODEL:
			timer.schedule(timerTask, 0, 50);
			break;
		}
	}

	/**
	 * ��дCallbacks�ӿڵĺ�������Fragment��ѡ����ȷ��ʱ����
	 */
	@Override
	public void successClicked() {
		// TODO Auto-generated method stub
		/* ��Ϸ������һ */
		if (GameData.isPlaySounds) {
			MediaPlayer mPlayer = MediaPlayer.create(GameActivity.this,
					R.raw.press);
			mPlayer.start();
		}
		GameData.currentGrade++;
		if (GameData.currentGameModel == GameData.CHALLENGEMODEL) {
			timeBar.setProgress(timeBar.getMax());
		} else if (GameData.currentGameModel == GameData.BREAKTHROUGHMODEL) {
			if (GameData.currentGrade % 10 == 0) {
				timeBar.setProgress((int) (timeBar.getMax() * Math.pow(0.8,
						GameData.currentGrade / 10)));
			}
		}
		/* ����Fragment */
		style = (int) (Math.random() * 3);
		switch (style) {
		case 0:
			if (number == false) {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment2).commit();
				number = true;
			} else {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment1).commit();
				number = false;
			}
			break;
		case 1:
			if (gesture == false) {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment4).commit();
				gesture = true;
			} else {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment3).commit();
				gesture = false;
			}
			break;
		case 2:
			if (color == false) {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment5).commit();
				color = true;
			} else {
				getFragmentManager().beginTransaction()
						.replace(R.id.container, fragment5).commit();
				color = false;
			}
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		if (timerTask != null) {
			timerTask.cancel();
			timerTask = null;
		}
		if (timer != null) {
			timer.cancel();
			timer.purge();
			timer = null;
		}
		super.onStop();
	}

}
