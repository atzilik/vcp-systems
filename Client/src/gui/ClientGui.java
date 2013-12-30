package gui;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import client.Client;

public class ClientGui extends AbstractGUIComponent {
	private Client client;
	private JTextField textField_IP;
	private JTextField textField_Port;
	public ClientGui() {
		super();
						
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(93, 74, 70, 14);
		add(lblServerIp);
		
		JLabel lblServerPort = new JLabel("Server Port:");
		lblServerPort.setBounds(93, 128, 70, 14);
		add(lblServerPort);
		
		textField_IP = new JTextField();
		textField_IP.setBounds(213, 71, 86, 20);
		add(textField_IP);
		textField_IP.setColumns(10);
		
		
		textField_Port = new JTextField();
		textField_Port.setText(Integer.toString(Client.DEFAULT_PORT));
		textField_Port.setBounds(213, 125, 86, 20);
		add(textField_Port);
		textField_Port.setColumns(10);
		
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = new Client(textField_IP.getText(),Integer.parseInt(textField_Port.getText()));
				JOptionPane.showMessageDialog(new JPanel(), "Conncetion to the Server was successful");
				setVisible(false);
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		
		btnConnect.setBounds(176, 190, 89, 23);
		add(btnConnect);
	}
	
	public static void main(String[] args){
		ClientGui cg = new ClientGui();
		cg.setVisible(true);
	}

}
