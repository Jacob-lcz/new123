package com.example2.we123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class regist extends Activity {
private TextView name,pass;
private Button btn;

	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reginfo);
		name =(TextView) findViewById(R.id.textname);
		pass=(TextView) findViewById(R.id.textpass);
		btn=(Button) findViewById(R.id.btn);
		final Intent intent1=getIntent();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(0*717,intent1);
				finish();
			}
		});
	
		Bundle bundle=intent1.getExtras();
		name.setText("”√ªß√˚:"+bundle.getString("name"));
		pass.setText( "√‹¬Î:"+bundle.getString ("password"));
	}
	
}
