// This file contains material supporting section 3.8 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import Room.DefaultRoom;
import Room.DefaultRoomList;
import User.EnterpriseUser;
import User.EnterpriseUserList;
import User.LoginData;
import User.NormalUser;
import User.NormalUserList;

/**
 * An instance of this class is created by the server when a client connects. It
 * accepts messages coming from the client and is responsible for sending data
 * to the client since the socket is private to this class. The AbstractServer
 * contains a set of instances of this class and is responsible for adding and
 * deleting them.
 * <p>
 *
 * Project Name: OCSF (Object Client-Server Framework)
 * <p>
 *
 * @author Dr Robert Lagani&egrave;re
 * @author Dr Timothy C. Lethbridge
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version February 2001 (2.12)
 */
public class ConnectionToClient extends Thread {
	// INSTANCE VARIABLES ***********************************************

	/**
	 * A reference to the Server that created this instance.
	 */
	private AbstractServer server;

	/**
	 * Sockets are used in the operating system as channels of communication
	 * between two processes.
	 * 
	 * @see java.net.Socket
	 */
	private Socket clientSocket;

	/**
	 * Stream used to read from the client.
	 */
	private ObjectInputStream input;

	/**
	 * Stream used to write to the client.
	 */
	private ObjectOutputStream output;

	/**
	 * Indicates if the thread is ready to stop. Set to true when closing of the
	 * connection is initiated.
	 */
	private boolean readyToStop;

	/**
	 * Map to save information about the client such as its login ID. The
	 * initial size of the map is small since it is not expected that concrete
	 * servers will want to store many different types of information about each
	 * client. Used by the setInfo and getInfo methods.
	 */
	private HashMap savedInfo = new HashMap(10);

	private String userId = "";
	private String userPW = "";
	private int enterpriseKey = -1;
	private int normalKey = -1;

	// CONSTRUCTORS *****************************************************

	/**
	 * Constructs a new connection to a client.
	 *
	 * @param group
	 *            the thread group that contains the connections.
	 * @param clientSocket
	 *            contains the client's socket.
	 * @param server
	 *            a reference to the server that created this instance
	 * @exception IOException
	 *                if an I/O error occur when creating the connection.
	 */
	ConnectionToClient(ThreadGroup group, Socket clientSocket,
			AbstractServer server) throws IOException {
		super(group, (Runnable) null);
		// Initialize variables
		this.clientSocket = clientSocket;
		this.server = server;

		clientSocket.setSoTimeout(0); // make sure timeout is infinite

		// Initialize the objects streams
		try {
			input = new ObjectInputStream(clientSocket.getInputStream());
			output = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException ex) {
			try {
				closeAll();
			} catch (Exception exc) {
			}

			throw ex; // Rethrow the exception.
		}

		readyToStop = false;
		start(); // Start the thread waits for data from the socket
	}

	// INSTANCE METHODS *************************************************

	/**
	 * Sends an object to the client.
	 *
	 * @param msg
	 *            the message to be sent.
	 * @exception IOException
	 *                if an I/O error occur when sending the message.
	 */
	final public void sendToClient(Object msg) throws IOException {
		if (clientSocket == null || output == null)
			throw new SocketException("socket does not exist");

		output.writeObject(msg);
	}

	/**
	 * Closes the client. If the connection is already closed, this call has no
	 * effect.
	 *
	 * @exception IOException
	 *                if an error occurs when closing the socket.
	 */
	final public void close() throws IOException {
		readyToStop = true; // Set the flag that tells the thread to stop

		try {
			closeAll();
		} finally {
			server.clientDisconnected(this);
		}
	}

	// ACCESSING METHODS ------------------------------------------------

	/**
	 * Returns the address of the client.
	 *
	 * @return the client's Internet address.
	 */
	final public InetAddress getInetAddress() {
		return clientSocket == null ? null : clientSocket.getInetAddress();
	}

	/**
	 * Returns a string representation of the client.
	 *
	 * @return the client's description.
	 */
	public String toString() {
		return clientSocket == null ? null : clientSocket.getInetAddress()
				.getHostName()
				+ " ("
				+ clientSocket.getInetAddress().getHostAddress() + ")";
	}

	/**
	 * Saves arbitrary information about this client. Designed to be used by
	 * concrete subclasses of AbstractServer. Based on a hash map.
	 *
	 * @param infoType
	 *            identifies the type of information
	 * @param info
	 *            the information itself.
	 */
	public void setInfo(String infoType, Object info) {
		savedInfo.put(infoType, info);
	}

	/**
	 * Returns information about the client saved using setInfo. Based on a hash
	 * map.
	 *
	 * @param infoType
	 *            identifies the type of information
	 */
	public Object getInfo(String infoType) {
		return savedInfo.get(infoType);
	}

	// RUN METHOD -------------------------------------------------------

	/**
	 * Constantly reads the client's input stream. Sends all objects that are
	 * read to the server. Not to be called.
	 */
	final public void run() {

		System.out.println("Here!!");
		server.clientConnected(this);
		int errorCode = -1;
		int registerCode = 1;

		System.out.println("Got it!!");
		// This loop reads the input stream and responds to messages
		// from clients
		try {
			// The message from the client
			Object msg = null;

			while (errorCode != 0 && errorCode != 3) {
				msg = input.readObject();

				try {
					LoginData LD = (LoginData) msg;
					server.receiveMessageFromClient(LD.getId(), this);
					server.receiveMessageFromClient(LD.getPw(), this);

					this.setUserId(LD.getId());
					this.setUserPW(LD.getPw());

					errorCode = loginAvailable(userId, userPW);

					if (errorCode == 1) {
						this.sendToClient("$$1");
					} else if (errorCode == 2) {
						this.sendToClient("$$2");
					} else if (errorCode == 4) {
						this.sendToClient("$$4");
					}
				} catch (Exception e) {

					try {
						EnterpriseUser EU = (EnterpriseUser) msg;

						server.receiveMessageFromClient(EU.getId(), this);

						registerCode = registerAvailable(EU);

						if (registerCode == 1)
							this.sendToClient("%%1"); // id중복
						else
							this.sendToClient("%%0");
					} catch (Exception e2) {
						NormalUser NU = (NormalUser) msg;

						server.receiveMessageFromClient(NU.getId(), this);

						registerCode = registerAvailable(NU);

						if (registerCode == 1)
							this.sendToClient("%%1"); // id중복
						else
							this.sendToClient("%%0");
					}

				}
			}

			if (errorCode == 0)
				this.sendToClient("$$0" + enterpriseKey);
			else if (errorCode == 3)
				this.sendToClient("$$3" + normalKey);

			try {
				Thread.sleep(700);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(getEnterpriseKey()==-1){
				this.sendToClient(DefaultRoomList.getDefaultRoomList().getList());
			}
			else {
				this.sendToClient(DefaultRoomList.getDefaultRoomList().findRegisteredRoom(getEnterpriseKey()));
			}

			while (!readyToStop) {
				// This block waits until it reads a message from the client
				// and then sends it for handling by the server
				msg = input.readObject();
				server.receiveMessageFromClient(msg, this);
			}
		} catch (Exception exception) {

			// System.out.println("CTC error");

			if (!readyToStop) {
				try {
					closeAll();
				} catch (Exception ex) {
				}

				server.clientException(this, exception);
			}
		}
	}

	// METHODS TO BE USED FROM WITHIN THE FRAMEWORK ONLY ----------------

	/**
	 * Closes all connection to the server.
	 *
	 * @exception IOException
	 *                if an I/O error occur when closing the connection.
	 */
	private void closeAll() throws IOException {
		try {
			// Close the socket
			if (clientSocket != null)
				clientSocket.close();

			// Close the output stream
			if (output != null)
				output.close();

			// Close the input stream
			if (input != null)
				input.close();
		} finally {
			// Set the streams and the sockets to NULL no matter what
			// Doing so allows, but does not require, any finalizers
			// of these objects to reclaim system resources if and
			// when they are garbage collected.
			output = null;
			input = null;
			clientSocket = null;
		}
	}

	/**
	 * This method is called by garbage collection.
	 */
	protected void finalize() {
		try {
			closeAll();
		} catch (IOException e) {
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	private int loginAvailable(String id, String pw) {

		EnterpriseUserList EUL = EnterpriseUserList.getEnterpriseUserList();
		NormalUserList NUL = NormalUserList.getNormalUserList();

		EnterpriseUser EU = null;
		NormalUser NU = null;

		EU = EUL.findUser(id);
		NU = NUL.findUser(id);

		if (EU == null) {
			// 사용자 회원정보 없음
			if (NU == null)
				return 1;
			else {
				if (NU.getPassword().equals(pw)) {
					// 일반사용자 로그인 성공
					this.setNormalKey(NU.getKey());
					return 3;
				}
				return 4;
			}
		} else {
			if (EU.getPassword().equals(pw)) {
				// 기업사용자 로그인 성공
				this.setEnterpriseKey(EU.getKey());
				return 0;
			}
		}

		// 기업사용자 비번 틀림
		return 2;
	}

	private int registerAvailable(EnterpriseUser EU) {

		EnterpriseUserList EUL = EnterpriseUserList.getEnterpriseUserList();
		NormalUserList NUL = NormalUserList.getNormalUserList();

		EnterpriseUser temp = null;
		NormalUser temp2 = null;

		temp = EUL.findUser(EU.getId());

		if (temp != null)
			return 1; // 1은 id 중복
		else {
			temp2 = NUL.findUser(EU.getId());

			if (temp2 != null)
				return 1;
			else
				EUL.addUser(EU);
		}

		// System.out.println(temp);

		return 0;
	}
	
	private int registerAvailable(NormalUser NU) {

		EnterpriseUserList EUL = EnterpriseUserList.getEnterpriseUserList();
		NormalUserList NUL = NormalUserList.getNormalUserList();

		EnterpriseUser temp = null;
		NormalUser temp2 = null;

		temp = EUL.findUser(NU.getId());

		if (temp != null)
			return 1; // 1은 id 중복
		else {
			temp2 = NUL.findUser(NU.getId());

			if (temp2 != null)
				return 1;
			else
				NUL.addUser(NU);
		}

		// System.out.println(temp);

		return 0;
	}

	public int getEnterpriseKey() {
		return enterpriseKey;
	}

	public void setEnterpriseKey(int enterpriseKey) {
		this.enterpriseKey = enterpriseKey;
	}

	public int getNormalKey() {
		return normalKey;
	}

	public void setNormalKey(int normalKey) {
		this.normalKey = normalKey;
	}

}
// End of ConnectionToClient class