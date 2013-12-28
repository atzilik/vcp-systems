// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import gui.CustomerComplaintMenu;
import gui.CustomerMenu;
import gui.Login;
import gui.ReservationMenu;
import gui.WorkerMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.Customer;
import logic.Worker;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
	//Instance variables **********************************************

	/**
	 * The interface type variable.  It allows the implementation of 
	 * the display method in the client.
	 */
	ChatIF clientUI; 
	private boolean login;


	//Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host The server to connect to.
	 * @param port The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) 
			throws IOException 
			{
		super(host, port); //Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
		login = false;
			}


	//Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) 
	{
		//    clientUI.display(msg.toString());
		if (msg instanceof String)
		{
			switch((String)msg)
			{
			case "No Entery": {
				login = false;
				JOptionPane.showMessageDialog(new JPanel(), "Wrong user name or password.", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}
			case "Insert reservation": {
				JOptionPane.showMessageDialog(new JPanel(), "Reservation taken.");
				break;
			}
			}	
		}
		else if (msg instanceof ArrayList)
		{
			ArrayList<Object> list = (ArrayList<Object>) msg;
			if (list.get(0).equals("Get Parking Lots"))
			{
				ReservationMenu rm = new ReservationMenu((CustomerMenu)list.get(1),(Map<String, Integer>)list.get(2));
				rm.setVisible(true);
			}
		}
		else if (msg instanceof Customer)
		{
			login = true;
			CustomerMenu cm = new CustomerMenu((Customer)msg);
			cm.setVisible(true);
		}

		else if (msg instanceof Worker)
		{
			login = true;
			WorkerMenu wr = new WorkerMenu((Worker)msg);
			wr.setVisible(true);
		}
	}

	/**
	 * This method handles all data coming from the UI            
	 *
	 * @param message The message from the UI.    
	 */
	public void handleMessageFromClientUI(Object message)
	{
		try
		{
			sendToServer(message);
		}
		catch(IOException e)
		{
			clientUI.display
			("Could not send message to server.  Terminating client.");
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit()
	{
		try
		{
			closeConnection();
		}
		catch(IOException e) {}
		System.exit(0);
	}


	public boolean isLogin() {
		return login;
	}


	public void setLogin(boolean login) {
		this.login = login;
	}

}
//End of ChatClient class
