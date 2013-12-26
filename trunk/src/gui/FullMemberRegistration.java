package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class FullMemberRegistration extends JFrame{
	private MainRegistrationMenu mrm;
	private JTextField textField;
	private JTextField textField_1;
	public FullMemberRegistration(final MainRegistrationMenu mrm) {
		this.mrm = mrm;
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("39px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("69px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("146px"),},
			new RowSpec[] {
				RowSpec.decode("21px"),
				RowSpec.decode("26px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				RowSpec.decode("116px"),
				RowSpec.decode("29px"),}));
		
		JLabel lblCid = new JLabel("Cid");
		getContentPane().add(lblCid, "4, 2, fill, center");
		
		textField = new JTextField();
		getContentPane().add(textField, "6, 2, fill, fill");
		textField.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start date");
		getContentPane().add(lblStartDate, "4, 4, fill, center");
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "6, 4, fill, fill");
		textField_1.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnCreate, "2, 6, 3, 1, fill, fill");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mrm.setVisible(true);
			}
		});
		getContentPane().add(btnCancel, "6, 6");
		setVisible(true);
	}

}
