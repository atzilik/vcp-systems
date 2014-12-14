package gui;

import java.awt.Font;
import javax.swing.JLabel;


public class Form2 extends AbsForm {
	
	public Form2(INavigator navigator) {
		
		
		super(navigator);
				
		initLogger(Form2.class.getName());
		setLayout(null);
		JLabel lbl_title = new JLabel("שאלון מטרות");		
		lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_title.setBounds(470, 24, 120, 16);
		add(lbl_title);												
		
		JLabel label = new JLabel("- \u05DE\u05D8\u05E8\u05EA \u05E9\u05D0\u05DC\u05D5\u05DF \u05D6\u05D4 \u05D4\u05D9\u05E0\u05D4 \u05DC\u05E2\u05D6\u05D5\u05E8 \u05DC\u05DE\u05E1\u05D2\u05E8\u05EA \u05D4\u05DE\u05D9\u05D5\u05E2\u05D3\u05EA \u05DC\u05DA \u05DC\u05D4\u05DB\u05D9\u05E8\u05DA \u05D1\u05E6\u05D5\u05E8\u05D4 \u05D4\u05D8\u05D5\u05D1\u05D4 \u05D1\u05D9\u05D5\u05EA\u05E8");
		label.setBounds(577, 53, 494, 16);
		add(label);
		
	}
}
