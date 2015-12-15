package com.example2.we123;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.telecom.Connection;
import android.widget.ImageView;

public class ImageLoader {
	private ImageView mImageView;
	 private Handler mhandler=new Handler(){
		 @Override
		 public void handleMessage(android.os.Message msg) {
			 
			 super.handleMessage(msg);
			mImageView.setImageBitmap((Bitmap)msg.obj);
		 }
		 
	 };
 public void showImageByThread(ImageView imageView, final String url){
	mImageView=imageView;
	 new Thread(){
		 @Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			
			Bitmap bitmap=getBitmapFromURL(url);
			Message message=Message.obtain();
			message.obj=bitmap;
			mhandler.sendMessage(message);
		}
	 }.start();
 }
 public Bitmap getBitmapFromURL(String urlString){
	 Bitmap bitmap;
	 InputStream is = null;
	 try {
		URL url=new URL(urlString);
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		is=new BufferedInputStream(connection.getInputStream());
		bitmap=BitmapFactory.decodeStream(is);
		connection.disconnect();
		return bitmap;
	} catch (java.io.IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
	try {
		is.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	 	return null;
	 
 }
}
