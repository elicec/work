package com.elicec.dfss;

import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;

public class HttpRequestHelper {

	public static byte[] getImageFromNet(String urlImage) throws Exception {
		byte[] imagearray = null;
		URL url = new URL(urlImage);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		// conn.setRequestMethod("GET");
		conn.connect();
		if (conn.getResponseCode() == 200) {
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			int len = 0;
			InputStream inputStream = conn.getInputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				arrayOutputStream.write(buffer, 0, buffer.length);
			}
			imagearray = arrayOutputStream.toByteArray();
		}
		return imagearray;

	}
	public static String doHttpPostByApache(String urlStr, List<NameValuePair> params,CookieStore cookie)throws Exception{
		HttpPost httpPost=new HttpPost(urlStr);
		HttpEntity httpentity = new UrlEncodedFormEntity(params, "utf-8");
		httpPost.setEntity(httpentity);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.setCookieStore(cookie);
		HttpResponse httpResponse = httpclient.execute(httpPost);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
	    {
	     String strResult = EntityUtils.toString(httpResponse.getEntity());
	      return strResult;
	    } 
	  else 
	    {
	     return "Ê§°Ü";
	    }
	}

	public static String loginDfss(String urlStr,List<NameValuePair> params,Context context)throws Exception{
		String retStr;
		HttpPost httpPost=new HttpPost(urlStr);
		HttpEntity httpentity = new UrlEncodedFormEntity(params, "utf-8");
		httpPost.setEntity(httpentity);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse httpResponse = httpclient.execute(httpPost);
		MyApplication myAPP=(MyApplication)context.getApplicationContext();
		
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
	    {
	      retStr= EntityUtils.toString(httpResponse.getEntity());
	      myAPP.setmCookieStore(httpclient.getCookieStore());
	      return retStr;
	    } 
	  else 
	    {
	     return "Ê§°Ü";
	    }
	}
	public static String doHttpPostByAndroid(String urlStr, String postData) {
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "utf-8");
			DataOutputStream dop = new DataOutputStream(
					connection.getOutputStream());
			dop.writeBytes(postData);
			dop.flush();
			dop.close();

			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
			result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	public static String doHttpGetByandroid(String urlStr) {
		
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
			result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}
	
	public static String getPersonInfo(){
		
		String jsonString= doHttpPostByAndroid(MyApplication.URLUserInfo, MyApplication.PostDataPersonInfo);
		
	}
}
