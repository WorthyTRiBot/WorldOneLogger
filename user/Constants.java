package scripts.worldlogger.user;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Constants {
	public static final Rectangle WORLD_SWITCH_BOUNDS = new Rectangle(5, 463, 100, 35);			//go to world select menu
	public static final Rectangle CANCEL_BOUNDS = new Rectangle(708, 4, 50, 16);				//go back to menu
	public static final Rectangle WORLD_ONE_BOUNDS = new Rectangle(199, 73, 88, 19);			//full world 1 button bounds
	public static final Rectangle WORLD_ONE_CHECK_BOUNDS = new Rectangle(249, 78, 21, 8);		//smaller portion of text
	public static final Rectangle BLACK_TEXT_SAMPLE_BOUNDS = new Rectangle(50, 150, 20, 20);	//small black box to test if on selection screen
	
	public static final Image WORLD_ONE_CHECK = getImage("http://i.imgur.com/daSUbFN.png");	//"FULL" text
	
	public static final RenderingHints antialiasing = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}
}
