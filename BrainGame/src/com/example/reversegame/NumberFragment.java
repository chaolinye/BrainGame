package com.example.reversegame;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 数字Fragment，用于显示不同的数字题目
 * 
 * @author chaolin，ruofeng
 * 
 */
public class NumberFragment extends Fragment {
	/**
	 * Model内部类，用于随机生成数字Fragment所需要的数据
	 * 
	 * @author chaolin
	 * 
	 */
	public class Model {
		/* 第一个操作数 */
		public int a;
		/* 操作运算符 */
		public int operator;
		/* 第二个操作数 */
		public int b;
		/* 第三个操作数 */
		public int c;
		/* 第四个操作数 */
		public int d;
		/* 正确答案 */
		public int result;
		/* 错误答案 */
		public int errorResult;

		/**
		 * 随机生成数据
		 */
		public void createData() {
			a = (int) (Math.random() * 20 - 10);
			b = (int) (Math.random() * 20 - 10);
			c = (int) (Math.random() * 20);
			d = (int) (Math.random() * 20);
			operator = (int) (Math.random() * 3);
			switch (operator) {
			case 0:
				result = a * b;
				errorResult = result + (int) (Math.random() * 4 + 1);
				break;
			case 1:
				result = c + d;
				errorResult = result + (int) (Math.random() * 3 + 1);
				break;
			case 2:
				result = c - d;
				errorResult = result + (int) (Math.random() * 3 + 1);
				break;

			}

		}

	}

	/* 用于与Activity的通信 */
	private Callbacks callbacks;
	/* 数据 */
	private Model model;
	/* 左边是否是正确答案，用于辅助事件监听事件 */
	private boolean isLeft;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"NumberFragment所在的Activity必须实现Callbacks接口!");
		}
		/* 把callbacks属性指向Fragment所在Activity */
		callbacks = (Callbacks) activity;
		model = new Model();
	}

	/**
	 * 此函数返回的View，就是Fragmeng所显示的内容，要求每次调用该函数时返回的View显示不同的题目
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		/* 获取要返回的View */
		View numberView = inflater.inflate(R.layout.fragment_number, container,
				false);

		if (model != null) {
			/* 调用model的方法产生随机数据 */
			model.createData();
			/* 根据随机数据设置View中控件的外观 */
			switch (model.operator) {
			case 0:
				((TextView) numberView.findViewById(R.id.display))
						.setText(model.a + "" + "*" + "" + model.b);
				break;
			case 1:
				((TextView) numberView.findViewById(R.id.display))
						.setText(model.c + "" + "+" + "" + model.d);
				break;
			case 2:
				((TextView) numberView.findViewById(R.id.display))
						.setText(model.c + "" + "-" + "" + model.d);
				break;

			}
			/* 生成随机数，用于控制那边是正确答案 */
			int z = (int) (Math.random() * 2);
			if (z == 1) {
				isLeft = false;
				((TextView) numberView.findViewById(R.id.left))
						.setText(model.result + "");
				((TextView) numberView.findViewById(R.id.right))
						.setText(model.errorResult + "");

			} else {
				isLeft = true;

				((TextView) numberView.findViewById(R.id.left))
						.setText(model.errorResult + "");
				((TextView) numberView.findViewById(R.id.right))
						.setText(model.result + "");
			}
		}
		/* 设置左答案控件的事件监听 */
		((TextView) numberView.findViewById(R.id.left))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (isLeft == true) {
							/* 答案选择正确，通知Activity更换Fragment */
							callbacks.successClicked();
						} else {
							/* 答案选择错误，转到游戏结束界面 */
							if (GameData.isPlaySounds) {
								MediaPlayer mPlayer = MediaPlayer.create(
										getActivity(), R.raw.alert);
								mPlayer.start();
							}
							Intent intent = new Intent(getActivity(),
									OverActivity.class);
							startActivity(intent);
							getActivity().finish();
						}
					}
				});
		/* 设置右答案控件的事件监听 */
		((TextView) numberView.findViewById(R.id.right))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (isLeft == false) {
							/* 答案选择正确，通知Activity更换Fragment */
							callbacks.successClicked();
						} else {
							/* 答案选择错误，转到游戏结束界面 */
							if (GameData.isPlaySounds) {
								MediaPlayer mPlayer = MediaPlayer.create(
										getActivity(), R.raw.alert);
								mPlayer.start();
							}
							Intent intent = new Intent(getActivity(),
									OverActivity.class);
							startActivity(intent);
							getActivity().finish();
						}
					}
				});
		/* 返回Fragment显示的View */
		return numberView;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

}
