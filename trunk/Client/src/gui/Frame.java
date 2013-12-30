package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import client.Client;

public class Frame extends JFrame {
	private static Client client;
	
	public Frame(){
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		Frame.client = client;
	}

	
}
