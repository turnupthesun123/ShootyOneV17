package cgc.shootyonev2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyCountDownTimer extends DialogFragment{
	
	private TextView timer;
	private long time = 4000;
	private long INTERVAL = 1000;
	public MyCountDownTimer(){};
	boolean countDownFinished = false;
	LevelOne LevelOne;
	Context gameView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.countdown_timer,container);
	    timer = (TextView)view.findViewById(R.id.fragment_timer);
	    //makes frame transparent
	    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	    return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    CountDownTimer gameTimer = new CountDownTimer(time, INTERVAL) {
	        @Override
	        public void onTick(long l) {
	            timer.setText(""+((int)Math.round(l/1000.0)-1));
	      
	          
	        }

	        @Override
	        public void onFinish() {
	        	
	        	
		        	
	        		   dismiss();
		     
	        		
	        }
	    };
	    gameTimer.start();
	    
	}
	
	
	
}//end of class