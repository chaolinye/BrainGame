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
 * 经典模式游戏Activity
 * 
 * @author chaolin
 * 
 */
public class GameActivity extends Activity implements Callbacks {
	/* 每种类型Fragment设置两个，主要考虑到Fragment的调用问题 */
	/* 数字游戏Fragment */
	NumberFragment fragment1;
	NumberFragment fragment2;
	/* 手势游戏Fragment */
	GestureFragment fragment3;
	GestureFragment fragment4;
	/* 颜色游戏Fragment */
	ColorFragment fragment5;
	ColorFragment fragment6;
	/* 用于控制交换调用以上两个NumberFragment */
	boolean number;
	/* 用于控制交换调用以上两个GestureFragment */
	boolean gesture;
	/* 用于控制交换调用以上两个ColorFragment */
	boolean color;

	/* 随机数 ,选择Fragment类型 */
	int style;

	/* 时间进度条 */
	ProgressBar timeBar;
	/* 用于设置时钟周期控制时间进度条的减少 */
	Timer timer;
	TimerTask timerTask;
	/* Handler消息传递机制，用于控制和监控时间进度条的减少 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x1314) {
				/* 如果游戏时间为零，转到游戏结束界面 */
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
				/* 时间进度条减少1 */
				timeBar.setProgress(timeBar.getProgress() - 1);

			}
		}

	};

	// 因为android没有给fragment提供触摸事件的监听，但为了防止高耦合，所以利用观察者模式，
	// 在这个activity中定义一个内部接口
	private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
			10);

	// dispatchTouchEvent，这个事件是用于分配 MotionEvent的
	// 利用dispatchTouchEvent这个方法，可以把触摸事件分配到每一个onTouchListeners
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

	// 一个内部接口，其实现交给GestureFragment
	public interface MyOnTouchListener {
		public boolean onTouch(MotionEvent ev);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		timeBar = (ProgressBar) findViewById(R.id.timeBar);
		/* 设置游戏当前分数为零 */
		GameData.currentGrade = 0;
		/* 初始化相关控件 */
		init();
	}

	/**
	 * 初始化Fragment，并调用第一个Fragment
	 * 
	 * 设置时间进度条减少的时钟周期
	 */
	public void init() {
		fragment1 = new NumberFragment();
		fragment2 = new NumberFragment();
		fragment3 = new GestureFragment();
		fragment4 = new GestureFragment();
		fragment5 = new ColorFragment();
		fragment6 = new ColorFragment();
		/* 调用第一个Fragment */
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
	 * 重写Callbacks接口的函数，在Fragment中选中正确答案时调用
	 */
	@Override
	public void successClicked() {
		// TODO Auto-generated method stub
		/* 游戏分数加一 */
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
		/* 更换Fragment */
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
