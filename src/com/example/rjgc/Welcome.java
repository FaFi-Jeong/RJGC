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
		
		//引导图片资源
		private final int[] pics = { R.drawable.welcome1,
				R.drawable.welcome2, R.drawable.welcome3,
				R.drawable.welcome4 };
		
		//底部小店图片
		private ImageView[] dots ;
		
		//记录当前选中位置
		private int currentIndex;
		
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.welcome);
	        
	        views = new ArrayList<View>();
	       
	        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
	        		LinearLayout.LayoutParams.WRAP_CONTENT);
	        
	        //初始化引导图片列表
	        for(int i=0; i<pics.length; i++) {
	        	ImageView iv = new ImageView(this);
	        	iv.setLayoutParams(mParams);
	        	iv.setImageResource(pics[i]);
	        	views.add(iv);
	        }
	        vp = (ViewPager) findViewById(R.id.welcomeviewpage);
	        //初始化Adapter
	        vpAdapter = new welcomeviewpage(views);
	        vp.setAdapter(vpAdapter);
	        //绑定回调
	        vp.setOnPageChangeListener(this);
	        
	        //初始化底部小点
	        initDots();
	        BBQ = (state1) findViewById(R.id.ttu);
			BBQ.setOnClickIntent(new state1.OnViewClickListener()
			{

				@Override
				public void onViewClick(state1 view)
				{
					Toast.makeText(Welcome.this, "测", 1000).show();
					Intent intent = new Intent();
					intent.setClass(Welcome.this, AActivity.class);
					startActivity(intent);
				}
			});
	        
	   }
	    
	    private void initDots() {
			LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

			dots = new ImageView[pics.length];

			//循环取得小点图片
			for (int i = 0; i < pics.length; i++) {
				dots[i] = (ImageView) ll.getChildAt(i);
				dots[i].setEnabled(true);//都设为灰色
				dots[i].setOnClickListener(this);
				dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
			}

			currentIndex = 0;
			dots[currentIndex].setEnabled(false);//设置为白色，即选中状态
	    }
	    
	    /**
	     *设置当前的引导页 
	     */
	    private void setCurView(int position)
	    {
			if (position < 0 || position >= pics.length) {
				return;
			}

			vp.setCurrentItem(position);
	    }

	    /**
	     *这只当前引导小点的选中 
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

	    //当滑动状态改变时调用
	
	

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
