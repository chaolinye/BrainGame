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
 * ����Fragment��������ʾ��ͬ��������Ŀ
 * 
 * @author chaolin��ruofeng
 * 
 */
public class NumberFragment extends Fragment {
	/**
	 * Model�ڲ��࣬���������������Fragment����Ҫ������
	 * 
	 * @author chaolin
	 * 
	 */
	public class Model {
		/* ��һ�������� */
		public int a;
		/* ��������� */
		public int operator;
		/* �ڶ��������� */
		public int b;
		/* ������������ */
		public int c;
		/* ���ĸ������� */
		public int d;
		/* ��ȷ�� */
		public int result;
		/* ����� */
		public int errorResult;

		/**
		 * �����������
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

	/* ������Activity��ͨ�� */
	private Callbacks callbacks;
	/* ���� */
	private Model model;
	/* ����Ƿ�����ȷ�𰸣����ڸ����¼������¼� */
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
					"NumberFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
		}
		/* ��callbacks����ָ��Fragment����Activity */
		callbacks = (Callbacks) activity;
		model = new Model();
	}

	/**
	 * �˺������ص�View������Fragmeng����ʾ�����ݣ�Ҫ��ÿ�ε��øú���ʱ���ص�View��ʾ��ͬ����Ŀ
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		/* ��ȡҪ���ص�View */
		View numberView = inflater.inflate(R.layout.fragment_number, container,
				false);

		if (model != null) {
			/* ����model�ķ�������������� */
			model.createData();
			/* ���������������View�пؼ������ */
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
			/* ��������������ڿ����Ǳ�����ȷ�� */
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
		/* ������𰸿ؼ����¼����� */
		((TextView) numberView.findViewById(R.id.left))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (isLeft == true) {
							/* ��ѡ����ȷ��֪ͨActivity����Fragment */
							callbacks.successClicked();
						} else {
							/* ��ѡ�����ת����Ϸ�������� */
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
		/* �����Ҵ𰸿ؼ����¼����� */
		((TextView) numberView.findViewById(R.id.right))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (isLeft == false) {
							/* ��ѡ����ȷ��֪ͨActivity����Fragment */
							callbacks.successClicked();
						} else {
							/* ��ѡ�����ת����Ϸ�������� */
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
		/* ����Fragment��ʾ��View */
		return numberView;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

}
