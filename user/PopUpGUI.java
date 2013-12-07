package scripts.worldlogger.user;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PopUpGUI extends JFrame {
	public PopUpGUI() {
		initComponents();
	}

	private void closeButton(ActionEvent e) {
		this.dispose();
	}

	private void initComponents() {
		error_message = new JTextField();
		close_button = new JButton();

		//======== this ========
		setTitle("Worthy's World One Logger");
		setAlwaysOnTop(true);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- error_message ----
		error_message.setFont(new Font("Dialog", Font.PLAIN, 12));
		error_message.setEditable(false);
		error_message.setBackground(Color.white);
		error_message.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(error_message);
		error_message.setBounds(0, 5, 325, 22);

		//---- close_button ----
		close_button.setText("OK");
		close_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeButton(e);
			}
		});
		contentPane.add(close_button);
		close_button.setBounds(130, 30, 65, close_button.getPreferredSize().height);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
	}

	public JTextField error_message;
	private JButton close_button;
}