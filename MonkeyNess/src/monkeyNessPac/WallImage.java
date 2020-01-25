package monkeyNessPac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class WallImage {
	
	private Random r = new Random();
	public int X;
	public int Y = r.nextInt(GamePanel.HEIGHT-400)+200;//max 600, min 200
	private int width_Wall = 45;
	private int height = GamePanel.HEIGHT-Y;
	private int gap = 200;
	
	
	public static int speed = -6;
	
	
	private BufferedImage img = null;
	
	public WallImage(int X){
		this.X=X;
		
		LoadImage();
	}

	private void LoadImage() {
		
		try{
			img = ImageIO.read(new File("C:/Users/rashe/Desktop/MonkeyNess/MonkeyNess/Images/Wall.jpg"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void drawWall(Graphics g){
		
		g.drawImage(img, X, Y, null); //bottom wall
		g.drawImage(img, X, (-GamePanel.HEIGHT)+(Y-gap), null); //upper wall
	}
	
	
	public void wallMovement(){
		
		X+=speed;
		
	if(X<=-width_Wall){
		X = GamePanel.WIDTH;
		Y = r.nextInt(GamePanel.HEIGHT-400)+200;
		height = GamePanel.HEIGHT-Y;
	}
	//creating rectangle for wall
	
	  Rectangle lowerRect = new Rectangle(X,Y,width_Wall,height);
	  Rectangle upperRect = new Rectangle(X,0,width_Wall,GamePanel.HEIGHT-(height+gap));
	  
	  if(lowerRect.intersects(MonkeyImage.getMonkeyRect())||upperRect.intersects(MonkeyImage.getMonkeyRect())){
		  //to do :close window for upper and lower part
		  boolean option = GamePanel.popUpMessage();
		  
		  if(option){
			  try{
				  Thread.sleep(500);
			  }catch(Exception ex){
				  ex.printStackTrace();
			  }
			  MonkeyImage.reset();
			  Wall_Reset();
		  }else{
			  //close the window
			  JFrame frame = MainMonkey.getWindow();
			  frame.dispose();
			  MainMonkey.timer.stop();
			  
		  }
		  
		  

	  }
	
	
	
	}

	private void Wall_Reset() {
		
		Y = r.nextInt(GamePanel.HEIGHT-400)+200;
		height = GamePanel.HEIGHT-Y;
		GamePanel.GameOver = true;
	}
	

}
