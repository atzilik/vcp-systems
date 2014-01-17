package gui;

import javax.swing.JPanel;

import DataObjects.ParkingLot;

import client.Client;
/**
 * abstract class which every GUI window inherits from
 * @author Gal
 *
 */
public abstract class AbstractGUIComponent extends JPanel {
	final public static int NUM_OF_PARKINGLOTS = 6;
	protected static Client client;
	/**
	 * our current status of parking lots
	 */
	protected static ParkingLot[] parkinglots;
	
	
	public void ClientSingleton(String host, int port)
	{
		if(client == null){
			client = new Client(host, port);
		}
	}
	
	public void setParkingLots(ParkingLot[] parkinglots){
		this.parkinglots = parkinglots;
	}
}
