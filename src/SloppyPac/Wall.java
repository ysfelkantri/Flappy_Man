package SloppyPac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall {
	
	private BufferedImage img  ;
	public int X = 240; // horizontal upper wall
	public  int Y = (int) ((Math.random()*GamePanel.height/2)-GamePanel.height)  ; // coo vertical upper wall
	private int gap = 200 ; // difference between upper and lower 
	private int width_w = 70 ; // width of wall
	private int height_w =  800  ; // 
	public static int speed = 4 ;
	
	public Wall (int x) 
	{
		this.X = x ;
		loadImage() ;
	}

	public void loadImage()
	{
		try {
			img = ImageIO.read(new File("Images/waall.png"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	
	public void drawWall(Graphics g )
	{
		g.drawImage(img, X , Y, width_w,height_w , null ) ;
		g.drawImage(img, X , Y+height_w + gap, width_w,height_w , null ) ;
	}
	
	public void move()
	{
		this.X -= speed ;
		
		if (this.X <= - width_w)
		{
			this.X = GamePanel.width*2 ;
			this.Y = (int) ((Math.random()*GamePanel.height/2 )-GamePanel.height) ;
		}
		
		Rectangle upperRect = new Rectangle(X,0,width_w,Y+height_w) ;
		Rectangle lowerRect = new Rectangle(X,Y+height_w+gap,width_w,GamePanel.height-(Y+height_w+gap)) ;
		
		if (upperRect.intersects(Bird.getBirdRect())||lowerRect.intersects(Bird.getBirdRect())) 
		{
			GamePanel.EndBackgroundMusic();
			GamePanel.PlayGameOverMusic();
			GamePanel.option = GamePanel.msg() ;
			if (GamePanel.option == true)
			{
				try {
					Thread.sleep(1000);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				Bird.reset() ;
				reset() ;
				Fen.tmr.start();
				GamePanel.option=false;
			}
			else 
			{
				Maintest.wind.dispose() ;
				GamePanel.EndGameOverMusic();
				GamePanel.RestartBackgroundMusic() ;
				Fen.timer.stop() ;
			}
		}
		
	}

	private void reset() {
		this.Y = (int) ((Math.random()*GamePanel.height/2)-GamePanel.height)  ;
		GamePanel.gameOver = true ;
		
		
	}
}
