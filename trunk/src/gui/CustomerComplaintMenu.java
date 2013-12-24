package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerComplaintMenu extends JFrame{
	private CustomerMenu cm;
	
	public CustomerComplaintMenu(final CustomerMenu cm){
		this.cm = cm;
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(128, 51, 196, 101);
		getContentPane().add(textArea);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnCancel.setBounds(37, 179, 115, 29);
		getContentPane().add(btnCancel);

		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(37, 51, 69, 20);
		getContentPane().add(lblReason);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(209, 179, 115, 29);
		getContentPane().add(btnSubmit);
		setVisible(true);
	}

}
