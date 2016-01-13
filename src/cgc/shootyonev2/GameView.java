package cgc.shootyonev2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
 
@SuppressLint({ "WrongCall", "DrawAllocation" })
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	
////////////////////////////////////////////////public variables////////////////////////////////////////////////////
	public int score = 0;	
	public long lastClick;
	public boolean gameOver = false;
	Drawable d = getResources().getDrawable(R.drawable.level_one_space);// canvas background	
	public String sysOut = "****.out.println();";
	public String sysOutComplete = "System.out.println();";
	public boolean correct = false;
	public int lives = 6;
	public Rect groundRect = new Rect(0,1020,768,1160);
	public boolean win = false;
	MyCountDownTimer myCountdownTimer;
	Sprite sp;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////private variables///////////////////////////////////////////////////////
	public boolean isRunning = false;
	private GameLoopThread gameLoopThread;
	public Bitmap bmpExplosion;
	private int statementsCompleted = 0;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
										 
///////////////////////////////////////////////Arrays to hold sprites///////////////////////////////////////////////
		public List<TempSprite> temps = new ArrayList<TempSprite>();
		public List<SpecialSprites> spSprites = new ArrayList <SpecialSprites>();
		public List<Sprite> sprites = new ArrayList<Sprite>();
		public List<Sprite> toRemove = new ArrayList<Sprite>();
		public List<SpecialSprites> toRemoveSp = new ArrayList<SpecialSprites>();
		public List <Sprite> toRemoveIncorrect = new ArrayList<Sprite>();
		public List <Sprite> wrongAsteroid = new ArrayList<Sprite>();
		//private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	
//////////////////////////////////////////////Start of methods/////////////////////////////////////////////////////	
	
	
   
	// creates instance of game loop on game surface
	public GameView(final Context context) {
		super(context);

		gameLoopThread = new GameLoopThread(this);
		getHolder().addCallback(this);
		bmpExplosion = BitmapFactory.decodeResource(getResources(),
				R.drawable.explosion);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		

		boolean retry = true;
		gameLoopThread.setRunning(false);
		while (retry) {
			try {
				gameLoopThread.join();
				retry = false;
				gameLoopThread = null;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

			
		createSprites(1000);
		
	
		
		isRunning = true;

		gameLoopThread.setRunning(isRunning);

		gameLoopThread.start();
	
}
             
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
       
       //adds sprites to array list
       //uses random int to determine when special sprite appears
       private void createSprites(final int x) {
    	   
    	  
    	   new Thread(new Runnable(){
    		   public void run(){
    	   
    			   Random r = new Random();
    			   
    			   int appear =  r.nextInt(50);
    			   int incorrect = r.nextInt(50);	   
    					  
    			   
    			  for(int i = 0; i < x; i++){
    		   
					if (i == appear) {
						spSprites.add(CreateSpecialSprite(R.drawable.system_asteroid));
						
					}
					
					if(i == incorrect) {
						
						wrongAsteroid.add(createIncorrectSprite(R.drawable.for_asteroid));

					}else{
						sprites.add(createSprite(R.drawable.asteroid1));
					}
					
					
             try{
            	 Thread.sleep(2000); //Waits for 2 second
             }
             catch(InterruptedException e)
             {
             e.printStackTrace();
             }
         }
     }
    	   }).start();
    	   
    	  	   
	    
      }
       
 
       public void createSpecialSprite(){
    	   
    	   
    		 spSprites.add(CreateSpecialSprite(R.drawable.system_asteroid));
    	
    	   
    	   
       }//end of createSpecialSprite
       
     
       public void CreateIncorrectSprite(){
    	   
    	   
    	   
    	   wrongAsteroid.add(createIncorrectSprite(R.drawable.for_asteroid));
    	   
    	   
       }

       
       //sets bmp to drawable
       private Sprite createSprite(int resource) {
             Bitmap bmp = BitmapFactory.decodeResource(getResources(),resource);
             return new Sprite(this, bmp);
           
       }
       
       private Sprite createIncorrectSprite(int resource) {
           Bitmap bmp = BitmapFactory.decodeResource(getResources(),resource);
           return new Sprite(this, bmp);
         
     }
 
       private SpecialSprites CreateSpecialSprite(int resource){
    	   Bitmap sysBitmap = BitmapFactory.decodeResource(getResources(),resource);
		return new SpecialSprites(this, sysBitmap);
       }
       
       
       //sets canvas to Drawable background
       //uses loop to draw sprites in array onto canvas
       //draw text onto canvas   
	@Override
       protected void onDraw(Canvas canvas) {

    	   d.setBounds(getLeft(), getTop(), getRight(), getBottom());
    	   
    	   d.draw(canvas);
    	   
    		float middle = canvas.getWidth()/2;
    		float width = canvas.getWidth();
    	
    		
    		//drawing asteroids while gameOver = false
    	if(gameOver == false){	
    		
    		
    	   for (int i = temps.size() - 1; i >= 0; i--) {
               temps.get(i).onDraw(canvas);
    	   		}
    	   
				for (Sprite sprite : sprites) {
					sprite.onDraw(canvas);
				}

				for (SpecialSprites spSprite : spSprites) {
		
					spSprite.onDraw(canvas);
             }
             
				for(int i = wrongAsteroid.size()-1; i >=0; i--){
					
					wrongAsteroid.get(i).onDraw(canvas);
					
				}
					
			}
				
             Paint paint = new Paint();
             
             paint.setColor(Color.BLACK);
        	  canvas.drawRect(groundRect, paint);
			
			paint.setColor(getResources().getColor(R.color.electric_blue));
       	  	paint.setTextSize(30);
       	  	paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
       	  	canvas.drawText("SCORE: " + score, 10,  30, paint);
       	  	canvas.drawText("LEVEL ONE", (middle - 80), 30, paint);
       	  	canvas.drawText("LIVES: " + lives, (width - 120), 30, paint);
       	  	
       	 	
       	  	if (correct == false){
       	  		
       	  		canvas.drawText(sysOut, (middle - 140), 1100, paint);
       	  		
       	  	}
       	  	 if (correct == true){
       	  		canvas.drawText("Statement Correct!", (middle - 140), 1100, paint);
       	  		statementsCompleted = statementsCompleted + 1;
       	  		
       	  		
       	  		
       	  	}
       	  	 
       	  	 correct = false;
       	  	

		if(lives < 5 && lives > 2) 
		{

		paint.setColor(Color.YELLOW);
		paint.setTextSize(30);
		canvas.drawText("LIVES: " + lives, (width - 120), 30, paint);

		}
		else if(lives < 3) 
		{

		paint.setColor(Color.RED);
		paint.setTextSize(30);
		canvas.drawText("LIVES: " + lives, (width - 120), 30, paint);
		
		}	
    
       	  	
       	 if(gameOver == true){
       		 
       		paint.setTextSize(60);
       		 paint.setColor(getResources().getColor(R.color.electric_blue));
       		canvas.drawText("GAME OVER", 230, 480, paint);
       		paint.setTextSize(30);
       		canvas.drawText("SCORE: " + score, 230, 560, paint);
       		 canvas.drawText("STATEMENTS COMPLETED: " + statementsCompleted, 230, 640, paint);
       		
       	 }
     
       }
 
	

	
       
	// removes sprite using onCollision method if x,y coordinates match touch
	// event
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (System.currentTimeMillis() - lastClick > 300) {
			lastClick = System.currentTimeMillis();
			float x = event.getX();
			float y = event.getY();
			synchronized (getHolder()) {
				for (int i = sprites.size() - 1; i >= 0; i--) {
					Sprite sprite = sprites.get(i);
					if (sprite.isCollition(x, y)) {
						toRemove.add(sprite);
						sprites.remove(sprite);

						temps.add(new TempSprite(temps, this, x, y,
								bmpExplosion));
						score += 10;
						System.out.println(score);
						break;
					}
				}

				sprites.removeAll(toRemove);

				for (int i = spSprites.size() - 1; i >= 0; i--) {
					SpecialSprites spSprite = spSprites.get(i);
					if (spSprite.isCollition(x, y)) {
						toRemoveSp.add(spSprite);
						spSprites.remove(spSprite);
						temps.add(new TempSprite(temps, this, x, y,
								bmpExplosion));
						score += 50;
						correct = true;
						System.out.println(score);
						break;

					}// end of is collision(Special Sprites)
				}// end of special sprites For

				spSprites.removeAll(toRemoveSp);

				for (int i = wrongAsteroid.size() - 1; i >= 0; i--) {
					Sprite wrongAsteroids = wrongAsteroid.get(i);
					if (wrongAsteroids.isCollition(x, y)) {
						toRemoveIncorrect.add(wrongAsteroids);
						wrongAsteroid.remove(wrongAsteroids);
						temps.add(new TempSprite(temps, this, x, y,
								bmpExplosion));

						if (lives <= 1 && gameOver == false) {

							gameOver = true;
							lives = (lives - 1);
						} else {
							lives = (lives - 1);
						}

						System.out.println(score);
						break;

					}// end of is collision(Wrong Sprites)
				}// end of wrongSprites sprites For

				wrongAsteroid.removeAll(toRemoveSp);

			}

		}

		return true;
	}// end of touch event
             
         
	/**
	 * Pause the game loop
	 */
	public void pause() {
		System.out.println("GAME PAUSED");
		isRunning = false;
		boolean retry = true;
		while (retry) {
			try {
				gameLoopThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}

       
	
   	
}//end of class
