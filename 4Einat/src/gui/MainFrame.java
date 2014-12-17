package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The main frame containing all GUI components
 * @author Mor
 *
 */
public class MainFrame extends JFrame implements INavigator {
	
	private static final long serialVersionUID = 4185989615854832714L;
	private Form1 form1;
	private Form2 form2;
	private MainMenu mainMenu;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	

	public MainFrame() {
		
		form1 = new Form1(this);
		form2 = new Form2(this);
		mainMenu = new MainMenu(this);
		add(mainMenu);
		
		setContentPane(mainMenu);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		setSize(dim);				
		setVisible(true);
		setResizable(false);
		pack();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
				
	}

	@Override
	public void toMainMenu() {

		setContentPane(mainMenu);
		repaint();
		pack();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

	@Override
	public void toForm1() {

		setContentPane(form1);
		repaint();
		pack();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
	}

	@Override
	public void toForm2() {
		setContentPane(form2);
		repaint();
		pack();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
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
