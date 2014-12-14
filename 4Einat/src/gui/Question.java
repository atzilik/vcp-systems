package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Question extends JPanel {

	private static final long serialVersionUID = 3700041806039633626L;
	private String qText;
	private String qExtraText;
	private QType qType;
	private ArrayList<Component> comps;
	
	public enum QType { FREE_TEXT, SINGLE_SELECTION, MULTI_SELECTION, BIRTH_DATE, FREE_TEXT_RANGE, FREE_TEXT_MULTI }
	
	public Question(String qText, QType qType , Component comp) {
		
		setLayout(new FlowLayout());
		this.qText = qText;
		this.qType = qType;
		comps = new ArrayList<Component>();
		comps.add(comp);
		add(comp);		
		add(new JLabel(qText));
		
	}
	
	public Question(String qText, QType qType , Component comp, String qExtraText) {
		
		setLayout(new FlowLayout());
		this.qText = qText;
		this.qType = qType;		
		this.qExtraText = qExtraText;
		comps = new ArrayList<Component>();
		comps.add(comp);
		add(new JLabel(qExtraText));
		add(comp);
		add(new JLabel(qText));		
		
	}
	
	public Question(String qText, QType qType, Component comp1, Component comp2, String qExtraText) {
		
		setLayout(new FlowLayout());
		this.qText = qText;
		this.qType = qType;		
		this.qExtraText = qExtraText;
		comps = new ArrayList<Component>();
		comps.add(comp1);
		comps.add(comp2);		
		add(comp2);
		add(new JLabel(qExtraText));		
		add(comp1);
		add(new JLabel(qText));
		
	}
	
	public Question(String qText, QType qType, ArrayList<Component> comps) {
		
		setLayout(new FlowLayout());
		this.qText = qText;
		this.qType = qType;	
		this.comps = comps;
		
		for(Component comp: comps) {
			add(comp);
		}
		
		JLabel label = new JLabel(qText);	
		add(label);
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
	
	public ArrayList<Component> getQcomponents() {		
		return comps;
	}
	
	


	
	
	
}
