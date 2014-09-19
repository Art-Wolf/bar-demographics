package ie.johndoyle.barinfo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

public class FirstActivity extends ActionBarActivity {

	public final static String FIRST_QUESTION = "ie.johndoyle.barinfo.FIRST_QUESTION";
	public String userName = "";
	public String gender = "";
	public String birthday = "";
	public String firstQuestion = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        Intent intent = getIntent();
        userName = intent.getStringExtra(LoginActivity.USER_NAME);
        gender = intent.getStringExtra(LoginActivity.GENDER);
        birthday = intent.getStringExtra(LoginActivity.BIRTHDAY);
        
		setContentView(R.layout.activity_first);

		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(10);     
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
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
	
	public void secondQuestion(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra(LoginActivity.USER_NAME, userName);
		intent.putExtra(LoginActivity.GENDER, gender);
		intent.putExtra(LoginActivity.BIRTHDAY, birthday);
		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
		
		firstQuestion = "" + numberPicker.getValue();
		intent.putExtra(FIRST_QUESTION, firstQuestion);
		startActivity(intent);
	}
}
