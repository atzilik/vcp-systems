package gui;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;

import DataObjects.Worker;
import javax.swing.JLabel;

public class CEOChoosePLReport extends AbstractGUIComponent {
	private Map<String,Integer> parkingLots;	
	private final JComboBox comboBoxParkLot;
	
	public CEOChoosePLReport(final IGUINavigator navigator,Map<String,Integer> mp, final Worker worker) {
		setLayout(null);
		this.parkingLots=mp;
		comboBoxParkLot = new JComboBox();
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		comboBoxParkLot.setBounds(116, 6, 136, 20);
		add(comboBoxParkLot);
		comboBoxParkLot.setBounds(21, 62, 138, 20);
		add(comboBoxParkLot);
		
		JLabel lblPleaseChooseA = new JLabel("Please choose a parking lot:");
		lblPleaseChooseA.setBounds(21, 23, 179, 20);
		add(lblPleaseChooseA);
	}
}
