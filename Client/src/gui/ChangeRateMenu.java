package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeRateMenu extends AbstractGUIComponent {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public ChangeRateMenu(final IGUINavigator navigator) {
		super();
		setLayout(null);
						
		JLabel lblNewLabel = new JLabel("Occasional Parking Rate:");
		lblNewLabel.setBounds(29, 55, 119, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Reserved Parking Rate:");
		lblNewLabel_1.setBounds(29, 89, 114, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Standard Membership Rate:");
		lblNewLabel_2.setBounds(29, 124, 134, 14);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(176, 86, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(176, 52, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(176, 121, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSend.setBounds(191, 169, 57, 23);
		add(btnSend);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(258, 169, 89, 23);
		add(btnCancel);
	}

}
