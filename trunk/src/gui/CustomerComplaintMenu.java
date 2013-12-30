package gui;


import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class CustomerComplaintMenu extends Frame{
	private CustomerMenu cm;
	JTextArea textArea;
	
	public CustomerComplaintMenu(final CustomerMenu cm){
		super();
		this.cm = cm;
	
		textArea = new JTextArea();
		textArea.setBounds(128, 51, 196, 101);
		getContentPane().add(textArea);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnCancel.setBounds(209, 177, 115, 29);
		getContentPane().add(btnCancel);

		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(37, 51, 69, 20);
		getContentPane().add(lblReason);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rnd = new Random();
				String[] arr = new String[4];
				arr[0] = "SubmitComplaint";
				arr[1] = Integer.toString(100000 + rnd.nextInt(900000));
				arr[2] = cm.getCst().getId();
				arr[3] = textArea.getText();
				Login.getCc().accept(arr);
			}
		});
		btnSubmit.setBounds(43, 177, 115, 29);
		getContentPane().add(btnSubmit);
	}

}
