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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.CeoWorker;
import logic.CustomerService;
import logic.FullMember;
import logic.ParkingLotManager;
import logic.STDCustomer;
import logic.STDMember;
import logic.Worker;
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
	private Connection con;
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName,sqlU,sqlP);
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
		if (msg instanceof String[])
		{
			String[] command = (String[])msg;
			switch (command.clone()[0])
			{
			case "check login": {
				checkLogin(command, client);
				break;
			}
			case "Insert reservation": {
				insertReservation(client, command);
				break;
			}
			}
		}
		else if (msg instanceof ArrayList)
		{
			ArrayList<Object> list = (ArrayList<Object>) msg;
			if (list.get(0).equals("Get Parking Lots"))
			{
				getParkingLotsId(client, (CustomerMenu)list.get(1));
			}
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

	public void checkLogin(String[] op, ConnectionToClient client){
		try 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE userName=? and password=?;");
			ps.setString(1, op[1]);
			ps.setString(2, op[2]);
			ResultSet user = ps.executeQuery();

			if (user.isBeforeFirst() == false)
				//user doesn't exist
			{
				sendToClient(client, "No Entery");
			}
			else
			{
				user.next();
				if (user.getString(3) != null)
					//worker
				{
					loadWorker(client, user.getString(3), user.getString(5));
				}
				else
					//customer
				{
					loadCustomer(client, user.getString(4), user.getString(5));
				}
			}
			user.close();
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void sendToClient(ConnectionToClient client, Object msg){
		try {
			client.sendToClient(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadCustomer(ConnectionToClient client, String customerId, String customerType) throws SQLException{
		String tableName = null;
		switch(Integer.parseInt(customerType))
		//1 - regular, 2 - standard, 3 - full
		{
		case 1: tableName = "customers";break;
		case 2: tableName = "std_members";break;
		case 3: tableName = "full_members";break;
		}
		String query = "SELECT * FROM $tableName WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query.replace("$tableName", tableName));
		ps.setString(1, customerId);
		ResultSet cst = ps.executeQuery();
		cst.next();
		switch(Integer.parseInt(customerType))
		//1 - regular, 2 - standard, 3 - full
		{
		case 1: {
			STDCustomer stdCst = new STDCustomer(cst.getString(1), cst.getString(2), cst.getString(3), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(7));
			sendToClient(client, stdCst);
			break;
		}
		case 2: {
			STDMember stdMem = new STDMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(10), cst.getInt(8), cst.getString(9));
			sendToClient(client, stdMem);
			break;
		}
		case 3: {
			FullMember fMem = new FullMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(8));
			sendToClient(client, fMem);
			break;
		}
		}
		cst.close();

	}
	
	/**
	 * loads worker details
	 * @param client
	 * @param workID
	 * @param type: 1 - CEO, 2 - parking Lot Manager, 3 - worker, 4- customer service
	 * @throws SQLException
	 */
	public void loadWorker(ConnectionToClient client, String workID, String type) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM workers WHERE id=?");
		ps.setString(1, workID);
		ResultSet wkr = ps.executeQuery();
		wkr.next();
		switch(Integer.parseInt(type))
		{
		case 1: {
			CeoWorker ceowkr = new CeoWorker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			sendToClient(client, ceowkr);
			break;
		}
		case 2: {
			ParkingLotManager plmgr = new ParkingLotManager(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			sendToClient(client, plmgr);
			break;
		}
		case 3: {
			Worker wokr = new Worker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			sendToClient(client, wokr);
			break;
		}
		case 4: {
			CustomerService cs = new CustomerService(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			sendToClient(client, cs);
			break;
			}
		}
		wkr.close();

	}

	public void getParkingLotsId(ConnectionToClient client, CustomerMenu cm) 
	{
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parkinglots");
			ResultSet rs = ps.executeQuery();
			List<Object> parkingLots = new ArrayList<Object>();
			parkingLots.add("Get Parking Lots");
			parkingLots.add(cm);
			Map<String,Integer> mp = new HashMap<String,Integer>();
			while(rs.next())
			{
				mp.put(rs.getString(2), rs.getInt(1));
			}
			parkingLots.add(mp);
			rs.close();
			sendToClient(client, parkingLots);
		}catch (SQLException e) {e.printStackTrace();}

	}

	public void insertReservation(ConnectionToClient client, String[] details)
	{
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO reservations (reservationId,carId,customerId,parkingLotId,estCinDate,estCinHour,estCotDate,estCotHour) VALUES(?,?,?,?,?,?,?,?);");
			for (int i = 1 ; i < details.length - 1; i++)
			{
				ps.setString(i, details[i]);
			}
			ps.executeUpdate();
			ps.close();
			sendToClient(client,"Insert reservation");
			
		}catch(SQLException e) {e.printStackTrace();}
	}

	public static void main(String[] args) 
	{
		Server sg = new Server();
		sg.setVisible(true);
	}

}
//End of EchoServer class