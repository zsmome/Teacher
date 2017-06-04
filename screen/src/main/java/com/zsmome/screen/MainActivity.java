package com.zsmome.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button mBt = (Button) findViewById(R.id.start_screen_Saver);
				mBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
				Intent mService = new Intent(MainActivity.this, ScreenService.class);
				mService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startService(mService);
			}
		});
		
	}

	

}
