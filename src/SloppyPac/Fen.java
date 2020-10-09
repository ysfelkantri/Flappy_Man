package SloppyPac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.sun.management.jmx.Trace;

public class Fen extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static boolean startGame = false ;
	private MenuPanel pan = new MenuPanel() ; 
	private GamePanel gan = new GamePanel() ;
	public int height = 800 ;
	public int width = 600 ;
	
	private JButton morning = new JButton();
	
	
	public static Timer timer,tmr ;

	public Fen()
	{
		
		super(" Flappy man ");

		this.setResizable(false);
		this.setLocation(200,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width,height);
		//this.setContentPane(pan);
		this.add(pan) ;
		
		pan.addMouseListener(new Panel_mouse());
		
		this.addKeyListener(new clickFEspace());
		//gan.addKeyListener(new clickFEspace());
		System.out.print("key added ");
		this.setVisible(true);
		this.revalidate() ;
		
		GamePanel.proceed = 4 ;
		//timer 
		
		timer = new Timer(20,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gan.repaint();	
				gan.mvt();
			}	
	});
		
		while (!startGame )
		{
			try {Thread.sleep(1000);
			}catch(InterruptedException exp)
			{exp.printStackTrace() ;}	
		}
			
		GamePanel.PlayBackgroundMusic();
		//this.addKeyListener(new clickFEspace());
		this.setResizable(true);
		this.remove(pan) ;
		//this.setContentPane(gan);
		this.add(gan) ;
		this.setVisible(true); 

		this.revalidate() ;
		//timerStart 1
		tmr = new Timer(1000,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
				GamePanel.proceed -- ;
				GamePanel.ready = true ;
				gan.repaint() ;
				if (GamePanel.proceed ==  -1)
				{
					tmr.stop();
					GamePanel.ready = false ;
					GamePanel.gameOver=false;
					timer.start() ;
				}
			}
		});
		tmr.start() ;
	}

	public class clickFEspace implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			if (arg0.getKeyChar() == ' ')
			{
				//System.out.println("" + MenuPanel.getUser());
				Bird.speed = -13 ;
				
				//Bird.PlayBirdMusic();
			}
			
		}
		
	}
	public class Panel_mouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			startGame = true ;
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

}
}


