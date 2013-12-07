package scripts.worldlogger.user;

import java.awt.Image;
import java.util.ArrayList;

import scripts.worldlogger.api.Node;

public class Variables {
	public static ArrayList<Node> nodes;
	public static PopUpGUI popupGUI;
	
	public static Image selectWorldScreen = null;
	public static Image worldOne = null;
	
	public static float t_startTime;	//tracking
	public static float t_timeTaken;
	public static boolean gotIntoWorldOne = false;
	
	public static boolean clickSwitch = false;
	public static boolean clickCancel= false;
}
