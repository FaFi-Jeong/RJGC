package com.example.rjgc;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.app.Activity;
import android.widget.Toast;
import android.content.Intent;
import com.example.rjgc.state1;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.ViewPager;
import android.view.View.OnClickListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;


public class Welcome extends Activity implements OnClickListener,OnPageChangeListener{
		
		state1 BBQ;
	
		private ViewPager vp;
		private welcomeviewpage vpAdapter;
		private List<View> views;
		
		//����ͼƬ��Դ
		private final int[] pics = { R.drawable.welcome1,
				R.drawable.welcome2, R.drawable.welcome3,
				R.drawable.welcome4 };
		
		//�ײ�С��ͼƬ
		private ImageView[] dots ;
		
		//��¼��ǰѡ��λ��
		private int currentIndex;
		
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.welcome);
	        
	        views = new ArrayList<View>();
	       
	        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
	        		LinearLayout.LayoutParams.WRAP_CONTENT);
	        
	        //��ʼ������ͼƬ�б�
	        for(int i=0; i<pics.length; i++) {
	        	ImageView iv = new ImageView(this);
	        	iv.setLayoutParams(mParams);
	        	iv.setImageResource(pics[i]);
	        	views.add(iv);
	        }
	        vp = (ViewPager) findViewById(R.id.welcomeviewpage);
	        //��ʼ��Adapter
	        vpAdapter = new welcomeviewpage(views);
	        vp.setAdapter(vpAdapter);
	        //�󶨻ص�
	        vp.setOnPageChangeListener(this);
	        
	        //��ʼ���ײ�С��
	        initDots();
	        BBQ = (state1) findViewById(R.id.ttu);
			BBQ.setOnClickIntent(new state1.OnViewClickListener()
			{

				@Override
				public void onViewClick(state1 view)
				{
					Toast.makeText(Welcome.this, "��", 1000).show();
					Intent intent = new Intent();
					intent.setClass(Welcome.this, AActivity.class);
					startActivity(intent);
				}
			});
	        
	   }
	    
	    private void initDots() {
			LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

			dots = new ImageView[pics.length];

			//ѭ��ȡ��С��ͼƬ
			for (int i = 0; i < pics.length; i++) {
				dots[i] = (ImageView) ll.getChildAt(i);
				dots[i].setEnabled(true);//����Ϊ��ɫ
				dots[i].setOnClickListener(this);
				dots[i].setTag(i);//����λ��tag������ȡ���뵱ǰλ�ö�Ӧ
			}

			currentIndex = 0;
			dots[currentIndex].setEnabled(false);//����Ϊ��ɫ����ѡ��״̬
	    }
	    
	    /**
	     *���õ�ǰ������ҳ 
	     */
	    private void setCurView(int position)
	    {
			if (position < 0 || position >= pics.length) {
				return;
			}

			vp.setCurrentItem(position);
	    }

	    /**
	     *��ֻ��ǰ����С���ѡ�� 
	     */
	   
		private void setCurDot(int positon)
	    {
			if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
				return;
			}

			dots[positon].setEnabled(false);
			dots[currentIndex].setEnabled(true);

			currentIndex = positon;
	    }

	    //������״̬�ı�ʱ����
	
	

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
