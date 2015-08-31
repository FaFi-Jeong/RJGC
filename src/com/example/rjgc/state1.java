//�ֻ�����״̬�л�
//����״̬
//��Ҫ������ʾδ�������δ������ϵ
//�ֻ�����ߣ��������ѷ���
package com.example.rjgc;

import android.os.Handler;
import android.content.Context;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;


public class state1 extends ImageView
{
	

	private static final int SCALE_REDUCE_INIT = 0;
	private static final int SCALING = 1;
	private static final int SCALE_ADD_INIT = 6;
	
	
	private int mWidth;
	/**
	 * �ؼ��ĸ�
	 */
	private int mHeight;
	/**
	 * �ؼ��Ŀ�1/2
	 */
	private int mCenterWidth;
	/**
	 * �ؼ��ĸ� 1/2
	 */
	private int mCenterHeight;
	/**
	 * ����һ�����ŵĳ���
	 */
	private float mMinScale = 0.85f;
	/**
	 * �����Ƿ����
	 */
	private boolean isFinish = true;

	public state1(Context context)
	{
		this(context, null);
	}

	public state1(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public state1(Context context, AttributeSet attrs, int defStyle)
	{
		super(context , attrs , defStyle);
	}

	/**
	 * ��Ҫ�ĳ�ʼ��
	 */
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		if (changed)
		{
			mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
			mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

			mCenterWidth = mWidth / 2;
			mCenterHeight = mHeight / 2;

			Drawable drawable = getDrawable();
			BitmapDrawable bd = (BitmapDrawable) drawable;
			bd.setAntiAlias(true);
		}
	}

	
	public boolean onTouchEvent(MotionEvent event)
	{
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			event.getX();
			event.getY();
			mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
			break;
		case MotionEvent.ACTION_UP:
			mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
			break;
		}
		return true;
	}

	/**
	 * �������ŵ�Handler
	 */
	private Handler mScaleHandler = new Handler()
	{
		private Matrix matrix = new Matrix();
		private int count = 0;
		private float s;
		/**
		 * �Ƿ��Ѿ������˵���¼�
		 */
		private boolean isClicked;

		public void handleMessage(android.os.Message msg)
		{
			matrix.set(getImageMatrix());
			switch (msg.what)
			{
			case SCALE_REDUCE_INIT:
				if (!isFinish)
				{
					mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
				} else
				{
					isFinish = false;
					count = 0;
					s = (float) Math.sqrt(Math.sqrt(mMinScale));
					beginScale(matrix, s);
					mScaleHandler.sendEmptyMessage(SCALING);
				}
				break;
			case SCALING:
				beginScale(matrix, s);
				if (count < 4)
				{
					mScaleHandler.sendEmptyMessage(SCALING);
				} else
				{
					isFinish = true;
					if (state1.this.mOnViewClickListener != null && !isClicked)
					{
						isClicked = true;
						state1.this.mOnViewClickListener.onViewClick(state1.this);
					} else
					{
						isClicked = false;
					}
				}
				count++;

				break;
			case 6:
				if (!isFinish)
				{
					mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
				} else
				{
					isFinish = false;
					count = 0;
					s = (float) Math.sqrt(Math.sqrt(1.0f / mMinScale));
					beginScale(matrix, s);
					mScaleHandler.sendEmptyMessage(SCALING);
				}
				break;
			}
		}


	};

	protected void sleep(int i)
	{
		try
		{
			Thread.sleep(i);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ����
	 * 
	 * @param matrix
	 * @param scale
	 */
	private synchronized void beginScale(Matrix matrix, float scale)
	{
		matrix.postScale(scale, scale, mCenterWidth, mCenterHeight);
		setImageMatrix(matrix);
	}

	
	/**
	 * �ص��ӿ�
	 */
	private OnViewClickListener mOnViewClickListener;

	public void setOnClickIntent(OnViewClickListener onViewClickListener)
	{
		this.mOnViewClickListener = onViewClickListener;
	}

	public interface OnViewClickListener
	{
		void onViewClick(state1 view);
	}

}


