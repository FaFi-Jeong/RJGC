package com.example.rjgc;


import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;
import android.content.Intent;
import com.example.rjgc.state1;


public class AActivity extends Activity{

	
	state1 joke;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);

		joke = (state1) findViewById(R.id.c_joke);
		joke.setOnClickIntent(new state1.OnViewClickListener()
		{

			@Override
			public void onViewClick(state1 view)
			{
				Toast.makeText(AActivity.this, "测试用例1", 1000).show();
				Intent intent = new Intent();
				intent.setClass(AActivity.this, Call.class);//接口，读联系人
				startActivity(intent);
			}
		});
		
		joke = (state1) findViewById(R.id.c_idea);
		joke.setOnClickIntent(new state1.OnViewClickListener()
		{
			public void onViewClick(state1 view)
			{
				Toast.makeText(AActivity.this, "测试用例2", 1000).show();
				Intent intent = new Intent();
				intent.setClass(AActivity.this, msg.class);//接口,读短信
				startActivity(intent);
			}
		});
		
		
	
	}
	}
