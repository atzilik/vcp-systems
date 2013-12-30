package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KioskReservationMenu extends AbstractGUIComponent {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public KioskReservationMenu() {
		super();
		
		JLabel lblCid = new JLabel("Cid");
		lblCid.setBounds(24, 28, 69, 20);
		add(lblCid);
		
		JLabel lblCarid = new JLabel("CarId");
		lblCarid.setBounds(24, 64, 69, 20);
		add(lblCarid);
		
		JLabel lblEstimateChackOut = new JLabel("Estimate chack out hour");
		lblEstimateChackOut.setBounds(24, 100, 178, 20);
		add(lblEstimateChackOut);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(24, 136, 69, 20);
		add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(86, 25, 146, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 61, 146, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(217, 97, 146, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(86, 133, 146, 26);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBounds(129, 183, 115, 29);
		add(btnCreate);
	}


}
