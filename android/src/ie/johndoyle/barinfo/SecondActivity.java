package ie.johndoyle.barinfo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

public class SecondActivity extends ActionBarActivity {

	public final static String SECOND_QUESTION = "ie.johndoyle.barinfo.SECOND_QUESTION";
	public String userName = "";
	public String gender = "";
	public String birthday = "";
	public String firstQuestion = "";
	public String secondQuestion = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        Intent intent = getIntent();
        userName = intent.getStringExtra(LoginActivity.USER_NAME);
        gender = intent.getStringExtra(LoginActivity.GENDER);
        birthday = intent.getStringExtra(LoginActivity.BIRTHDAY);
        firstQuestion = intent.getStringExtra(FirstActivity.FIRST_QUESTION);
		setContentView(R.layout.activity_second);

		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(100);     
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
	
	public void thirdQuestion(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, ThirdActivity.class);
		intent.putExtra(LoginActivity.USER_NAME, userName);
		intent.putExtra(LoginActivity.GENDER, gender);
		intent.putExtra(LoginActivity.BIRTHDAY, birthday);
		intent.putExtra(FirstActivity.FIRST_QUESTION , firstQuestion);
		
		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
		
		secondQuestion = "" + numberPicker.getValue();
		intent.putExtra(SECOND_QUESTION, secondQuestion);
		startActivity(intent);
	}
}
