package com.wisdomleaf.codingtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.wisdomleaf.codingtest.R;
import androidx.appcompat.app.AppCompatActivity;
public class SplashActivity extends AppCompatActivity
{
	private static final String TAG = SplashActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.activity_splash);
		
		int SPLASH_DISPLAY_LENGTH = 5000;
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				Intent intent = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
			}
		},SPLASH_DISPLAY_LENGTH);
	}
	
	
}
