package gui;

import javax.swing.JPanel;

import client.Client;

public abstract class AbstractGUIComponent extends JPanel {
	
//	public AbstractGUIComponent(){
//		setLayout(null);
//	}
	
	private int port = 5555;
	private String host = "localhost";
	protected Client client = new Client(host, port);
	
	

}
