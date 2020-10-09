package SloppyPac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage img ;
	private JButton morning = new JButton();
	private JButton night = new JButton();
	
	private static JTextField user = new JTextField();
	private static JTextField pswd = new JTextField();
	
	//private JButton start = new JButton("START");                                    
                                                                                       

	public static boolean lil = false;
	public MenuPanel()
	{
		loadImage() ;
		this.setLayout(null);
		//setFocusable(true);
		user.setBounds(100, 630, 130, 20);
		this.add(user);
		//setFocusable(true);
		pswd.setBounds(350, 630, 130, 20);
		this.add(pswd);
		

		
		morning.setBounds(110,200, 360, 165);
		this.add(morning) ;
		
		night.setBounds(110,100, 360, 165);
		this.add(night) ;
		//this.add(morning) ;
		
		//this.add(night) ;
		
		
		

	}
	
	public void loadImage()
	{
		try {
			img = ImageIO.read(new File("Images/Panel_m.png"));
			morning.setIcon(new ImageIcon("Images/morningb.png"));
			night.setIcon(new ImageIcon("Images/nightb.png"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override 
	public void paint(Graphics g )
	{
		super.paint(g);
		
		g.drawImage(img, 0, 0, 600, 800, null ) ;
		g.setColor(Color.RED);
		g.setFont(new Font ("arial",Font.BOLD,15));
		g.drawString("username :", 100, 620); 
		g.drawString("password :", 350, 620); 
		
		
		night.addActionListener(new ActionListener (){	
			@Override 
			public void actionPerformed(ActionEvent ev) { lil = true ; Fen.startGame = true; System.out.println("sssssss"); }});
		morning.addActionListener(new ActionListener (){	
			@Override 
			public void actionPerformed(ActionEvent argu){ Fen.startGame = true ;}}) ;
	}
	/*public static String getUser() { return user.getText() ; }
	public static String getPswd() { return pswd.getText() ; }*/
}
