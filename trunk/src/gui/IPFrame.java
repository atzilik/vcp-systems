package gui;
import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class IPFrame extends JFrame{
	public IPFrame(int port) throws UnknownHostException{
		getContentPane().setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(32, 45, 46, 14);
		getContentPane().add(lblIp);
		
		JLabel lblNewLabel = new JLabel(InetAddress.getLocalHost().getHostAddress());
		lblNewLabel.setBounds(88, 45, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(32, 92, 46, 14);
		getContentPane().add(lblPort);
		
		JLabel lblNewLabel_1 = new JLabel(Integer.toString(port));
		lblNewLabel_1.setBounds(88, 92, 46, 14);
		getContentPane().add(lblNewLabel_1);
		setSize(new Dimension(200, 200));
	}

}
