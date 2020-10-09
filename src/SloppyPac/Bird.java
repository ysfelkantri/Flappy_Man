package SloppyPac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Bird {

	private BufferedImage img  ;
	private BufferedImage img1  ;
	private BufferedImage img2  ;
	private static int bird_dia = 60 ;
	public static final int posX = 200 ;
	public static int posY=(GamePanel.height/2) - bird_dia ;
	public static int speed = 2 ;
	private int acce = 1 ;
	
	// MUsic 
	public static  InputStream musicBird;
	public static AudioStream audiosBird;
	
	public Bird() 
	{	
		loadImage() ;
	}

	public void loadImage()
	{
		try {
				img = ImageIO.read(new File("Images/superman.png"));
				img1 = ImageIO.read(new File("Images/superman2.png"));
				img2 = ImageIO.read(new File("Images/superman4.png"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	/*public void loadImage1()
	*{
	*	try {
	*			img = ImageIO.read(new File("E:/learning/S2 GSEII/java/game project/superman.png"));
	*	}catch(IOException ex) {
	*		ex.printStackTrace();
	*	}
	}*/
	
	public void drawBird(Graphics g )
	{
		if (GamePanel.distance%10 <3 )g.drawImage(img, posX , posY, bird_dia,bird_dia , null ) ;
		else if(GamePanel.distance%10 >= 3 && GamePanel.distance%10 <6  ) g.drawImage(img1, posX , posY, bird_dia,bird_dia , null ) ;
		else if(GamePanel.distance%10 == 6 /*>= 4 && GamePanel.distance%10 <6*/  ) g.drawImage(img2, posX , posY, bird_dia,bird_dia , null ) ;
		else  g.drawImage(img1, posX , posY, bird_dia,bird_dia , null ) ;
	}
	
	public void move()
	{
		if ((posY< GamePanel.height))
		{
			if ((posY < 0)) posY = 0 ;
			speed += acce ;
			posY += speed ;
		}
		else 
		{
			GamePanel.EndBackgroundMusic();
			
			GamePanel.option = GamePanel.msg() ;
			if (GamePanel.option)
			{	
			try {
				Thread.sleep(1000);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			reset() ;
			
			}else {
				Maintest.wind.dispose() ;
				GamePanel.RestartBackgroundMusic() ;
				Fen.timer.stop() ;
			}
		}
	}
	public static void reset() {
		speed = 2 ;
		posY = (GamePanel.height/2) - bird_dia ;
		GamePanel.gameOver = true ;
		Fen.tmr.start();
		GamePanel.option=false;
	}
	
	public static Rectangle getBirdRect() 
	{
		Rectangle birdRect = new Rectangle(posX,posY,bird_dia,bird_dia) ;
		return birdRect ;
	}
	public static void InitBirdMusic() {
		try {
			Bird.musicBird = new FileInputStream(new File("music/soundBird.wav"));
			System.out.println("music Bird");
			Bird.audiosBird= new AudioStream(musicBird);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// AudioPlayer.player.start(audios);
	}
	public static void PlayBirdMusic() {
		AudioPlayer.player.start(Bird.audiosBird);
	}
}
