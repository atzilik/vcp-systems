package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame{
	private JTextField textField_IP;
	private JTextField textField_Port;
	private String host;
	private int port;
	public ClientGUI() {
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
		textField_Port.setBounds(213, 125, 86, 20);
		getContentPane().add(textField_Port);
		textField_Port.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				host = (textField_IP.getText().equals(""))?"localhost":textField_IP.getText();
				port = (textField_Port.getText().equals(""))?ClientConsole.DEFAULT_PORT:Integer.parseInt(textField_Port.getText());
				
				ClientConsole cc = new ClientConsole(host, port);
				//cc.accept();
				JOptionPane.showMessageDialog(new JPanel(), "Conncetion to the Server was successful");
			}
		});
		btnConnect.setBounds(176, 190, 89, 23);
		getContentPane().add(btnConnect);
	}
	
	public static void main(String[] args){
		ClientGUI cg = new ClientGUI();
		cg.setVisible(true);
	}

}
