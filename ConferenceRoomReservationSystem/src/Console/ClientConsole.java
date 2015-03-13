package Console;

//This file contains material supporting section 3.7 of the textbook:
//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com 

import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import Client.*;
import Common.*;
import Room.*;
import Screen.LoginScreen;
import Screen.Main;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConsole extends JFrame implements UserIF {
	// Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	private static UserClient client;
	
	private static int key;

	public static UserClient getClient() {
		return client;
	}

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 */
	public ClientConsole(String host, int port) {
		try {
			client = new UserClient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!"
					+ " Terminating client.");
			System.exit(1);
		}
	}

	// Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it
	 * sends it to the client's message handler.
	 */
	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(
					new InputStreamReader(System.in));
			String message;

			// client.login("admin");// 어드민으로 로그인

			while (true) {
				message = fromConsole.readLine();
				if (message.substring(0, 1).equals("#")) {
					String[] command = message.substring(1).split(" ");
					if (command[0].equals("room")) {
						client.registerRoom("aaaasfbasd", 20, "abasdb","asdbasdb", "asdbsadb", 
								new Price(100),new Date(),new Date());
					} else if (command[0].equals("logoff")) {
						client.closeConnection();
					} else if (command[0].equals("sethost")) {
						if (!client.isConnected())
							client.setHost(command[1]);
						else
							System.out.println("Already Connected!");
					} else if (command[0].equals("setport")) {
						if (!client.isConnected())
							client.setPort(Integer.parseInt(command[1]));
						else
							System.out.println("Already Connected!");
					} else if (command[0].equals("login")) {
						if (!client.isConnected())
							client.openConnection();
						else
							System.out.println("Already Connected!");
					} else if (command[0].equals("gethost")) {
						System.out.println(client.getHost());
					} else if (command[0].equals("getport")) {
						System.out.println(client.getPort());
					} else {
						System.out.println("Unknown Command");
					}
				} else
					client.handleMessageFromClientUI(message);
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 * 
	 * @param message
	 *            The string to be displayed.
	 */
	public void display(String message) {

		if (message.substring(0, 1).equals(" ")) {
			System.out.println(message.substring(1));
		} else {
			client.setMessageFromServer(message);
			System.out.println("> " + message);
		}
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args
	 *            [0] The host to connect to.
	 */
	public static void main(String[] args) {

		String host = "";
		int port = 0; // The port number
		boolean sethost = false;
		boolean setport = false;

		try {
			port = Integer.parseInt(args[0]);
			setport = true;
			host = "localhost";
		} catch (Throwable t) {
			port = DEFAULT_PORT;
			try {
				host = args[0];
				sethost = true;
			} catch (ArrayIndexOutOfBoundsException e) {
				host = "localhost";
			}
		}

		if (args.length == 2) {
			if (sethost) {
				try {
					port = Integer.parseInt(args[1]);
					setport = true;
				} catch (Throwable t) {
					port = DEFAULT_PORT;
				}
			} else if (setport) {
				try {
					host = args[1];
					sethost = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					host = "localhost";
				}
			}

		}

		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		try {

			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
		}

		setUIFont(new FontUIResource(new Font("Malgun Gothic", Font.PLAIN, 25)));

		ClientConsole chat = new ClientConsole(host, port);
		chat.setTitle("Meeting Room Book System");
		LoginScreen LS = LoginScreen.getLoginScreen();
		LS.makeTitleScreen();

		chat.add(LS);
		chat.setDefaultCloseOperation(EXIT_ON_CLOSE);
		chat.setVisible(true);
		chat.setSize(1100, 700);
		
		chat.accept(); // Wait for console data
	}

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

	public static int getKey() {
		return key;
	}

	public static void setKey(int setkey) {
		key = setkey;
	}
	

}
// End of ConsoleChat class