package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckInMenu extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public CheckInMenu() {
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(61, 47, 69, 20);
		getContentPane().add(lblId);
		
		JLabel lblCarid = new JLabel("CarId");
		lblCarid.setBounds(61, 87, 69, 20);
		getContentPane().add(lblCarid);
		
		textField = new JTextField();
		textField.setBounds(118, 44, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 84, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(126, 165, 115, 29);
		getContentPane().add(btnSubmit);
		setVisible(true);
	}

}
