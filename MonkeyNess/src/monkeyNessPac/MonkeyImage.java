package monkeyNessPac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MonkeyImage {
	
	private BufferedImage img = null;
	private static int monkey_dia =36 ;
	//monkey diameter
	public static int x = (GamePanel.WIDTH/2)-monkey_dia/2;
	public static int y = GamePanel.HEIGHT/2;
	
	private static int speed=2;
	private int acce = 1;
	
	
	public MonkeyImage(){
		LoadImage();
	}



	private void LoadImage() {
		
		try{
			img = ImageIO.read(new File("C:/Users/rashe/Desktop/MonkeyNess/MonkeyNess/Images/Monkey.png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	//draw Image
	
	public void drawMonkey(Graphics g){
		g.drawImage(img, x, y, null);
	}
	
	
	//monkey movement method
	public void monkeyMovement(){
		
		if(y>=0 && y<=GamePanel.HEIGHT){
			speed +=acce;
			y+=speed;
		}else{
			
			boolean option = GamePanel.popUpMessage();
			
			if (option){
				try{
					Thread.sleep(500);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				reset();
			}else{
				//close window
				JFrame frame = MainMonkey.getWindow();
				frame.dispose();
				MainMonkey.timer.stop();
			}
			
			
		}
	}


	
	public void goUpwards(){
		speed= -17;
		
	}
	
	
	
	

	public static void reset() {
		speed=2;
		y = GamePanel.HEIGHT/2;
		GamePanel.GameOver = true;
		
		//reseting score
		GamePanel.score=0;
			
	}
	
	public static Rectangle getMonkeyRect(){
		Rectangle monkeyRect = new Rectangle(x,y,monkey_dia,35);
		return monkeyRect;
		
		
	}
	

}
