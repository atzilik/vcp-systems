package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import client.ClientConsole;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame{
	private ClientConsole cc;
	private JTextField textField_IP;
	private JTextField textField_Port;
	public Client() {
		
		setSize(new Dimension(400, 300));
		setTitle("Server Connection");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(93, 74, 70, 14);
		getContentPane().add(lblServerIp);
		
		JLabel lblServerPort = new JLabel("Server Port:");
		lblServerPort.setBounds(93, 128, 70, 14);
		getContentPane().add(lblServerPort);
		
		textField_IP = new JTextField();
		textField_IP.setBounds(213, 71, 86, 20);
		getContentPane().add(textField_IP);
		textField_IP.setColumns(10);
		
		textField_Port = new JTextField();
		textField_Port.setText(Integer.toString(ClientConsole.DEFAULT_PORT));
		textField_Port.setBounds(213, 125, 86, 20);
		getContentPane().add(textField_Port);
		textField_Port.setColumns(10);
		
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cc = new ClientConsole(textField_IP.getText(),Integer.parseInt(textField_Port.getText()));
				JOptionPane.showMessageDialog(new JPanel(), "Conncetion to the Server was successful");
				setVisible(false);
				Login lg = new Login(cc);
				
			}
		});
		
		btnConnect.setBounds(176, 190, 89, 23);
		getContentPane().add(btnConnect);
	}
	
	public static void main(String[] args){
		Client cg = new Client();
		cg.setVisible(true);
	}

}
