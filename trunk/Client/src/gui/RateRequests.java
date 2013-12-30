package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RateRequests extends AbstractGUIComponent {
	public RateRequests() {
		super();	
		
		JButton btnNewButton = new JButton("Accept Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(132, 74, 109, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reject Request");
		btnNewButton_1.setBounds(132, 131, 109, 23);
		add(btnNewButton_1);
	}

}
