package gui;

import javax.swing.JFrame;

/**
 * The main frame containing all GUI components
 * @author Mor
 *
 */
public class MainFrame extends JFrame implements INavigator {


	
	private static final long serialVersionUID = 4185989615854832714L;
	private Form1 form1;
	

	public MainFrame() {
		
		form1 = new Form1(this);
		add(form1);
		
		setSize(1024, 768);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
				
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toForm1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toForm2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toForm3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toForm4() {
		// TODO Auto-generated method stub
		
	}

}
