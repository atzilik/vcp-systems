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
		
		JLabel label = new JLabel("- מטרת שאלון זה הינה לקבל תמונה כללית על מטרותייך העתידיות ,\n בתחומים שונים  וכן לסכם את המטרות עלייהן עבדת באשפוז הנוכחי.");
		label.setBounds(377, 54, 825, 16);
		add(label);
		
		JLabel label_1 = new JLabel("- \u05DE\u05D8\u05E8\u05D4 \u05D4\u05D9\u05D0 \u05DE\u05E9\u05D4\u05D5 \u05E9\u05E8\u05D5\u05E6\u05D9\u05DD \u05D1\u05D4\u05E9\u05D2\u05EA\u05D5, \u05EA\u05DB\u05DC\u05D9\u05EA \u05D0\u05D5 \u05DB\u05D5\u05D5\u05E0\u05D4. \u05E2\u05DC \u05E4\u05D9 \u05E8\u05D5\u05D1, \u05DE\u05E6\u05D1 \u05D1\u05D5 \u05D4\u05D5\u05D2\u05E9\u05DE\u05D4 \u05D4\u05DE\u05D8\u05E8\u05D4 \u05DE\u05DB\u05D5\u05E0\u05D4 \u05D4\u05E6\u05DC\u05D7\u05D4.");
		label_1.setBounds(529, 90, 618, 16);
		add(label_1);
		
		qPanel.setBounds(0, 150, 1000, 500);
		
	}
}
