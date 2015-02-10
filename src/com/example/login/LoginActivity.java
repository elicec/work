package com.example.login;

import com.elicec.dfss.HttpRequestHelper;
import com.elicec.dfss.MyApplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private ImageView loginImage;
	private TextView topText;
	private TextPaint tp;
	private Button loginbtn;
	private EditText username;
	private EditText password;
	private Thread mThread;
	private Drawable mIconPerson;
	private Drawable mIconLock;
	private ImageView validPic;
	
	
	
	private Handler mHandler = new Handler() {
        public void handleMessage (Message msg) {
            switch(msg.what) {
            case 1:
            	validPic.setImageBitmap((Bitmap) msg.obj);
                break;
 
            case 0:
                Toast.makeText(getApplication(), "获取图片失败", Toast.LENGTH_LONG).show();
                break;
            }
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		setContentView(R.layout.main);
		mIconPerson = getResources().getDrawable(R.drawable.txt_person_icon);
		mIconPerson.setBounds(5, 1, 60, 50);
		mIconLock = getResources().getDrawable(R.drawable.txt_lock_icon);
		mIconLock.setBounds(5, 1, 60, 50);

		username = (EditText) findViewById(R.id.username);
		username.setCompoundDrawables(mIconPerson, null, null, null);
		password = (EditText) findViewById(R.id.password);
		password.setCompoundDrawables(mIconLock, null, null, null);

		init();

	}

	public void init() {
		
		validPic=(ImageView) findViewById(R.id.validpic);
		topText = (TextView) findViewById(R.id.topname);
		topText.setTextColor(Color.MAGENTA);
		topText.setTextSize(24.0f);
		topText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);

		tp = topText.getPaint();

		tp.setFakeBoldText(true);
		loginImage = (ImageView) findViewById(R.id.loginImage);
		loginImage.setBackgroundDrawable(new BitmapDrawable(Util.toRoundBitmap(
				this, "putao2.jpg")));
		loginImage.getBackground().setAlpha(0);
		loginImage.setImageBitmap(Util.toRoundBitmap(this, "putao2.jpg"));

		loginbtn = (Button) findViewById(R.id.loginbtn);
		loginbtn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.getBackground().setAlpha(20);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.getBackground().setAlpha(255);

				}
				return true;
			}

		});
		//线程中获取网络图片
		mThread=new Thread(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				final Bitmap bm;
				final byte[] data;
				try {
					data=HttpRequestHelper.getImageFromNet(MyApplication.URLValidPic);
					bm=BitmapFactory.decodeByteArray(data, 0, data.length);
					mHandler.obtainMessage(1,bm).sendToTarget();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					mHandler.obtainMessage(0).sendToTarget();
					e.printStackTrace();
				}
				
				
			}
		});
		mThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
