package com.example.fyp_offlineUsability;

import java.util.LinkedList;
import java.util.Queue;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.sensor.Connectivity;

public class SenderManager {

	Context context;
	Queue queue;
	private BroadcastReceiver receiver;
	
	public SenderManager(Context context){
		this.context = context;
		queue = new LinkedList<String>();
	}

	public void sendMessage(String message) {
		// TODO Auto-generated method stub

		if (Connectivity.isConnected(context)){
			Sender sender = new Sender();
			sender.execute(message);

			//while there is more item in queue
		}
		else{
			addToQueue(message);
			//add to queue of the message
			//register a broadcast reciever that connectivity changes
			IntentFilter filter = new IntentFilter();
			filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

			receiver = new Myreceiver();
			context.registerReceiver(receiver, filter);
		}
	}
	
	public class Myreceiver extends BroadcastReceiver{
		 
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
        	System.out.println("connectivity changed!");
        	if(Connectivity.isConnected(context)){
        		
        		while(!queue.isEmpty())
        		{
        			Sender sender = new Sender();
        			sender.execute(queue.remove().toString());
        		}
        	}
        }
         
    }

	private void addToQueue(String message) {
		// TODO Auto-generated method stub
		queue.add(message);

	}


}
