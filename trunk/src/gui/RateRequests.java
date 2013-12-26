package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RateRequests extends Frame {
	public RateRequests() {
		super();
		setTitle("Rate Request");
		
		JButton btnNewButton = new JButton("Accept Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(132, 74, 109, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reject Request");
		btnNewButton_1.setBounds(132, 131, 109, 23);
		getContentPane().add(btnNewButton_1);
	}

}
