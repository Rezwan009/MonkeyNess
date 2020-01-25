package monkeyNessPac;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//to do resetting x coordinates
	public static boolean GameOver = false;
	
	//to do counting score
	public static int score = 0;
	//to do : pop up massage for get ready
	public static boolean starting = false;
	public static int proceed= -1;
	
	public static final int WIDTH=600;
	public static final int HEIGHT=800;
	
	private int xCoor=0;
	private BufferedImage img;
	
	MonkeyImage mi = new MonkeyImage();
	
	WallImage wi = new WallImage(GamePanel.WIDTH);
	WallImage wi2 = new WallImage(GamePanel.WIDTH+(GamePanel.WIDTH/2));
	
	public GamePanel(){
		LoadImage();
		//mouse pressed event
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				super.mousePressed(e);
				mi.goUpwards();
			}
		});
	}

	private void LoadImage() {
		
		try{
			img = ImageIO.read(new File("C:/Users/rashe/Desktop/MonkeyNess/MonkeyNess/Images/gamePanel.png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.drawImage(img, xCoor, 0, null);
		g.drawImage(img, xCoor+2390, 0, null);
		
		mi.drawMonkey(g);
		wi.drawWall(g);
		wi2.drawWall(g);
		
		//displaying score
		g.setFont(new Font("Tahoma",Font.BOLD,40));
		g.drawString("Score "+score, WIDTH/2, 100);
		
		//drawing integer for starting and get ready
		if (starting){
			g.setFont(new Font("Tahoma",Font.BOLD,150));
			g.drawString(Integer.toString(proceed), WIDTH/2-75, 250);
			
		}
		
	}
	
	
	public void move(){
		mi.monkeyMovement();
		
		wi.wallMovement();
		wi2.wallMovement();
		
		if (GameOver){
			wi.X=GamePanel.WIDTH;
			wi2.X=GamePanel.WIDTH+(GamePanel.WIDTH/2);
			GameOver = false;
			
		}
		//moving Background Image
		xCoor+=WallImage.speed;
		
		if(xCoor==-2400){
			xCoor=0;
		}
		
		//score increment 
		//System.out.println(wi.X+"->"+MonkeyImage.x +"   :   "+wi2.X+"->"+MonkeyImage.x);
		
		if(wi.X==MonkeyImage.x || wi2.X==MonkeyImage.x){
			score+=1;
			
			
		}
		
	}
	
	
	//to do pop up massage
	public static boolean popUpMessage(){
		
		int result = JOptionPane.showConfirmDialog(null, "Game Over MonkeyBoy, Your score is: "+score+"\n Do you wanna restart the game ?", "Game Over", JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION){
			return true;
		}else{
			return false;
		}
	}
	

}
