package scripts.worldlogger.nodes;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Screen;

import scripts.worldlogger.api.Node;
import scripts.worldlogger.user.Constants;
import scripts.worldlogger.user.Variables;

public class JoinWorldOne extends Node {

	@Override
	public void execute() {
		getInitialImages();
			
		if (!onSelectWorldScreen() && !Variables.clickSwitch) {
			Mouse.hop(getRandomPoint(Constants.WORLD_SWITCH_BOUNDS));	//go to world selection screen
			Mouse.click(0);
			Variables.clickSwitch = true;
			Variables.clickCancel = false;
		}
		
		if (!matchImages(Variables.worldOne, Constants.WORLD_ONE_CHECK) && onSelectWorldScreen()) {
			Mouse.hop(getRandomPoint(Constants.WORLD_ONE_BOUNDS));	//click on world 1
			Mouse.click(0);
			
			Variables.t_timeTaken = (System.currentTimeMillis() - Variables.t_startTime)/1000f;
			Variables.gotIntoWorldOne = true;
		}
		
		if (onSelectWorldScreen() && !Variables.clickCancel) {
			Mouse.hop(getRandomPoint(Constants.CANCEL_BOUNDS));	//go back to main menu
			Mouse.click(0);
			Variables.clickSwitch = false;
			Variables.clickCancel = true;
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
	
	/**
	 * Sets Variables selectWorldScreen and worldOne to cropped captured screen images
	 */
	private void getInitialImages() {
		if (Variables.selectWorldScreen == null) {
			Variables.selectWorldScreen = Screen.getGameImage().getSubimage(Constants.BLACK_TEXT_SAMPLE_BOUNDS.x,
					Constants.BLACK_TEXT_SAMPLE_BOUNDS.y,
					Constants.BLACK_TEXT_SAMPLE_BOUNDS.width, 
					Constants.BLACK_TEXT_SAMPLE_BOUNDS.height);
			if (!onSelectWorldScreen()) {	//make sure the image was taken on the right screen
				Mouse.hop(getRandomPoint(Constants.WORLD_SWITCH_BOUNDS));	//go to world selection screen
				Mouse.click(0);
			}
		}
		Variables.worldOne = Screen.getGameImage().getSubimage(Constants.WORLD_ONE_CHECK_BOUNDS.x,
				Constants.WORLD_ONE_CHECK_BOUNDS.y, 
				Constants.WORLD_ONE_CHECK_BOUNDS.width, 
				Constants.WORLD_ONE_CHECK_BOUNDS.height);	//get pic of w1 to match
	}
		
	/**
	 * Matches two images
	 * @param imgOne
	 * @param imgTwo
	 * @return true if images match
	 */
	private boolean matchImages(Image imgOne, Image imgTwo) {
		BufferedImage one = (BufferedImage) imgOne;
		BufferedImage two = (BufferedImage) imgTwo;
		for (int x = 0; x < one.getWidth(); x++) {
			for (int y = 0; y < one.getHeight(); y++) {
				if (one.getRGB(x, y) != two.getRGB(x, y))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @return true if the matched imaged is completely black, meaning it's on the selection screen
	 */
	private boolean onSelectWorldScreen() {
		BufferedImage img = (BufferedImage) Variables.selectWorldScreen;
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				if (img.getRGB(x, y) != Color.black.getRGB())
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @param r
	 * @return a random point in r
	 */
	private Point getRandomPoint(Rectangle r) {
		int randomX = General.random(r.x, r.x + r.width);
		int randomY = General.random(r.y, r.y + r.height);

		return new Point(randomX, randomY);
	}
}
