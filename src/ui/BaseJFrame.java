package ui;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BaseJFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected static Font font10 = new Font("Tahoma", Font.PLAIN, 10);
	protected static Font font12 = new Font("Tahoma", Font.BOLD, 12);
	protected static Font font14 = new Font("Tahoma", Font.BOLD, 14);

	protected void createLabel(String name, int x, int y, int width, int height, Font font){
		JLabel label = new JLabel(name);
		label.setFont(font);
		label.setBounds(x, y, width, height);
		getContentPane().add(label);
	}
}