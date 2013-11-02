package com.example.fyp_offlineUsability;

import com.example.sensor.Connectivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	SenderManager analyzer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showConnectivityStatus();
		analyzer = new SenderManager(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View v){
		EditText messageToSend = (EditText) findViewById(R.id.messageSendTxt);
		String message = messageToSend.getText().toString();
		analyzer.sendMessage(message);
		
		messageToSend.setText(null);
	}
	
	public void showConnectivityStatus(){

		TextView result = (TextView) findViewById(R.id.connectTextView);
		
		if(Connectivity.isConnected(getApplicationContext())){
			result.setText("on internet now, will directly send");
		}else{
			result.setText("not on internet now, will use queue and send pattern");
		}
		
	}
	
}
