package gui;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import server.EchoServer;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.UnknownHostException;


public class Server extends Frame{
	private int port;
	private JTextField textField_Port;
	EchoServer sv;
	private JTextField txtRoot;
	private JPasswordField textField_SqlP;
	private JTextField txtVcpprot;

	public Server() {
		super();
		setTitle("Server Setup");
		getContentPane().setLayout(null);

		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(55, 52, 46, 14);
		getContentPane().add(lblPort);

		textField_Port = new JTextField();
		textField_Port.setText(Integer.toString(EchoServer.DEFAULT_PORT));
		textField_Port.setBounds(159, 51, 86, 20);
		getContentPane().add(textField_Port);
		textField_Port.setColumns(10);
		
		JLabel lblSqlUserName = new JLabel("SQL User Name:");
		lblSqlUserName.setBounds(55, 93, 108, 14);
		getContentPane().add(lblSqlUserName);
		
		txtRoot = new JTextField();
		txtRoot.setText("root");
		txtRoot.setBounds(159, 90, 86, 20);
		getContentPane().add(txtRoot);
		txtRoot.setColumns(10);
		
		JLabel lblSqlPassword = new JLabel("SQL Password :");
		lblSqlPassword.setBounds(55, 135, 108, 14);
		getContentPane().add(lblSqlPassword);
		
		textField_SqlP = new JPasswordField();
		textField_SqlP.setText("aeovwsz");
		textField_SqlP.setBounds(159, 132, 86, 20);
		getContentPane().add(textField_SqlP);
		textField_SqlP.setColumns(10);
		
		JLabel lblDbName = new JLabel("DB Name:");
		lblDbName.setBounds(55, 180, 64, 14);
		getContentPane().add(lblDbName);
		
		txtVcpprot = new JTextField();
		txtVcpprot.setText("vcp");
		txtVcpprot.setBounds(159, 177, 86, 20);
		getContentPane().add(txtVcpprot);
		txtVcpprot.setColumns(10);

		JButton btnConnect = new JButton("Start Server");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				port = Integer.parseInt(textField_Port.getText());
				sv = new EchoServer(Integer.parseInt(textField_Port.getText()), txtRoot.getText(), textField_SqlP.getText(), txtVcpprot.getText());
				try 
				{
					sv.listen(); //Start listening for connections
					ServerIP ap = new ServerIP(port);
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
		btnConnect.setBounds(130, 214, 129, 23);
		getContentPane().add(btnConnect);
		
		
	}

}
