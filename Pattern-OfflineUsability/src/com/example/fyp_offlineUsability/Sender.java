package com.example.fyp_offlineUsability;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;

public class Sender extends AsyncTask<String, Void, String> {

    long beforeTime;
    long afterTime;
    public MainActivity activity;
    protected int counter;

    public Sender(MainActivity a){
        activity = a;
    }

    public Sender(){
    }
    
    
    protected void onPreExecute() {
        beforeTime = System.currentTimeMillis();
    }

    @Override
    protected String doInBackground(String... params) {

    	HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        JSONObject json = new JSONObject();
        String message = params[0];
        try {
            HttpPost post = new HttpPost("http://chat-app-env-rqv96f7qqz.elasticbeanstalk.com/messages");
        	json.put("content", message);
            StringEntity se = new StringEntity( json.toString());  
            post.setEntity(se);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            
            response = client.execute(post);

            /*Checking response */
            if(response!=null){
                InputStream in = response.getEntity().getContent(); //Get the data in the entity
               
                // Wrap a BufferedReader around the InputStream
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                
                String line;
                StringBuilder total = new StringBuilder();
                // Read response until the end
                while ((line = rd.readLine()) != null) { 
                    total.append(line); 
                }
                
                // Return full string
                System.out.println("response =="+total.toString());
                return total.toString();
            }

        } catch(Exception e) {
            e.printStackTrace();
            return (e.toString());
            //createDialog("Error", "Cannot Estabilish Connection");
        }
        return "hehe";
    }

    protected void onPostExecute(String results) {

    }

}
