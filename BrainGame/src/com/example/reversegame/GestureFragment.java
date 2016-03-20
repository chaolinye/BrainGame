package com.example.reversegame;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GestureFragment extends Fragment implements OnGestureListener {

	final int FLIP_UP = 80;// ���ϻ���
	final int FLIP_DOWN = 80;// ���»���
	final int FLIP_LEFT = 80;// ���󻬶�
	final int FLIP_RIGHT = 80;// ���һ���

	private int currentImage;
	private GestureDetector mGestureDetector;// ���ȶ���һ�����Ƽ���������������������������ʱ��
	GameActivity.MyOnTouchListener myOnTouchListener; // ����һ�������¼��ļ�����
	private Callbacks callbacks;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"NumberFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
		}
		/* ��callbacks����ָ��Fragment����Activity */
		callbacks = (Callbacks) activity;
		// �������Ƽ����
		mGestureDetector = new GestureDetector(getActivity(), this);

		// ʵ��MyOnTouchListener()�е�onTouch�������Ϳ�����fragment�ϼ��������¼�
		myOnTouchListener = new GameActivity.MyOnTouchListener() {

			@Override
			public boolean onTouch(MotionEvent ev) {
				return mGestureDetector.onTouchEvent(ev);

			}
		};
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		View imageView = inflater.inflate(R.layout.fragment_image, container,
				false);
		ImageView image = (ImageView) imageView.findViewById(R.id.d_image);

		// ��������������ҷ����ͼƬ
		currentImage = createRandomImage();
		image.setImageResource(currentImage);

		((GameActivity) getActivity())
				.registerMyOnTouchListener(myOnTouchListener);

		return imageView;
	}

	// ������������������ҷ����ͼƬ�ĺ���
	public int createRandomImage() {
		int[] images = { R.drawable.left01, R.drawable.right01,
				R.drawable.up01, R.drawable.down01 };
		int number = (int) (Math.random() * images.length);
		return images[number];
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if (arg0.getX() - arg1.getX() > FLIP_LEFT)// ���󻬶�
		{
			((GameActivity) getActivity())
					.unregisterMyOnTouchListener(myOnTouchListener);
			if (currentImage == R.drawable.right01) {
				callbacks.successClicked();
				return true;
			} else {
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(getActivity(),
							R.raw.alert);
					mPlayer.start();
				}
				Intent intent = new Intent(getActivity(), OverActivity.class);
				startActivity(intent);
				getActivity().finish();
				return true;
			}
		} else if (arg1.getX() - arg0.getX() > FLIP_RIGHT)// ���һ���
		{
			((GameActivity) getActivity())
					.unregisterMyOnTouchListener(myOnTouchListener);

			if (currentImage == R.drawable.left01) {
				callbacks.successClicked();
				return true;
			} else {
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(getActivity(),
							R.raw.alert);
					mPlayer.start();
				}
				Intent intent = new Intent(getActivity(), OverActivity.class);
				startActivity(intent);
				getActivity().finish();
				return true;
			}
		} else if (arg0.getY() - arg1.getY() > FLIP_DOWN)// ���ϻ���
		{
			((GameActivity) getActivity())
					.unregisterMyOnTouchListener(myOnTouchListener);
			if (currentImage == R.drawable.down01) {
				callbacks.successClicked();
				return true;
			} else {
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(getActivity(),
							R.raw.alert);
					mPlayer.start();
				}
				Intent intent = new Intent(getActivity(), OverActivity.class);
				startActivity(intent);
				getActivity().finish();
				return true;
			}
		} else if (arg1.getY() - arg0.getY() > FLIP_UP)// ���»���
		{
			((GameActivity) getActivity())
					.unregisterMyOnTouchListener(myOnTouchListener);
			if (currentImage == R.drawable.up01) {
				callbacks.successClicked();
				return true;
			} else {
				if (GameData.isPlaySounds) {
					MediaPlayer mPlayer = MediaPlayer.create(getActivity(),
							R.raw.alert);
					mPlayer.start();
				}
				Intent intent = new Intent(getActivity(), OverActivity.class);
				startActivity(intent);
				getActivity().finish();
				return true;
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

}
