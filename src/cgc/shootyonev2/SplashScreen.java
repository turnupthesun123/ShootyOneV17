package cgc.shootyonev2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

//John McNaughton
//26/02/2015
//Splash Screen
public class SplashScreen extends Activity {

	//variable for timer
	//value = 5 seconds
	private static int SPLASH_TIME_OUT=5000;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(new Runnable() {
			
			//shows splash screen
			
			@Override
			public void run() {
			//method implemented when timer is complete	
				
				//uses intent to switch screens
				//uses SplashScreen as an 'anchor'
				//switches to main activity after 5 seconds
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				
				startActivity(i);
				
				//close activity
				finish();
				
				
			}
		}, SPLASH_TIME_OUT);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
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
}

