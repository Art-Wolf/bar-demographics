package ie.johndoyle.barinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SubmitActivity extends ActionBarActivity implements LocationListener {

	private LocationManager locationManager;
	public String userName = "";
	public String gender = "";
	public String birthday = "";
	public String firstQuestion = "";
	public String secondQuestion = "";
	public String thirdQuestion = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
        userName = intent.getStringExtra(LoginActivity.USER_NAME);
        gender = intent.getStringExtra(LoginActivity.GENDER);
        birthday = intent.getStringExtra(LoginActivity.BIRTHDAY);
        firstQuestion = intent.getStringExtra(FirstActivity.FIRST_QUESTION);
        secondQuestion = intent.getStringExtra(SecondActivity.SECOND_QUESTION);
        thirdQuestion = intent.getStringExtra(ThirdActivity.THIRD_QUESTION);
        
		setContentView(R.layout.activity_submit);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
				3000,   // 3 sec
				10, this);
		
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);    
		Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);  

		String longitude = "" + location.getLongitude();
		String latitude = "" + location.getLatitude();
		
		try {
			AsyncTaskRunner runner = new AsyncTaskRunner();
			runner.execute(userName, gender, birthday, firstQuestion, secondQuestion, thirdQuestion, longitude, latitude);
		} finally {}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	class AsyncTaskRunner extends AsyncTask<String, String, String> {


		private String resp;

		protected String doInBackground(String... params) {

			JSONObject json = new JSONObject();

			try {
				json.put("name", params[0]);
				json.put("gender", params[1]);
				json.put("birthday", params[2]);
				json.put("bartender_count", params[3]);
				json.put("patron_count", params[4]);
				json.put("duration", params[5]);
				json.put("longitude", params[6]);
				json.put("latitude", params[7]);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost request = new HttpPost("http://bar-ut.herokuapp.com/api/v1/stat");
			request.setHeader("Content-Type", "application/json");
			
			
			try {
				request.setEntity(new StringEntity(json.toString()));
				
				HttpResponse response = httpClient.execute(request);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    response.getEntity().getContent(), "UTF-8"));
	            String sResponse;
	            StringBuilder s = new StringBuilder();
	 
	            while ((sResponse = reader.readLine()) != null) {
	                s = s.append(sResponse);
	            }
	            
				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			return resp;
		}

		protected void onPostExecute(String result) {
		}


		protected void onPreExecute() {
			// Things to be done before execution of long running operation. For
			// example showing ProgessDialog
		}

		protected void onProgressUpdate(String... text) {
			// Things to be done while execution of long running operation is in
			// progress. For example updating ProgessDialog
		}
	}
}
