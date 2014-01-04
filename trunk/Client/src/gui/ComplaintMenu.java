package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComplaintMenu extends AbstractGUIComponent {
	private JTextField textField;
	private JTextField textField_1;
	public ComplaintMenu(final IGUINavigator navigator) {
		super();	
		setLayout(null);
		
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(22, 9, 40, 14);
		add(lblAnswer);
		
		textField = new JTextField();
		textField.setBounds(67, 6, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCompenstation = new JLabel("Compenstation:");
		lblCompenstation.setBounds(158, 9, 76, 14);
		add(lblCompenstation);
		
		textField_1 = new JTextField();
		textField_1.setBounds(239, 6, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCompenstate = new JButton("Compenstate");
		btnCompenstate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCompenstate.setBounds(330, 5, 97, 23);
		add(btnCompenstate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(173, 48, 89, 23);
		add(btnCancel);
	}
}
