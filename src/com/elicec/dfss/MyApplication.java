package com.elicec.dfss;



import org.apache.http.client.CookieStore;

import android.app.Application;

public class MyApplication extends Application {
	
	private CookieStore mCookieStore;
	public static String URLLogin="http://wsyc.dfss.com.cn/DfssAjax.aspx" ;
	public static String URLValidPic="http://wsyc.dfss.com.cn/validpng.aspx?aa=3&page=lg";
	public static String URLUserInfo="http://wsyc.dfss.com.cn/DfssAjax.aspx";
	public static String PostDataPersonInfo="AjaxMethod=jbxx";
	public CookieStore getmCookieStore() {
		return mCookieStore;
	}

	public void setmCookieStore(CookieStore mCookieStore) {
		this.mCookieStore = mCookieStore;
	}

}
