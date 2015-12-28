package com.example2.we123;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//声明变量
public class LoginModel extends Activity {
	private int year;
	private int month;
	private int day;
	private Calendar cal;
	private JSONObject json;
	public String TAG="login";
	 private String url="http://loginonother.sinaapp.com/api/Customers/";

//	 点击文字第三方注册
//	private TextView regist1;
	private ImageButton btn;
    private UMSocialService Controller;
	final int CODE=0*717;
	private Button button;
	private Button button1;
	private EditText et1;
	private EditText et2;
	private CheckBox cb;
	private SharedPreferences pref;
	private Editor editor;
	public String code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  
		super.onCreate(savedInstanceState);
		AppClose appState = (AppClose)this.getApplication();  
        appState.addActivity(this); 
		setContentView(R.layout.login);
//		为变量找到对应的R文件
//		 点击文字第三方注册
//		regist1=(TextView) findViewById(R.id.regist);
//		regist1.setClickable(true);
//		regist1.setFocusable(true);
		cal=Calendar.getInstance();
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH);
		day=cal.get(Calendar.DAY_OF_MONTH);
		btn=(ImageButton) findViewById(R.id.btn);
	    Controller = UMServiceFactory.getUMSocialService("com.umeng.login");
	    button =(Button) findViewById(R.id.button);
//		button1 =(Button) findViewById(R.id.button1);
		et1=(EditText) findViewById(R.id.name);
		et2=(EditText) findViewById(R.id.password);
		cb=(CheckBox) findViewById(R.id.cb);
		
//		regist1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
////				测试跳转
//				Bundle bundle=new Bundle();
////				bundle.putCharSequence("name", name);
////				bundle.putCharSequence("password", password);
//				Intent intent =new Intent(MainActivity.this,regist.class);
////				intent.putExtras(bundle);
//				startActivityForResult(intent, CODE);
//				
//			}
//		});
//		  创建Pref对象命名并取得Edit对象
		pref=getSharedPreferences("mylogin", MODE_PRIVATE);
		editor=pref.edit();
//		判断是否勾选checkBox并决定是否在edittext里面写入用户名密码
	Boolean ischeck=pref.getBoolean("ischecked", false);
	if(ischeck){
	if(pref.getInt("year",0)!=0){
		if(pref.getInt("year",0)-year!=0){
//			重新授权
			cb.setChecked(true);
			et1.setText("");
			et2.setText("");
		Toast.makeText(LoginModel.this, "授权过期，请重新输入用户名密码", Toast.LENGTH_LONG).show();
		
		}else {
			if((month-pref.getInt("month", 0))>=1){
				cb.setChecked(true);
				et1.setText("");
				et2.setText("");
				Toast.makeText(LoginModel.this, "授权过期，请重新输入用户名密码", Toast.LENGTH_LONG).show();
				
		}else{
			String name=pref.getString("userid", "");
			String password=pref.getString("password", "");
			et1.setText(name);
			et2.setText(password);
			cb.setChecked(true);
		}
		
		}
		
		
	}
	}
	   
	}
//设定按钮点击事件
	public void click(View view){
	switch(view.getId()){
	case R.id.button:
		
		String name =et1.getText().toString().trim();
		String password=et2.getText().toString().trim();
	   String lmd5="id:"+name+"pwd:"+password;
	   String loginInfo=new MD5(lmd5).md5(lmd5);
	   Toast.makeText(this, loginInfo, 1).show();
	

	     new LoginThread(LoginModel.this,loginInfo,url,TAG).show();
	       




		if(cb.isChecked()){
			editor.putInt("year",year);
			editor.putInt("month",month);
			editor.putInt("day",day);
			editor.putBoolean("ischecked", true);
			editor.putString("userid", name);
			editor.putString("password", password);
			editor.commit();
}else {
	        editor.putInt("year",0);
	        editor.putInt("month",0);
	        editor.putInt("day",0);
			editor.putBoolean("ischecked",false);
		    editor.remove(name);
		     editor.remove(password);
		    editor.commit();
			
		}
		
	break;

	
	   case R.id.btn:
		   new sinaLogin(this, Controller).getLogin();
		 
			
	break;	}
}




}

	

