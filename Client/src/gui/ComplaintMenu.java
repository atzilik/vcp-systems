package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComplaintMenu extends AbstractGUIComponent {
	private JTextField textField;
	private JTextField textField_1;
	public ComplaintMenu() {
		super();	
		
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(58, 40, 46, 14);
		add(lblAnswer);
		
		textField = new JTextField();
		textField.setBounds(114, 37, 120, 80);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCompenstation = new JLabel("Compenstation:");
		lblCompenstation.setBounds(58, 141, 76, 14);
		add(lblCompenstation);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 138, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCompenstate = new JButton("Compenstate");
		btnCompenstate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCompenstate.setBounds(145, 217, 102, 23);
		add(btnCompenstate);
	}

}
