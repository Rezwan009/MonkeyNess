package monkeyNessPac;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	private BufferedImage img=null;
	public boolean StartingPoint =false;
	
	public MenuPanel(){
		
		LoadImage();
		//handle mouse click event
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				super.mouseClicked(e);
				StartingPoint=true;
			}
		});
	}

	private void LoadImage() {
		
		try{
			img= ImageIO. read(new File("C:/Users/rashe/Desktop/MonkeyNess/MonkeyNess/Images/menuPanel.png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.drawImage(img, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
	}
	
	
}
