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
 * ��ɫFragment,������ʾ��ͬ����ɫ
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

	/* ������Activity��ͨ�� */
	private Callbacks callbacks;

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
					"ColorFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
		}
		/* ��callbacks����ָ��Fragment����Activity */
		callbacks = (Callbacks) activity;
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

		textview = (TextView) numberView.findViewById(R.id.display);
		tipTv = (TextView) numberView.findViewById(R.id.tipTv);
		tipTv.setText("ѡ���ֵ���ɫ");
		/* ������� */
		int random = (int) (Math.random() * 10);
		/* ��ɫ��ID */
		int[] colorIds = new int[] { R.color.C0, R.color.C1, R.color.C2,
				R.color.C3, R.color.C4, R.color.C5, R.color.C6, R.color.C7,
				R.color.C8, R.color.C9 };
		/* ��Ϊ10����� */
		int a = 0;
		int random1 = 0;
		switch (random) {
		case 0:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C0)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 1:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C1)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 2:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C2)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 3:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C3)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 4:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C4)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 5:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C5)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 6:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C6)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 7:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C7)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 8:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C8)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;

		case 9:
			textview.setText("��");
			while ((a = colorIds[random1 = ((int) (Math.random() * 10))]) == R.color.C9)
				continue;
			textview.setTextColor(this.getResources().getColor(a));
			break;
		default:
			break;
		}

		/* ��������������ڿ����ı�����ȷ�� */
		int b = (int) (Math.random() * 2);
		left = (TextView) numberView.findViewById(R.id.left);
		right = (TextView) numberView.findViewById(R.id.right);

		if (b == 1) {
			isLeft = false; // �����ʾ��ȷ���ұ���ʾ����
			/* ��ʾ���廹��ͼƬ */
			int c = (int) (Math.random() * 2); // �����ȷ����ʾ���廹��ͼƬ�ı�ʶ

			/* ��ʾ������ */
			if (c == 1)
				switch (random1) {
				case 0:
					left.setText("��");
					break;
				case 1:
					left.setText("��");
					break;
				case 2:
					left.setText("��");
					break;
				case 3:
					left.setText("��");
					break;
				case 4:
					left.setText("��");
					break;
				case 5:
					left.setText("��");
					break;
				case 6:
					left.setText("��");
					break;
				case 7:
					left.setText("��");
					break;
				case 8:
					left.setText("��");
					break;
				case 9:
					left.setText("��");
					break;
				default:
					break;
				}
			else
				// ��ʾ������ɫ
				left.setBackgroundResource(a);

			int d = (int) (Math.random() * 2);// �ұߴ������ʾ���廹��ͼƬ�ı�ʶ

			/* ��ʾ������ */
			if (d == 1)
				switch (random) {
				case 0:
					right.setText("��");
					break;
				case 1:
					right.setText("��");
					break;
				case 2:
					right.setText("��");
					break;
				case 3:
					right.setText("��");
					break;
				case 4:
					right.setText("��");
					break;
				case 5:
					right.setText("��");
					break;
				case 6:
					right.setText("��");
					break;
				case 7:
					right.setText("��");
					break;
				case 8:
					right.setText("��");
					break;
				case 9:
					right.setText("��");
					break;
				default:
					break;
				}
			else
				// ��ʾ���Ǳ�����ɫ
				right.setBackgroundResource(colorIds[random]);
		} else {
			isLeft = true;// �����ʾ�����ұ���ʾ��ȷ
			/* ��ʾ���廹��ͼƬ */
			int c = (int) (Math.random() * 2); // ��ߴ������ʾ���廹��ͼƬ�ı�ʶ

			if (c == 1) // ��ʾ������
			{
				switch (random) {
				case 0:
					left.setText("��");
					break;
				case 1:
					left.setText("��");
					break;
				case 2:
					left.setText("��");
					break;
				case 3:
					left.setText("��");
					break;
				case 4:
					left.setText("��");
					break;
				case 5:
					left.setText("��");
					break;
				case 6:
					left.setText("��");
					break;
				case 7:
					left.setText("��");
					break;
				case 8:
					left.setText("��");
					break;
				case 9:
					left.setText("��");
					break;
				default:
					break;
				}
			} else
				// ��ʾ���Ǳ�����ɫ
				left.setBackgroundResource(colorIds[random]);

			int d = (int) (Math.random() * 2);// �ұ���ȷ����ʾ���廹��ͼƬ�ı�ʶ

			if (d == 1)// ��ʾ������
				switch (random1) {
				case 0:
					right.setText("��");
					break;
				case 1:
					right.setText("��");
					break;
				case 2:
					right.setText("��");
					break;
				case 3:
					right.setText("��");
					break;
				case 4:
					right.setText("��");
					break;
				case 5:
					right.setText("��");
					break;
				case 6:
					right.setText("��");
					break;
				case 7:
					right.setText("��");
					break;
				case 8:
					right.setText("��");
					break;
				case 9:
					right.setText("��");
					break;
				}
			else
				// ��ʾ���Ǳ�����ɫ
				right.setBackgroundResource(a);
		}

		/* ������𰸿ؼ����¼����� */
		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isLeft == true) {
					/* ��ѡ�����ת����Ϸ�������� */
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
					/* ��ѡ����ȷ��֪ͨActivity����Fragment */
					callbacks.successClicked();

				}
			}
		});

		/* �����Ҵ𰸿ؼ����¼����� */
		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isLeft == false) {
					/* ��ѡ�����ת����Ϸ�������� */
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
					/* ��ѡ����ȷ��֪ͨActivity����Fragment */
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
