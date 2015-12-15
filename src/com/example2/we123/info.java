package com.example2.we123;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class info extends Activity {

	private TextView weiboname,location,fans,friends,weibonum;
	private ImageView img;
	String imguri;
	

		  @Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.my);
			img=(ImageView) findViewById(R.id.img);
			weiboname =(TextView) findViewById(R.id.weiboname);
			location=(TextView) findViewById(R.id.location);
		    fans=(TextView) findViewById(R.id.fans);
		   friends=(TextView) findViewById(R.id.friends);
		   weibonum=(TextView) findViewById(R.id.weibonum);
		   Handler handler=new Handler();
		   Intent intent1=getIntent();
		
			Bundle bundle=intent1.getExtras();
			weiboname.setText("用户名:"+bundle.getString("screen_name"));
			location.setText( "所在地："+bundle.getString ("location"));
			fans.setText( "粉丝数："+bundle.getString ("followers_count"));
			friends.setText( "已关注朋友数："+bundle.getString ("friends_count"));
			weibonum.setText( "已发微博条数："+bundle.getString ("statuses_count"));
			String imguri=bundle.getString("profile_image_url");
		
			new ImageLoader().showImageByThread(img,imguri);
		  
	}
//			public void run( String imgurl){
//				URL url;
//				try {
//					url = new URL(imgurl);	
//					HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//					conn.setReadTimeout(5000);
//					conn.setRequestMethod("GET");
//					InputStream in=conn.getInputStream();
//					final Bitmap bit=BitmapFactory.decodeStream(in);
//					img.setImageBitmap(bit);
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//			}
}
