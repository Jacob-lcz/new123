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
//��������
public class LoginModel extends Activity {
	private int year;
	private int month;
	private int day;
	private Calendar cal;
	private JSONObject json;
	public String TAG="login";
	 private String url="http://loginonother.sinaapp.com/api/Customers/";

//	 ������ֵ�����ע��
//	private TextView regist1;
	private ImageButton btn;
    private UMSocialService Controller;
	final int CODE=0*717;
	private Button button;
	private Button button1;
	private EditText et1;
	private EditText et2;
	private TextView forget,registuser;
//	private CheckBox cb;
	private SharedPreferences pref;
	private Editor editor;
	public String code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  
		super.onCreate(savedInstanceState);
		AppClose appState = (AppClose)this.getApplication();  
        appState.addActivity(this); 
		setContentView(R.layout.login);
		forget=(TextView) findViewById(R.id.forgetpwd);
		forget.setClickable(true);
		forget.setFocusable(true);
		registuser=(TextView) findViewById(R.id.registtouser);
		registuser.setClickable(true);
		registuser.setFocusable(true);
//		Ϊ�����ҵ���Ӧ��R�ļ�
//		 ������ֵ�����ע��
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

		et1=(EditText) findViewById(R.id.name);
		et2=(EditText) findViewById(R.id.password);
		registuser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginModel.this,RegistModel.class);
				startActivity(intent);
			}
		});
		forget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginModel.this,resetPwd.class);
				startActivity(intent);
			}
		});
//		cb=(CheckBox) findViewById(R.id.cb);
		
//		regist1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
////				������ת
//				Bundle bundle=new Bundle();
////				bundle.putCharSequence("name", name);
////				bundle.putCharSequence("password", password);
//				Intent intent =new Intent(MainActivity.this,regist.class);
////				intent.putExtras(bundle);
//				startActivityForResult(intent, CODE);
//				
//			}
//		});
//		  ����Pref����������ȡ��Edit����
//		pref=getSharedPreferences("mylogin", MODE_PRIVATE);
//		editor=pref.edit();
////		�ж��Ƿ�ѡcheckBox�������Ƿ���edittext����д���û�������
//	Boolean ischeck=pref.getBoolean("ischecked", false);
//	if(ischeck){
//	if(pref.getInt("year",0)!=0){
//		if(pref.getInt("year",0)-year!=0){
////			������Ȩ
//			cb.setChecked(true);
//			et1.setText("");
//			et2.setText("");
//		Toast.makeText(LoginModel.this, "��Ȩ���ڣ������������û�������", Toast.LENGTH_LONG).show();
//		
//		}else {
//			if((month-pref.getInt("month", 0))>=1){
//				cb.setChecked(true);
//				et1.setText("");
//				et2.setText("");
//				Toast.makeText(LoginModel.this, "��Ȩ���ڣ������������û�������", Toast.LENGTH_LONG).show();
//				
//		}else{
//			String name=pref.getString("userid", "");
//			String password=pref.getString("password", "");
//			et1.setText(name);
//			et2.setText(password);
//			cb.setChecked(true);
//		}
//		
//		}
//		
//		
//	}
//	}
//	   
	}
//�趨��ť����¼�
	public void click(View view){
	switch(view.getId()){
	case R.id.button:
		
		String name =et1.getText().toString().trim();
		String password=et2.getText().toString().trim();
	   String lmd5="id:"+name+"pwd:"+password;
	   String loginInfo=new MD5(lmd5).md5(lmd5);
	
	

	     new LoginThread(LoginModel.this,loginInfo,url,TAG).show();
	       




//		if(cb.isChecked()){
//			editor.putInt("year",year);
//			editor.putInt("month",month);
//			editor.putInt("day",day);
//			editor.putBoolean("ischecked", true);
//			editor.putString("userid", name);
//			editor.putString("password", password);
//			editor.commit();
//}else {
//	        editor.putInt("year",0);
//	        editor.putInt("month",0);
//	        editor.putInt("day",0);
//			editor.putBoolean("ischecked",false);
//		    editor.remove(name);
//		     editor.remove(password);
//		    editor.commit();
//			
//		}
		
	break;

	
	   case R.id.btn:
		   new sinaLogin(this, Controller).getLogin();
		 
			
	break;	}
}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}


}

	

