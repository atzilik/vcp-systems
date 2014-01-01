package gui;

import javax.swing.JPanel;

import client.Client;

public abstract class AbstractGUIComponent extends JPanel {
	
	private int port = 5555;
	private String host = "localhost";
	protected static Client client;
	
	
	public void ClientSingleton(String host, int port)
	{
		if(client == null){
			client = new Client(host, port);
		}
	}
}
