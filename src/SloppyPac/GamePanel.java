package SloppyPac;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//import sun.audio.AudioPlayer ;
import javax.sound.sampled.AudioSystem;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.applet.Applet;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int width = 700 ;
	public static final int height = 900 ;
	private BufferedImage img,img1 ;
	private Bird bird = new Bird() ;
	private Wall wall = new Wall(GamePanel.width ) ;
	private Wall wall2 = new Wall(GamePanel.width + 500) ;
	private Wall wall3 = new Wall(GamePanel.width + 1000) ;
	private static int xcour = 0 ;
	public static boolean gameOver = false  ; 
	public static int score = 0 ;
	public static boolean ready = false ;
	public static int proceed = -1 ;
	public static int distance = -1 ;
	
	public static boolean option = false ;
	
	
	//MusicBackground
	public static  InputStream musicBackground ;
	public static AudioStream audiosBackground;
	
	// Music GameOver
	public static  InputStream musicGameOver;
	public static AudioStream audiosGameOver;
	
	
	
	public GamePanel ()
	{
		loadImage() ;
		//setFocusable(true);
		InitBachgroundMusic();
		InitGameOverMusic();
		Bird.InitBirdMusic();	
	}
	public void loadImage()
	{
		try {
			img = ImageIO.read(new File("Images/lil.jpg"));
			img1 = ImageIO.read(new File("Images/sba7.png"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override 
	public void paint(Graphics g )
	{
		super.paint(g);
		if (MenuPanel.lil == true) {
		g.drawImage(img, xcour, 0, null ) ;
		g.drawImage(img, xcour+1047, 0, null ) ;
		}else {
		g.drawImage(img1, xcour, 0, null ) ;
		g.drawImage(img1, xcour+1047, 0, null ) ;}
		
		g.setFont(new Font("Tahoma",Font.BOLD,20));
		g.setColor(Color.red);
		g.drawString("Score : " + score, 10, 30);
		if (distance >= 0 )
		{
			g.setColor(Color.green) ;
			g.drawString( "" + distance , 13, 60) ;
		}
		bird.drawBird(g);
		wall.drawWall(g) ;
		wall2.drawWall(g) ;
		wall3.drawWall(g) ;
		if (ready)
		{
			g.setFont(new Font("Tahoma",Font.BOLD,100));
			g.setColor(Color.blue);
			if(proceed == 3) {
				g.drawString("Ready",GamePanel.width/2 - 150 , GamePanel.height/2 ) ;

				GamePanel.PlayBackgroundMusic() ;
//----			
				}
			else g.drawString(Integer.toString(proceed),GamePanel.width/2 - 75 , GamePanel.height/2 ) ;
			
		}
		else distance++ ;
	}
	public void mvt() {
		if (gameOver)
		{
			wall.X = GamePanel.width ;
			wall2.X = GamePanel.width +500 ;
			wall3.X = GamePanel.width +1000 ;
			score = 0 ;
			distance = -1 ;
			GamePanel.proceed = 4 ;	
			GamePanel.RestartBackgroundMusic() ;
		}else {
			bird.move();
			wall.move();
			wall2.move();
			wall3.move();
			xcour -= Wall.speed/4 ;
		}
		
		if (xcour+1047 == 0)
		{
			xcour = 0;
		}
		if ((Bird.posX == wall.X) || (Bird.posX == wall2.X))
		{
			GamePanel.score++ ;
		}
		
	}
	
	
	public static boolean msg()
	{
		int result = JOptionPane.showConfirmDialog(null, " your score is : " + score + "\n Would you like to restart the game ? " , "Game Over !", JOptionPane.YES_NO_OPTION) ;
		if (result == JOptionPane.YES_OPTION) return true ;
		else return false ;
	}

	public static void InitBachgroundMusic() {
		try {
			GamePanel.musicBackground = new FileInputStream(new File("music/soundBackground.wav"));
			GamePanel.audiosBackground= new AudioStream(musicBackground);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void PlayBackgroundMusic() {
		AudioPlayer.player.start(GamePanel.audiosBackground);
	}
	
	public static void EndBackgroundMusic() {
		AudioPlayer.player.stop(GamePanel.audiosBackground);
	}
	
	public static void RestartBackgroundMusic() {
		EndBackgroundMusic() ;
		InitBachgroundMusic() ;
	}
	
	public static void InitGameOverMusic() {
		try {
			GamePanel.musicGameOver = new FileInputStream(new File("music/Gameover.wav"));
			GamePanel.audiosGameOver= new AudioStream(musicGameOver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// AudioPlayer.player.start(audios);
	}
	public static void PlayGameOverMusic() {
		AudioPlayer.player.start(GamePanel.audiosGameOver);
	}
	public static void EndGameOverMusic() {
		AudioPlayer.player.stop(GamePanel.audiosGameOver);
	}
	/*public void musique()
	{
		InputStream is ;
		AudioPlayer  AP = AudioPlayer.player ;
		AudioStream  AS ;
		AudioData  AD ;
		ContnuousAudioDataStream loop = null ;
	}*/
}
