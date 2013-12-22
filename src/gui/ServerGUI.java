package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import server.EchoServer;

import client.ClientConsole;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.UnknownHostException;


public class ServerGUI extends JFrame{
	private int port;
	private JTextField textField_Port;
	EchoServer sv;

	public ServerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(289, 200));
		setLocationRelativeTo(null);
		setTitle("Server Setup");
		getContentPane().setLayout(null);

		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPort.setBounds(55, 52, 46, 14);
		getContentPane().add(lblPort);

		textField_Port = new JTextField();
		textField_Port.setText("");
		textField_Port.setBounds(111, 51, 86, 20);
		getContentPane().add(textField_Port);
		textField_Port.setColumns(10);

		JButton btnConnect = new JButton("Start Server");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				port = (textField_Port.getText().equals(""))?EchoServer.DEFAULT_PORT:Integer.parseInt(textField_Port.getText());
				sv = new EchoServer(port);
				try 
				{
					sv.listen(); //Start listening for connections
					IPFrame ap = new IPFrame(port);
					ap.setVisible(true);
				} 
				catch (UnknownHostException ex){
					ex.printStackTrace();
				}
				catch (Exception ex) {
					System.out.println("ERROR - Could not listen for clients!");
					System.exit(1);
				}

			}
		});
		btnConnect.setBounds(96, 109, 129, 23);
		getContentPane().add(btnConnect);
	}

}
