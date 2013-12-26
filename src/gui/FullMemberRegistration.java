package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FullMemberRegistration extends JFrame{
	protected MainRegistrationMenu mrm;
	private JTextField textField;
	private JTextField textField_1;
	public FullMemberRegistration(final MainRegistrationMenu mrm) {
		this.mrm = mrm;
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);

		JLabel lblCid = new JLabel("Cid");
		lblCid.setBounds(39, 24, 69, 20);
		getContentPane().add(lblCid);

		textField = new JTextField();
		textField.setBounds(123, 21, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblStartDate = new JLabel("Start date");
		lblStartDate.setBounds(39, 60, 69, 20);
		getContentPane().add(lblStartDate);

		textField_1 = new JTextField();
		textField_1.setBounds(123, 57, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBounds(57, 199, 115, 29);
		getContentPane().add(btnCreate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mrm.setVisible(true);
			}
		});
		btnCancel.setBounds(220, 199, 115, 29);
		getContentPane().add(btnCancel);
		setVisible(true);
	}

}