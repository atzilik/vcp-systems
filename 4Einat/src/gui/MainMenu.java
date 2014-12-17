package gui;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainMenu extends JPanel {
	
	private INavigator navigator;
	
	public MainMenu(final INavigator navigator) {
		
		this.navigator = navigator;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));					
			
		JLabel title = new JLabel("תיקיית מעבר");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setAlignmentX(CENTER_ALIGNMENT);		

		
		
//		JPanel idPanel = new JPanel();
//		idPanel.setLayout(new FlowLayout());
//		idPanel.setPreferredSize(new Dimension(200,200));
//		JTextField idTextField = new JTextField("הזן את מספר תעודת הזהות של המטופל");
//		JLabel idLabel = new JLabel("מספר ת.ז:");
//		idPanel.add(idTextField);
//		idPanel.add(idLabel);	
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		Form1 form1 = new Form1(navigator);
		tabbedPane.addTab("1 שאלון היכרות", form1);
		Form2 form2 = new Form2(navigator);
		tabbedPane.addTab("2 שאלות מטרות", form2);
		tabbedPane.setPreferredSize(tabbedPane.getMaximumSize());

			
		add(title);		
		JPanel space = new JPanel();		
		space.setPreferredSize(new Dimension(100,20));		
		space.setMaximumSize(space.getPreferredSize());
		add(space);			
		add(tabbedPane);
					
		
	}

}
