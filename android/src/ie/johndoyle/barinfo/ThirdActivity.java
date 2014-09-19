package ie.johndoyle.barinfo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

public class ThirdActivity extends ActionBarActivity {

	public final static String THIRD_QUESTION = "ie.johndoyle.barinfo.THIRD_QUESTION";
	public String userName = "";
	public String gender = "";
	public String birthday = "";
	public String firstQuestion = "";
	public String secondQuestion = "";
	public String thirdQuestion = "";
	
	private Chronometer chronometer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
        userName = intent.getStringExtra(LoginActivity.USER_NAME);
        gender = intent.getStringExtra(LoginActivity.GENDER);
        birthday = intent.getStringExtra(LoginActivity.BIRTHDAY);
        firstQuestion = intent.getStringExtra(FirstActivity.FIRST_QUESTION);
        secondQuestion = intent.getStringExtra(SecondActivity.SECOND_QUESTION);
        
		setContentView(R.layout.activity_third);
		
		chronometer = (Chronometer) findViewById(R.id.chronometer1);

	}

    public void onClick(View v) {
           switch(v.getId()) {
           case R.id.button2:
                  chronometer.setBase(SystemClock.elapsedRealtime());
                  chronometer.start();
                  break;
          case R.id.Button01:
        	     long miliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
        	     thirdQuestion = "" + miliseconds;
                 chronometer.stop();
                 break;
          }
   }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
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
	
	public void submit(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SubmitActivity.class);
		intent.putExtra(LoginActivity.USER_NAME, userName);
		intent.putExtra(LoginActivity.GENDER, gender);
		intent.putExtra(LoginActivity.BIRTHDAY, birthday);
		intent.putExtra(FirstActivity.FIRST_QUESTION , firstQuestion);
		intent.putExtra(SecondActivity.SECOND_QUESTION, secondQuestion);
		
		thirdQuestion = "" + chronometer.toString();
		intent.putExtra(THIRD_QUESTION, thirdQuestion);
		startActivity(intent);
	}
}
