package gui;

import javax.swing.JPanel;

import DataObjects.ParkingLot;

import client.Client;

public abstract class AbstractGUIComponent extends JPanel {
	final public static int NUM_OF_PARKINGLOTS = 6;
	protected static Client client;
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
