package Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Server.*;
import Common.*;

public class ServerConsole extends EchoServer implements UserIF {

	public ServerConsole(int port) {
		super(port);
	}

	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(
					new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				if (message.substring(0, 1).equals("#")) {
					String[] command = message.substring(1).split(" ");
					if (command[0].equals("quit")) {
						System.exit(0);
					} else if (command[0].equals("stop")) {
						stopListening();
					} else if (command[0].equals("close")) {
						close();
					} else if (command[0].equals("setport")) {
						if (!isListening()) {
							setPort(Integer.parseInt(command[1]));
							System.out.println("Port is set to " + command[1]);
						} else
							System.out.println("Server is Running!");
					} else if (command[0].equals("start")) {
						if (!isListening()) {
							listen();
						} else
							System.out.println("Server is Running!");
					} else if (command[0].equals("getport")) {
						System.out.println(getPort());
					} else {
						System.out.println("Unknown Command");
					}
				} else {
					display(message);
					sendToAllClients("SERVER MSG > " + message);
				}
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		System.out.println("SERVER MSG > " + message);

	}

}