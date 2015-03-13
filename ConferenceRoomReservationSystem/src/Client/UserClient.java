// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package Client;

import Client.*;
import Common.*;
import Room.*;
import User.EnterpriseUser;
import User.LoginData;
import User.NormalUser;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 * 
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class UserClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	UserIF clientUI;

	private String messageFromServer = "";
	private ArrayList<DefaultRoom> registeredRoomListFromServer;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 * 
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */

	public UserClient(String host, int port, UserIF clientUI)
			throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 * 
	 * @param msg
	 *            The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {

		try {
			String temp = (String) msg;

			clientUI.display(temp);
		} catch (Exception e) {

			clientUI.display("Object Received......");

			setRegisteredRoomListFromServer((ArrayList<DefaultRoom>) msg);

			Iterator<DefaultRoom> itr = this.getRegisteredRoomListFromServer()
					.iterator();

			while (itr.hasNext()) {
				DefaultRoom temp = itr.next();
				temp.print();
			}
		}
	}

	/**
	 * This method handles all data coming from the UI
	 * 
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {
		try {
			sendToServer(message);
		} catch (IOException e) {
			clientUI.display("Could not send message to server. Terminating client.");
			quit();
		}
	}

	public void registerRoom(String name, int maxNumber, String city,
			String zipcode, String detail, Price price, Date start, Date end) {
		DefaultRoom temp = new DefaultRoom(name, maxNumber);
		temp.setLocation(city, zipcode, detail);
		temp.setPrice(price);
		temp.setStart(start);
		temp.setEnd(end);
		temp.setPeriod();

		try {
			sendToServer(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(String id, String pw) {

		LoginData LD = new LoginData(id, pw);
		try {
			sendToServer(LD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteRoom(String name) {

		String temp = "##";

		temp += name;

		try {
			sendToServer(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void registerEnterpriseUser(EnterpriseUser EU) {

		EnterpriseUser temp = EU;

		try {
			sendToServer(temp);
			System.out.println("Well done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void registerNormalUser(NormalUser NU) {

		NormalUser temp = NU;

		try {
			sendToServer(temp);
			System.out.println("Well done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bookRoom(String name, Date date) {

		BookedRoom temp = new BookedRoom();
		temp.setDate(date);
		temp.setName(name);
		temp.setIsbooked(true);

		try {
			sendToServer(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	@Override
	protected void connectionException(Exception exception) {
		System.out.println("server has shutdowned!");
		try {
			closeConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void connectionClosed() {
		System.out.println("Terminating client.");
	}

	public String getMessageFromServer() {
		return messageFromServer;
	}

	public void setMessageFromServer(String messageFromServer) {
		this.messageFromServer = messageFromServer;
	}

	public ArrayList<DefaultRoom> getRegisteredRoomListFromServer() {
		return registeredRoomListFromServer;
	}

	public void setRegisteredRoomListFromServer(
			ArrayList<DefaultRoom> registeredRoomListFromServer) {
		this.registeredRoomListFromServer = registeredRoomListFromServer;
	}

}

// End of ChatClient class