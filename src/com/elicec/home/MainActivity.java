package com.elicec.home;


import com.example.login.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener{
	protected SlidingMenu mSlidingMenu;
	private ImageButton ivTitleBtnLeft;
	private Fragment mContent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSlidingMenu();
		setContentView(R.layout.activity_main);
		initView();
	}
	
	
	private void initView() {
		ivTitleBtnLeft = (ImageButton)this.findViewById(R.id.ivTitleBtnLeft);
		ivTitleBtnLeft.setOnClickListener(this);
		
	}

	private void initSlidingMenu() {
		mContent = new Fragment_yixin();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		.commit();
		
		setBehindContentView(R.layout.main_left_layout);
		FragmentTransaction mFragementTransaction = getSupportFragmentManager()
				.beginTransaction();
		Fragment mFrag = new LeftSlidingMenuFragment();
		mFragementTransaction.replace(R.id.main_left_fragment, mFrag);
		mFragementTransaction.commit();
		// customize the SlidingMenu
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
		mSlidingMenu.setFadeEnabled(true);
		mSlidingMenu.setBehindScrollScale(0.333f);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivTitleBtnLeft:
			mSlidingMenu.showMenu(true);
			break;
		default:
			break;
		}
		
	}
   
	 
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}
	
	
}
