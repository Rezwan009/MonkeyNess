package monkeyNessPac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.Timer;

public class MainMonkey {
         //Window
	     //1st panel
	     //2nd panel
	private static JFrame window;
	public static Timer timer,timer2;
	private int proceed=4;
	
	public MainMonkey(){
		window=new JFrame ();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(GamePanel.WIDTH,GamePanel.HEIGHT);
		window.setLocationRelativeTo(null);
		window.setTitle("MonkeyNess");
		window.setResizable(true);
		//window.setVisible(true);
	}
	
	private void rendering(){
		
		MenuPanel mp = new MenuPanel();
		GamePanel gp = new GamePanel();
		
		//timer
		timer= new Timer(20,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gp.repaint();
				gp.move();
			}
		});
		
		window.add(mp);
		window.setVisible(true);
		
		while(mp.StartingPoint==false){
			try{
				Thread.sleep(10);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		//remove menu panel
		window.remove(mp);
		window.add(gp);
		gp.setVisible(true);
		window.revalidate();
		
		//start the timer
		//timer.start();
		timer2 = new Timer (1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				proceed--;
				GamePanel.proceed=proceed;
				GamePanel.starting=true;
				gp.repaint();
				if(proceed==0){
					timer2.stop();
					timer.start();
					GamePanel.starting=false;
				}
				
			}
			
		});
		
		timer2.start();
		
	}
	
	//close window
	public static JFrame getWindow(){
		return window;
	}
	
	public static void main(String[] args) {
		
		MainMonkey mk = new MainMonkey();
		mk.rendering();
		

	}

}
