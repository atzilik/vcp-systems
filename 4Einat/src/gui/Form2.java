package gui;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *The 2nd form
 * @author Mor
 */
public class Form2 extends AbsForm {
	

	private static final long serialVersionUID = -1036859008720364088L;
	private static final String title = "שאלון מטרות";
	private static final String subTitle = "- מטרת שאלון זה הינה לקבל תמונה כללית על מטרותייך העתידיות ,\n בתחומים שונים  וכן לסכם את המטרות עלייהן עבדת באשפוז הנוכחי.";
	
	public Form2(INavigator navigator) {
		
		
		super(navigator,title,subTitle);
														
		
		
	}
}
