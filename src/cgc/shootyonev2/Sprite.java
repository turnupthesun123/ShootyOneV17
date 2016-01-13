package cgc.shootyonev2;


import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

@SuppressLint("DrawAllocation")
public class Sprite {
	// direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2, 4 };
	private int x, y;
	private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 5;
	private static final int MAX_SPEED = 15;
	private GameView gameView;
	private Bitmap bmp;
	public int ySpeed, xSpeed;
	private int height;
	private int width;
	private int currentFrame = 0;
	public boolean hit = false;
	
	
	
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
		
	}
	 
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
	public Sprite(GameView gameView, Bitmap bmp) {
		this.gameView = gameView;
		this.bmp = bmp;
		//finds the width and height of rectangle containing sprite
		 this.width = bmp.getWidth() / BMP_COLUMNS;
         this.height = bmp.getHeight() / BMP_ROWS;
		
		//creates random x direction for sprite
		Random rnd = new Random();
		x = rnd.nextInt(gameView.getWidth() - width);
		//y direct set to downwards
		y = 0;
		//change to alter speed
		//can also change constant MAX_SPEED
		xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
	
	
		ySpeed = (MAX_SPEED*2);
		
		
	}
	
	
	
	//method used to update sprite direction on collision with canvas edge
	private void update() {
				
		if (x > gameView.getWidth() - width - xSpeed || x + xSpeed < 0) {
			xSpeed = -xSpeed;
		}
		
		if(y >= 1020 - height - ySpeed && y <= 1040 - height - ySpeed){
			
			hit = true;
			
			float x1 = getX();
			float y1 = getY();
			
			System.out.println(x1);
			System.out.println(y1);
			
			
		
		
		}
		
		y = y + ySpeed;
		
		
		
		currentFrame = ++currentFrame % BMP_COLUMNS;
		
		
	}
	
	
	
	
	//draws sprite on canvas
	//src is a source rectangle inside the bitmap which is going to be written
	//dst is a destination rectangle on canvas where we are going to write
	public void onDraw(Canvas canvas) {
		update();
		 int srcX = currentFrame * width;
		 int srcY = getAnimationRow() * height;
         Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
         Rect dst = new Rect(x, y, x + width, y + height);
         
       
         
         canvas.drawBitmap(bmp, src, dst, null);
         
         Paint paint = new Paint();
         paint.setTextSize(60);
         paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
         paint.setColor(gameView.getResources().getColor(R.color.electric_blue));
         paint.setTextSize(60);
         
         if(gameView.lives > 1 && hit == true){
        	canvas.drawText("HIT", 320, 500, paint);
        	gameView.lives = (gameView.lives - 1);
        	hit = false;
        	
         }else if(gameView.lives <= 1 && hit == true){
        	 
        	 	gameView.lives = 0;
        		
        	gameView.gameOver = true;
        		
        		
        		canvas.drawText("GAME OVER", 230, 500, paint);
        	}
        	
       	
         if(gameView.gameOver == true){
        	 
        	 
        	 canvas.drawText("GAME OVER", 230, 500, paint);
     	}
 
     
         hit = false;
	}
         
	
	
	
	
		//method returns true of x and y coordinates are inside area covered by sprite
		//if x< x2 touch was outside left of sprite
		// If x2 > x && x2 < x + width is true that means that the touch is in the same column
		//still we have to check if it is in the same row
		//y2 > y && y2 < y + height checks for row collision in the same way.
	  public boolean isCollition(float x2, float y2) {
          return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
	  
	 
	  
	  
	  //Creates sprite animation
	  //not definitely needed
	  private int getAnimationRow() {
          double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
          int direction = (int) Math.round(dirDouble) % BMP_ROWS;
          return DIRECTION_TO_ANIMATION_MAP[direction];
    }
	 
	


	 
}//end of class