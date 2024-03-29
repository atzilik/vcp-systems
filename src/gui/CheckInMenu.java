package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckInMenu extends Frame{
	private CustomerMenu cm;
	private JTextField textField_1;
	public CheckInMenu(final CustomerMenu cm) {
		super();
		this.cm = cm;
		
		JLabel lblCarid = new JLabel("CarId");
		lblCarid.setBounds(61, 87, 69, 20);
		getContentPane().add(lblCarid);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 84, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(51, 165, 115, 29);
		getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnCancel.setBounds(216, 165, 115, 29);
		getContentPane().add(btnCancel);
	}

}
