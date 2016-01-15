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
	public String TAG="login";
	 private String url="http://loginonother.sinaapp.com/api/Customers/";
    private UMSocialService Controller;
	final int CODE=0*717;
	private Button button;
	private Button button1;
	private EditText et1;
	private EditText et2;
	private TextView forget,registuser;
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
	    Controller = UMServiceFactory.getUMSocialService("com.umeng.login");
	    button =(Button) findViewById(R.id.button);
		et1=(EditText) findViewById(R.id.name);
		et2=(EditText) findViewById(R.id.password);
		
	/*��½�����ע���������������¼����ڵ�½���һ��ע����ת�ӿ���Ҫ�ǿ��ǵ�ͬһ̨�ֻ��ĵڶ���
		�û���ע���˻����Ҳ�����������Ϊע�Ṧ�ܼ���ֻ�����ڳ�ʼ����ʱ��һ�������û��Ļ���Ҳ����˵�ڳ�ʼ��������ݵ�½������ע�ᵼ��
		�Ѿ��������û�ע�����û��ˣ������ڵ�½�����ṩһ���ӿڡ�*/
		
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
	}
	
//�趨��ť����¼�
	public void click(View view){
	switch(view.getId()){
//	���ص�½����
	case R.id.button:	
		String name =et1.getText().toString().trim();
		String password=et2.getText().toString().trim();
	   String lmd5="email:"+name+"pwd:"+password;
	   String loginInfo=new MD5(lmd5).md5(lmd5);
	     new LoginThread(LoginModel.this,loginInfo,url,TAG).show();
	break;
//	�������˵�½����
	   case R.id.btn:
		   new sinaLogin(this, Controller).getLogin();		
	break;	}
}

}

	

