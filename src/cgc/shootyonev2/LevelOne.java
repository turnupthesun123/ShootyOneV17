package cgc.shootyonev2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;





@SuppressLint("NewApi")
public class LevelOne extends Activity {

	GameView gameView;
	
	MyCountDownTimer myCountDownTimer = new MyCountDownTimer();
	
	GameLoopThread gameLoopThread;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

			
			Tutorial(null);
			
	
		setContentView(new GameView(this));
	

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_one, menu);
		return true;
	}

	//define fragment layout and functionality
			public void Tutorial(View v){
			
		final Dialog alertDialog = new Dialog(this);
		alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		alertDialog.setContentView(R.layout.activity_tutorial);
		
		alertDialog.getWindow().setBackgroundDrawable(
		new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		alertDialog.show();
		Button btnStart = (Button) alertDialog.findViewById(R.id.btnStart);
		
		

			btnStart.setOnClickListener(new View.OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				
					
					alertDialog.dismiss();
					
					myCountDownTimer.show(getFragmentManager(), "countdown_timer");
					
				}
			});
			
		
			
			
		}
	
	

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("GAME PAUSED");
		gameView.pause();
		
	}
			
	
	
	
		
}//end of class
