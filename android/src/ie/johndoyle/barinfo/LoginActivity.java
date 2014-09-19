package ie.johndoyle.barinfo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.*;
import com.facebook.model.*;
import android.widget.TextView;
import android.content.Intent;

public class LoginActivity extends ActionBarActivity {

	public final static String USER_NAME = "ie.johndoyle.barinfo.USER_NAME";
	public final static String GENDER = "ie.johndoyle.barinfo.GENDER";
	public final static String BIRTHDAY = "ie.johndoyle.barinfo.BIRTHDAY";
	public String userName = "";
	public String gender = "";
	public String birthday = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// start Facebook Login
	    Session.openActiveSession(this, true, new Session.StatusCallback() {

	      // callback when session changes state
	      @Override
	      public void call(Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {

	          // make request to the /me API
	          Request.newMeRequest(session, new Request.GraphUserCallback() {

	            // callback after Graph API response with user object
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	              if (user != null) {
	                TextView welcome = (TextView) findViewById(R.id.welcome);
	                welcome.setText("Hello " + user.getFirstName() + "!");
	                userName = user.getName();
	                gender = user.asMap().get("gender").toString();
	                birthday = user.getBirthday();
	              }
	            }
	          }).executeAsync();
	        }
	      }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
	public void firstQuestion(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, FirstActivity.class);
		intent.putExtra(USER_NAME, userName);
		intent.putExtra(GENDER, gender);
		intent.putExtra(BIRTHDAY, birthday);
		startActivity(intent);
	}
}
