package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckResarvationStatus extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public CheckResarvationStatus() {
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
	
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(130, 187, 115, 29);
		getContentPane().add(btnCancel);
		
		JButton btnOpenComplaintMenu = new JButton("Open complaint menu");
		btnOpenComplaintMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpenComplaintMenu.setBounds(89, 131, 199, 29);
		getContentPane().add(btnOpenComplaintMenu);
		
		JLabel lblCid = new JLabel("Cid");
		lblCid.setBounds(36, 36, 69, 20);
		getContentPane().add(lblCid);
		
		JLabel lblCarid = new JLabel("CarId");
		lblCarid.setBounds(36, 75, 69, 20);
		getContentPane().add(lblCarid);
		
		textField = new JTextField();
		textField.setBounds(89, 33, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 72, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

}
