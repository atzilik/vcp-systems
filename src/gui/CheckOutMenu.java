package gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckOutMenu extends JFrame{
	private CustomerMenu cm;
	private JTextField textField_1;
	public CheckOutMenu(final CustomerMenu cm) {
		this.cm = cm;
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
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
		btnSubmit.setBounds(61, 165, 115, 29);
		getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnCancel.setBounds(226, 165, 115, 29);
		getContentPane().add(btnCancel);
		setVisible(true);
	}

}
