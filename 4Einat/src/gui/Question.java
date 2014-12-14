package gui;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Question extends JPanel {

	private static final long serialVersionUID = 3700041806039633626L;
	private String qText;
	private String qExtraText;
	private QType qType;
	private Component comp;
	
	public enum QType { FREE_TEXT, SINGLE_SELECTION, MULTI_SELECTION, BIRTH_DATE }
	
	public Question(String qText, QType qType , Component comp) {
		
		setLayout(new FlowLayout());
		this.qText = qText;
		this.qType = qType;
		this.comp = comp;
		add(comp);
		add(new JLabel(qText));
		
	}
	
	public Question(String qText, QType qType , Component comp, String qExtraText) {
		
		setLayout(new FlowLayout());
		this.qText = qText;
		this.qType = qType;		
		this.qExtraText = qExtraText;
		this.comp = comp;
		add(new JLabel(qExtraText));
		add(comp);
		add(new JLabel(qText));		
		
	}

	public String getQtext() {
		return qText;
	}
	
	public String qExtraText() {
		return qExtraText;
	}

	public QType getqType() {
		return qType;
	}
	
	public Component getQcomponent() {		
		return comp;
	}
	
	


	
	
	
}
