package com.example2.we123;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.DateSorter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RegistModel extends Activity {
private EditText registpass,registmail,birth,name;
private Button btn;
private RadioGroup RG;
private int year;
private int month;
private int day;
private 	Calendar cal;
private JSONObject registinfo;
public String TAG="regist";
private String url="http://loginonother.sinaapp.com/api/Customers";

String sex;
//private 	RadioButton r;

	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppClose appState = (AppClose)this.getApplication();  
        appState.addActivity(this); 
		setContentView(R.layout.regist);
		RG=(RadioGroup) findViewById(R.id.RG);
	cal=Calendar.getInstance();
	year=cal.get(Calendar.YEAR);
	month=cal.get(Calendar.MONTH);
	day=cal.get(Calendar.DAY_OF_MONTH);
		btn=(Button) findViewById(R.id.btn);
		name=(EditText) findViewById(R.id.name);
		registpass=(EditText) findViewById(R.id.registpass);
		registmail=(EditText) findViewById(R.id.registmail);
		birth=(EditText) findViewById(R.id.birth);
	
//	RadioGroud获取性别信息
		RG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
			RadioButton  rad=(RadioButton) findViewById(checkedId);
          sex= rad.getText().toString();			
			}
		});
	
//		  出生日期 一获取焦点就执行（主要是在edittext里面不用这个监听的话会导致第一次点击不弹出选择对话框，’
//		对话框会等edittext获取到焦点点击才会弹出）
	birth.setOnFocusChangeListener(new OnFocusChangeListener() {	
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if(hasFocus){
			show()	;
			}else{	
			}
			}
		
	});
//注册按钮的监听事件，并设置正则表达式防止不规范的输入
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final	String email=registmail.getText().toString().trim();
				String birthday=birth.getText().toString().trim();
				String  username =name.getText().toString().trim();
				 String sex1=sex;
				// TODO Auto-generated method stub
//				 构造生成注册MD5（账号加密码）
//				String userid=registname.getText().toString().trim();
				String pass=registpass.getText().toString().trim();
				 String lmd5="email:"+email+"pwd:"+pass;
				   String registmd5=new MD5(lmd5).md5(lmd5);				   
				if (username.equals("") ) {
					 Toast.makeText(RegistModel.this, "昵称不能为空", Toast.LENGTH_SHORT)
					 .show();
//					 } else if(userid.length() < 4 || userid.length() > 20){ 
//						 Toast.makeText(RegistModel.this, "账号长度应在4～20位！", Toast.LENGTH_SHORT)
//						 .show();
					 
			 } else if (pass.length() < 6 || pass.length() > 15) { 
				 Toast.makeText(RegistModel.this, "密码长度应在6～15位！", Toast.LENGTH_SHORT)
				 .show();
			 }else if (!EmailFormat(email) ) {
					 Toast.makeText(RegistModel.this, "邮箱格式不正确", Toast.LENGTH_SHORT)
					 .show();
				
					 }else if (birthday.equals("")) { 
						 Toast.makeText(RegistModel.this, "请填写出生日期", Toast.LENGTH_SHORT)
						 .show();
					 } else if (sex1==null) { 
						 Toast.makeText(RegistModel.this, "请选择性别", Toast.LENGTH_SHORT)
						 .show();
					 }else {
						  JSONObject regist=new JSONObject();
						     try {
						    	 regist.put("name",username);
//                                  regist.put("userid",userid);
						    	 regist.put("id", registmd5);

								regist.put("email", email);
								regist.put("birthday", birthday);
								regist.put("sex",sex1);		
//								构造好json数据进行注册
								 new RegistThread(RegistModel.this,regist,url,TAG).show();				
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					 }			 
			}
		});
	
	
	}
//	  日期选择器的方法
	public void show(){
		
		new DatePickerDialog(this, new OnDateSetListener(){

			@Override
			public void onDateSet(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				
				birth.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
			}
			
			
		}, year, month,day).show();	
	}
	public static boolean EmailFormat(String email) {  
        String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";  
        boolean result = Pattern.matches(emailPattern, email);  
        return result;
}
	
//	这种在最外层的activity（就是说没有跳转到新的activity），在已完成就finish（），这样的话就不会出现按返回键跳回当前界面
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}