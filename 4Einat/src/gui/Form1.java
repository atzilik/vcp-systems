package gui;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * The 1st form 
 * @author Mor
 *
 */
public class Form1 extends AbsForm {
	
	private static final long serialVersionUID = 4771432968796203686L;				
	private final static String title = "שאלון היכרות";
	private final static String subTitle = "כותרת משנית -";
	
	public Form1(INavigator navigator) {
		
		super(navigator, title, subTitle);				
		
			
	}
}
