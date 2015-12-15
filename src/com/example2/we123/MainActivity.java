package com.example2.we123;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.utils.Log;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String ACTION1="MY";
	public static final String CAT="CAT";
	private ImageButton btn;
    private UMSocialService mController;
	final int CODE=0*717;
	private Button button;
	private Button button1;
	private EditText et1;
	private EditText et2;
	private CheckBox cb;
	private SharedPreferences pref;
	private Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(ImageButton) findViewById(R.id.btn);
	    mController = UMServiceFactory.getUMSocialService("com.umeng.login");
	    button =(Button) findViewById(R.id.button);
		button1 =(Button) findViewById(R.id.button1);
		et1=(EditText) findViewById(R.id.name);
		et2=(EditText) findViewById(R.id.password);
		cb=(CheckBox) findViewById(R.id.cb);
		pref=getSharedPreferences("mylogin", MODE_PRIVATE);
		editor=pref.edit();
		String name1 =pref.getString("name", "");
		if(name1==null){
			cb.setChecked(false);
			
		}else{
		cb.setChecked(true);
			et1.setText(name1);
		}
	}
	 
		
		

			

  
public void click(View view){
	switch(view.getId()){
	case R.id.button:
		String name =et1.getText().toString().trim();
		String password=et2.getText().toString().trim();
	String name1=pref.getString("name'", "");
	String password1=pref.getString(password, "");
	if("admin".equals(name)&&"123456".equals(password)){
//	if("name".equals(name1)&&"password".equals(password1)){
		Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
		if(cb.isChecked()){
			editor.putString("name", name);
			editor.putString("password", password);
			editor.commit();
		Bundle bundle=new Bundle();
		bundle.putCharSequence("name", name);
		bundle.putCharSequence("password", password);
		Intent intent =new Intent(this,regist.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, CODE);
			
			
		}else {
			
//			editor.remove(name);
//			editor.remove(password);
//			editor.commit();
			
		}
		
	}else{
		
		Toast.makeText(MainActivity.this, "账户名密码错误", Toast.LENGTH_SHORT).show();
		
	}break;
	case R.id.button1:
		System.exit(0); 
		break;

	
	   case R.id.btn:
		  
			mController.doOauthVerify(MainActivity.this, SHARE_MEDIA.SINA,new UMAuthListener() {
	            @Override
	            public void onError(SocializeException e, SHARE_MEDIA platform) {
	            }
	            @Override
	            public void onComplete(Bundle value, SHARE_MEDIA platform) {
	                if (value != null && !TextUtils.isEmpty(value.getString("uid"))) {
	                    Toast.makeText(MainActivity.this, "授权成功.",                      Toast.LENGTH_SHORT).show();
	                    getUserInfo();
	              
	              
	                } else {
	                    Toast.makeText(MainActivity.this, "授权失败",                       Toast.LENGTH_SHORT).show();
	                }
	            }
	            @Override
	            public void onCancel(SHARE_MEDIA platform) {}
	            @Override
	            public void onStart(SHARE_MEDIA platform) {}
	});

	break;	}


		   
	
	
		
	
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
super.onActivityResult(requestCode, resultCode, data);
  if(resultCode==CODE&&requestCode==CODE){
	  et1.setText("");
	  et2.setText("");
  }

}

public void getUserInfo(){	
	
	 final Bundle bundle1=new Bundle();
   mController.getPlatformInfo(MainActivity.this, SHARE_MEDIA.SINA, new UMDataListener() {
  
	   @Override 
    public void onStart() {
        Toast.makeText(MainActivity.this, "获取平台数据开始...", Toast.LENGTH_SHORT).show();
    }                                              
    @Override
        public void onComplete(int status, Map<String, Object> info) {
            if(status == 200 && info != null){
                StringBuilder sb = new StringBuilder();
            	
                Set<String> keys = info.keySet();
             
                for(String key : keys){
//                   sb.append(key+"="+info.get(key).toString()+"\r\n");
                   bundle1.putString(key, info.get(key).toString());
                  
            }   	
            
                  info(bundle1);
                
             
                }else{
               Log.d("TestData","发生错误："+status);
           }
            

        }
    
   public void info(Bundle bundle){
	  Intent intent=new Intent(MainActivity.this,info.class);
	  intent.putExtras(bundle);
	  startActivity(intent);
	
	   
	   
   }
    	
    
});


	}

}

	

