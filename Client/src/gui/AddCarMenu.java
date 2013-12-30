package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCarMenu extends Frame{
	private JTextField textField;
	public AddCarMenu() {
		super();
		JLabel lblCarid = new JLabel("CarId");
		lblCarid.setBounds(53, 60, 69, 20);
		getContentPane().add(lblCarid);
		
		textField = new JTextField();
		textField.setBounds(132, 57, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnApplay = new JButton("Applay");
		btnApplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApplay.setBounds(52, 150, 115, 29);
		getContentPane().add(btnApplay);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClose.setBounds(207, 150, 115, 29);
		getContentPane().add(btnClose);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		setVisible(true);
	}

}