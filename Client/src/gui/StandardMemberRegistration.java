package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class StandardMemberRegistration extends FullMemberRegistration {
	private JTextField textField;
	public StandardMemberRegistration(final MainRegistrationMenu mrm) {
		super(mrm);

		JLabel lblEstimateChackOut = new JLabel("Estimate chack out hour");
		lblEstimateChackOut.setBounds(36, 92, 190, 20);
		add(lblEstimateChackOut);

		textField = new JTextField();
		textField.setBounds(219, 89, 104, 26);
		add(textField);
		textField.setColumns(10);

		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(36, 125, 90, 20);
		add(lblParkingLot);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(127, 122, 109, 26);
		add(comboBox);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mrm.setVisible(true);
			}
		});
		btnCancel.setBounds(220, 199, 115, 29);
		add(btnCancel);
	}
}