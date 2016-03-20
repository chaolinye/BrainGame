package com.example.reversegame;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 颜色Fragment,用于显示不同的颜色
 * 
 * @author wenzi,shuang
 * 
 */
public class ColorFragment extends Fragment {
	/**
	 * 
	 * 
	 * @author wenzi,shuang
	 */
	private TextView textview;
	private TextView left;
	private TextView right;
	private TextView tipTv;

	/* 用于与Activity的通信 */
	private Callbacks callbacks;

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
					"ColorFragment所在的Activity必须实现Callbacks接口!");
		}
		/* 把callbacks属性指向Fragment所在Activity */
		callbacks = (Callbacks) activity;
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

		textview = (TextView) numberView.findViewById(R.id.display);
		tipTv = (TextView) numberView.findViewById(R.id.tipTv);
		tipTv.setText("选择汉字的颜色");
		/* 随机数据 */
		int random = (int) (Math.random() * 10);
		/* 颜色的ID */
		int[] colorIds = new int[] { R.color.C0, R.color.C1, R.color.C2,
				R.color.C3, R.color.C4, R.color.C5, R.color.C6, R.color.C7,
				R.color.C8, R.color.C9 };
		/* 分为10种情况 */
		int a = 0;
		int random1 = 0;
		switch (random) {
		case 0:
			textview.setText("红");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C0)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 1:
			textview.setText("橙");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C1)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 2:
			textview.setText("黄");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C2)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 3:
			textview.setText("绿");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C3)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 4:
			textview.setText("蓝");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C4)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 5:
			textview.setText("紫");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C5)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 6:
			textview.setText("粉");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C6)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 7:
			textview.setText("棕");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C7)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 8:
			textview.setText("青");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C8)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 9:
			textview.setText("灰");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C9)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;
		default:
			break;
		}

		/* 生成随机数，用于控制哪边是正确答案 */
		int b = (int) (Math.random() * 2);
		left = (TextView) numberView.findViewById(R.id.left);
		right = (TextView) numberView.findViewById(R.id.right);

		if (b == 1) {
			isLeft = false; // 左边显示正确，右边显示错误
			/* 显示字体还是图片 */
			int c = (int) (Math.random() * 2); // 左边正确答案显示字体还是图片的标识

			/* 显示的是字 */
			if (c == 1)
				switch (random1) {
				case 0:
					left.setText("红");
					break;
				case 1:
					left.setText("橙");
					break;
				case 2:
					left.setText("黄");
					break;
				case 3:
					left.setText("绿");
					break;
				case 4:
					left.setText("蓝");
					break;
				case 5:
					left.setText("紫");
					break;
				case 6:
					left.setText("粉");
					break;
				case 7:
					left.setText("棕");
					break;
				case 8:
					left.setText("青");
					break;
				case 9:
					left.setText("灰");
					break;
				default:
					break;
				}
			else
				// 显示背景颜色
				left.setBackgroundResource(a);

			int d = (int) (Math.random() * 2);// 右边错误答案显示字体还是图片的标识

			/* 显示的是字 */
			if (d == 1)
				switch (random) {
				case 0:
					right.setText("红");
					break;
				case 1:
					right.setText("橙");
					break;
				case 2:
					right.setText("黄");
					break;
				case 3:
					right.setText("绿");
					break;
				case 4:
					right.setText("蓝");
					break;
				case 5:
					right.setText("紫");
					break;
				case 6:
					right.setText("粉");
					break;
				case 7:
					right.setText("棕");
					break;
				case 8:
					right.setText("青");
					break;
				case 9:
					right.setText("灰");
					break;
				default:
					break;
				}
			else
				// 显示的是背景颜色
				right.setBackgroundResource(colorIds[random]);
		} else {
			isLeft = true;// 左边显示错误，右边显示正确
			/* 显示字体还是图片 */
			int c = (int) (Math.random() * 2); // 左边错误答案显示字体还是图片的标识

			if (c == 1) // 显示的是字
			{
				switch (random) {
				case 0:
					left.setText("红");
					break;
				case 1:
					left.setText("橙");
					break;
				case 2:
					left.setText("黄");
					break;
				case 3:
					left.setText("绿");
					break;
				case 4:
					left.setText("蓝");
					break;
				case 5:
					left.setText("紫");
					break;
				case 6:
					left.setText("粉");
					break;
				case 7:
					left.setText("棕");
					break;
				case 8:
					left.setText("青");
					break;
				case 9:
					left.setText("灰");
					break;
				default:
					break;
				}
			} else
				// 显示的是背景颜色
				left.setBackgroundResource(colorIds[random]);

			int d = (int) (Math.random() * 2);// 右边正确答案显示字体还是图片的标识

			if (d == 1)// 显示的是字
				switch (random1) {
				case 0:
					right.setText("红");
					break;
				case 1:
					right.setText("橙");
					break;
				case 2:
					right.setText("黄");
					break;
				case 3:
					right.setText("绿");
					break;
				case 4:
					right.setText("蓝");
					break;
				case 5:
					right.setText("紫");
					break;
				case 6:
					right.setText("粉");
					break;
				case 7:
					right.setText("棕");
					break;
				case 8:
					right.setText("青");
					break;
				case 9:
					right.setText("灰");
					break;
				}
			else
				// 显示的是背景颜色
				right.setBackgroundResource(a);
		}

		/* 设置左答案控件的事件监听 */
		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isLeft == true) {
					/* 答案选择错误，转到游戏结束界面 */
					if (GameData.isPlaySounds) {
						MediaPlayer mPlayer = MediaPlayer.create(getActivity(),
								R.raw.alert);
						mPlayer.start();
					}
					Intent intent = new Intent(getActivity(),
							OverActivity.class);
					startActivity(intent);
					getActivity().finish();

				} else {
					/* 答案选择正确，通知Activity更换Fragment */
					callbacks.successClicked();

				}
			}
		});

		/* 设置右答案控件的事件监听 */
		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isLeft == false) {
					/* 答案选择错误，转到游戏结束界面 */
					if (GameData.isPlaySounds) {
						MediaPlayer mPlayer = MediaPlayer.create(getActivity(),
								R.raw.alert);
						mPlayer.start();
					}
					Intent intent = new Intent(getActivity(),
							OverActivity.class);
					startActivity(intent);
					getActivity().finish();
				} else {
					/* 答案选择正确，通知Activity更换Fragment */
					callbacks.successClicked();

				}
			}
		});

		return numberView;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
}
