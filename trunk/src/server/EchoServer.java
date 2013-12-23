package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import gui.CustomerMenu;
import gui.Server;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.corba.se.pept.transport.ConnectionCache;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
	//Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	private Connection conn;
	private String sqlUser;
	private String sqlPass;
	private String dbName;

	//Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port, String sqlU, String sqlP, String dbName) 
	{
		super(port);
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error*/}

		try 
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName,sqlU,sqlP);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) 
		{/* handle any errors*/
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}


	//Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient
	(Object msg, ConnectionToClient client)
	{
		//	    System.out.println("Message received: " + msg + " from " + client);
		//	    this.sendToAllClients(msg);

		String[] command = (String[])msg;
		//		switch (command[0])
		//		{
		//		case "check login": checkLogin(command, conn);
		//		}
		if (command[0].equals("check login"))
		{
			checkLogin(command, conn, client);
		}
	}


	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server starts listening for connections.
	 */
	protected void serverStarted()
	{
		System.out.println
		("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server stops listening for connections.
	 */
	protected void serverStopped()
	{
		System.out.println
		("Server has stopped listening for connections.");
	}

	//Class methods ***************************************************

	/**
	 * This method is responsible for the creation of 
	 * the server instance (there is no UI in this phase).
	 *
	 * @param args[0] The port number to listen on.  Defaults to 5555 
	 *          if no argument is entered.
	 */

	public void checkLogin(String[] op, Connection con, ConnectionToClient client){
		try 
		{
			String query = "SELECT * FROM $tableName WHERE UserName=? and Password=?;";
			PreparedStatement ps = con.prepareStatement(query.replace("$tableName", op[3].equals("yes")?"customers":"workers"));
			ps.setString(1, op[1]);
			ps.setString(2, op[2]);

			ResultSet rs = ps.executeQuery();

			if (rs.first() == false)
			{
				sendToClient(client, "No Entery");
			}
			else
			{
				sendToClient(client, "Login Success");
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void sendToClient(ConnectionToClient client, String msg){
		try {
			client.sendToClient(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) 
	{
		Server sg = new Server();
		sg.setVisible(true);
	}

}
//End of EchoServer class
