package Screen;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleConstants.ColorConstants;

import Room.*;

public class Main extends JFrame {

	// set Font
	public static void setUIFont(FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				Font font = new Font(f.getFontName(), orig.getStyle(),
						f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	public Main() {
	}

	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
		}

		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		setUIFont(new FontUIResource(new Font("Malgun Gothic", Font.TRUETYPE_FONT, 30)));

		Main M = new Main();

		M.setTitle("Meeting Room Book System");

		LoginScreen LS = LoginScreen.getLoginScreen();
		LS.makeTitleScreen();
		M.add(LS);

		M.setDefaultCloseOperation(EXIT_ON_CLOSE);
		M.setVisible(true);
		M.setSize(1100, 700);

	}

}