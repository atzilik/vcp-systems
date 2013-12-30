package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CancelReservation extends Frame{
	private CustomerMenu cm;
	private JTextField textField_ResNum;
	public CancelReservation(final CustomerMenu cm) {
		super();
		this.cm = cm;
	
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnCancel.setBounds(232, 175, 115, 29);
		getContentPane().add(btnCancel);
		
		JLabel lblCarid = new JLabel("Reservation Number");
		lblCarid.setBounds(10, 75, 146, 20);
		getContentPane().add(lblCarid);
		
		textField_ResNum = new JTextField();
		textField_ResNum.setBounds(166, 72, 146, 26);
		getContentPane().add(textField_ResNum);
		textField_ResNum.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[3];
				arr[0] = "Cancel Reservation";
				arr[1] = textField_ResNum.getText();
				arr[2] = cm.getCst().getId();
				Login.getCc().accept(arr);
			}
		});
		btnSubmit.setBounds(62, 175, 115, 29);
		getContentPane().add(btnSubmit);
	}
}
