package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BirthDatePanel extends JPanel {

	private JComboBox<String> year;
	private JComboBox<String> month;
	private JComboBox<String> day;
	private int numDays = 1;
    private GregorianCalendar cal =  (GregorianCalendar) GregorianCalendar.getInstance(); ;
	
	public BirthDatePanel() {
		
		setLayout(new GridLayout(1,0));		
		
		day = new JComboBox<String>();		
		day.setEnabled(false);					
		
		add(day);
		add(new JLabel("יום:"));
		add(getMonthsComboBox());
		add(new JLabel("חודש:"));
		add(getYearsComboBox());		
		add(new JLabel("שנה:"));
	
	}

	public String getBirthDate() {
		
		String sYear = (String) year.getSelectedItem();
		String sMonth = (String) month.getSelectedItem();
		String sDay = (String) day.getSelectedItem();
		
		return sDay+"/"+sMonth+"/"+sYear;
	}
	
	private JComboBox<String> getYearsComboBox() {
		
				// Creating years
				int numYears = (Calendar.getInstance().get(Calendar.YEAR) - 1900) + 1; 
				String[] years = new String[numYears];		
				Integer iYear = 1900;
				for(int i=0;i<years.length;i++)
				{
					years[i] = iYear.toString();
					iYear++;			
				}
				
				year = new JComboBox<String>(years);
				
				year.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
										
						month.setEnabled(true);
						
					}
					
				});
				
				return year;
		
	}

	private JComboBox<String> getMonthsComboBox() {
		
		// Creating months
		String[] months = new String[12]; 			
		for(int i=0;i<months.length;i++) 
			months[i] = new Integer(i+1).toString();		
		
		month = new JComboBox<String>(months);
		month.setEnabled(false);		
		
		month.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sYear = (String) year.getSelectedItem();
				String sMonth = (String) month.getSelectedItem();
				
				int iYear = Integer.parseInt(sYear);
				int iMonth = Integer.parseInt(sMonth);
				
				cal = new GregorianCalendar();
				cal.clear();
				cal.set(Calendar.YEAR, iYear);
				cal.set(Calendar.MONTH, iMonth-1);				
			
				numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);				
				setDaysComboBox();			
			}
		});
		return month;
	}

	private void setDaysComboBox() {
		

		String[] days = new String[numDays];
		day.removeAllItems();
		for(int i=0;i<numDays;i++)
		{
			days[i] = new Integer(i+1).toString();
			day.addItem(days[i]);
		}
		
		day.setEnabled(true);		

	}
}
