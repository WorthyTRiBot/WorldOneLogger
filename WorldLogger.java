package scripts.worldlogger;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingUtilities;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import scripts.worldlogger.api.Node;
import scripts.worldlogger.nodes.JoinWorldOne;
import scripts.worldlogger.user.PopUpGUI;
import scripts.worldlogger.user.Variables;


@ScriptManifest (authors = {"Worthy"}, category = "Worthy Scripts", name = "World One Logger")
public class WorldLogger extends Script {

	@Override
	public void run() {
		onStart();
		loop();
	}
	
	private void onStart() {
		println("Welcome to Worthy Scripts.");
		println("World One Logger Initiated.");
		
		setLoginBotState(false);
		Variables.popupGUI = new PopUpGUI();
		Variables.nodes = new ArrayList<>();
		Variables.t_startTime = System.currentTimeMillis();
		
		Collections.addAll(Variables.nodes, new JoinWorldOne());
	}
	
	private void loop() {
		while (!Variables.gotIntoWorldOne) {
			for (final Node node : Variables.nodes) {
				if (node.validate()) {
					node.execute();
					sleep(General.random(20, 40));
				}
			}
		}

		onFinish();
		stopScript();
	}
	
	private void onFinish() {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					Variables.popupGUI.setVisible(true);
					Variables.popupGUI.error_message.setText("Logged into World 1 in " 
							+ Variables.t_timeTaken + ((Variables.t_timeTaken != 1) ? " seconds" : " second"));
				}
			});
		} catch (Throwable ignore) {
			General.println("Failed to load popup GUI.");
		}
	}
}