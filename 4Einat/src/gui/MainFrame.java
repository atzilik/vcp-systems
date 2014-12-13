package gui;

import javax.swing.JFrame;

/**
 * The main frame containing all GUI components
 * @author Mor
 *
 */
public class MainFrame extends JFrame implements INavigator {


	
	private static final long serialVersionUID = 4185989615854832714L;
	private Questioneere1 form1;
	

	public MainFrame() {
		
		form1 = new Questioneere1(this, "C:\\questions.csv");
		add(form1);
		
		setSize(1024, 768);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
				
	}

	@Override
	public void nextScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		
	}

}
