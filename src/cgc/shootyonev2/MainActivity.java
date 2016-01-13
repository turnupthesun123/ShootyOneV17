package cgc.shootyonev2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	
    Button btnPlay;
	Button btnExit;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			


		
		
		Button btnExit = (Button) findViewById(R.id.btnExit);
		
	    btnExit.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	
	        	finish();
	            System.exit(0);
	        }
	    });
		
		
		
	}

	public void OnClickGoToFragment(View V){
		
		final FragmentManager manager=getFragmentManager();//create an instance of fragment manager
		final FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction
		final JavaFragment jfrg = new JavaFragment();//create the fragment instance for the top fragment
	    final CFragment cfrg = new CFragment();//create the fragment instance for the middle fragment
	    final VisualBasicFragment vbfrg =new VisualBasicFragment();//create the fragment instance for the bottom fragment
	
			Button btnPlay = (Button) findViewById(R.id.btnPlay);
			Button btnExit = (Button) findViewById(R.id.btnExit);
			
			btnPlay.setVisibility(View.GONE);
			btnExit.setVisibility(View.GONE);
			
		    transaction.add(R.id.My_Container_1_ID, jfrg, "Frag_Top_tag");
		    transaction.add(R.id.My_Container_2_ID, cfrg, "Frag_Middle_tag");
		    transaction.add(R.id.My_Container_3_ID, vbfrg, "Frag_Bottom_tag");
		
		
		    transaction.commit();
		
			
		}
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

