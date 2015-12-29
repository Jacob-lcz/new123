//package com.example2.we123;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class info extends Activity {
//
//	private TextView username,email,lastlogintime,createtime,sex,birthday;
//	private ImageView img;
//	String imguri;
//	
//
//		  @Override
//		protected void onCreate(Bundle savedInstanceState) {
//			// TODO Auto-generated method stub
//			super.onCreate(savedInstanceState);
//			setContentView(R.layout.thirdlogin);
//			img=(ImageView) findViewById(R.id.img);
//			username=(TextView) findViewById(R.id.username);
//			email=(TextView) findViewById(R.id.email);
//			lastlogintime=(TextView) findViewById(R.id.lastlogintime);
//		    createtime=(TextView) findViewById(R.id.createtime);
//		   sex=(TextView) findViewById(R.id.sex);
////		   Handler handler=new Handler();
//		   Intent intent1=getIntent();
//		String info =intent1.getStringExtra("info");
//		try {
//			JSONObject infojson=new JSONObject(info);
//			username.setText("用户名:"+infojson.getString("name"));
//		
//			lastlogintime.setText( "上次登陆时间："+infojson.getString("lastlogintime"));
//			createtime.setText( "账户创建时间："+infojson.getString("createtime"));
//			sex.setText( "性别："+ infojson.getString("sex"));
//			if(infojson.get("email").toString().equals("")){
//				email.setText( "邮箱："+"null");
//			}else{
//				email.setText("邮箱："+infojson.getString("email"));
//			}
//			Toast.makeText(this,infojson.getString("picture"), Toast.LENGTH_SHORT).show();
//			if(infojson.get("picture").toString().equals("")){
////				img.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//				Toast.makeText(this, "1111111", Toast.LENGTH_SHORT).show();
//			}else{
//				
//			String imguri=infojson.getString("picture");
//			new ImageLoader().showImageByThread(img,imguri);
//			Toast.makeText(this, imguri, Toast.LENGTH_SHORT).show();
//			}
//			
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////			Bundle bundle=intent1.getExtras();
////			username.setText("用户名:"+bundle.getString("username"));
////			email.setText( "邮箱："+bundle.getString ("email"));
////			lastlogintime.setText( "上次登陆时间："+bundle.getString ("lastlogintime"));
////			createtime.setText( "账户创建时间："+bundle.getString ("createtime"));
////			sex.setText( "性别："+bundle.getString ("sex"));
////			String imguri=bundle.getString("image");
////		
////			
////		  
//	}
//
//}
