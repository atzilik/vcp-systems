package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RateRequests extends AbstractGUIComponent {
	public RateRequests(final IGUINavigator navigator) {
		super();	
		
		JButton btnNewButton = new JButton("Accept Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setLayout(null);
		btnNewButton.setBounds(114, 5, 109, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reject Request");
		btnNewButton_1.setBounds(228, 5, 107, 23);
		add(btnNewButton_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(178, 63, 89, 23);
		add(btnCancel);
	}

}
