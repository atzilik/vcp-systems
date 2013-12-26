package gui;
import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JFrame;


public class ServerIP extends Frame{
	public ServerIP(int port) throws UnknownHostException{
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(32, 45, 46, 14);
		getContentPane().add(lblIp);
		
		JLabel lblNewLabel = new JLabel(InetAddress.getLocalHost().getHostAddress());
		lblNewLabel.setBounds(88, 45, 113, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(32, 92, 46, 14);
		getContentPane().add(lblPort);
		
		JLabel lblNewLabel_1 = new JLabel(Integer.toString(port));
		lblNewLabel_1.setBounds(88, 92, 46, 14);
		getContentPane().add(lblNewLabel_1);
		setSize(new Dimension(286, 200));
		
	}

}
