package gui;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * This class let's the ceo choose a parking lot and see it's snapshot report via pdf file
 * @author Alon
 *
 */
public class SnapShotReport extends AbstractGUIComponent {

	/**
	 * Map is used to match parking lot name to its id.  The ceo will know which parkinglot he's dealing with
	 */
	private static Map<String,Integer> parkingLots;
	private static JComboBox comboBoxParkLot;
	File reportFile = new File("C:/VCP/ReportTest.pdf");
	public SnapShotReport(final IGUINavigator navigator, Map<String,Integer> mp) {
		setLayout(null);
		this.parkingLots=mp;
		//load combobox with the parking lots name
		comboBoxParkLot = new JComboBox();
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		comboBoxParkLot.setBounds(99, 51, 123, 20);
		add(comboBoxParkLot);

		JButton btnGenerateSnapshotReport = new JButton("<html>Generate SnapShot Report<br />(PDF)</html>");
		btnGenerateSnapshotReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Document document = new Document(PageSize.A4, 50, 50, 50, 50);
				PdfWriter writer = null;
				try {
				//	File reportFile = new File("C:/VCP/ReportTest.pdf");
					writer = PdfWriter.getInstance(document,new FileOutputStream(reportFile));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				document.open();
				try {
					snapShotGen(document,"SnapShot Report of " + comboBoxParkLot.getSelectedItem() + " - (floors 0-2) \n Floor No 0" ,writer);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Desktop.isDesktopSupported()) {
				    try {
				        Desktop.getDesktop().open(reportFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}

			}
		});
		btnGenerateSnapshotReport.setBounds(52, 154, 224, 76);
		add(btnGenerateSnapshotReport);

		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnGoBack.setBounds(114, 241, 89, 23);
		add(btnGoBack);

		JLabel lblNewLabel = new JLabel("Please choose a parking lot:");
		lblNewLabel.setBounds(79, 26, 188, 14);
		add(lblNewLabel);
	}

	public static void snapShotGen (Document document,String str,PdfWriter writer) throws DocumentException{
		int xAxis=75;
		int yAxis=560;
		Paragraph title1 = new Paragraph(str,FontFactory.getFont(FontFactory.COURIER,16, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setSpacingBefore(50);
		title1.setAlignment(Element.ALIGN_CENTER);
		paragraph1.add(title1);
		document.add(paragraph1);

		PdfContentByte canvas = writer.getDirectContent();
		canvas.saveState();
		canvas.setColorFill(BaseColor.LIGHT_GRAY);
		canvas.rectangle(50,530, 500, 200);
		canvas.fillStroke();
		canvas.restoreState();
		//Paragraph title2 = new Paragraph("Floor No " + 0,FontFactory.getFont(FontFactory.COURIER,16, Font.BOLD, new CMYKColor(0, 255, 255,17)));
		

		for (int i=0;i<=parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].FLOORS_SIZE-1;i++)
		{
			for (int j=0;j<=parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].ROWS_SIZE-1;j++)
			{
				for (int k=0;k<=parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].getDepthSize()-1;k++)
				{
					if (parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].getParkingspace()[i][j][k].isAvailable())
					{
						canvas.setColorFill(BaseColor.WHITE);
					}
					else if (parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].getParkingspace()[i][j][k].isOccupied())
					{
						canvas.setColorFill(BaseColor.RED);
					}
					canvas.rectangle(xAxis,yAxis, 30, 30);
					canvas.fillStroke();
					xAxis=xAxis+60;
				}//finished one row
				xAxis=75; // reset coordinates
				yAxis+=60;
			}
			if (i!=2){
			document.newPage(); //new page
			Paragraph title3 = new Paragraph("Floor No " + (i+1),FontFactory.getFont(FontFactory.COURIER,16, Font.BOLD, new CMYKColor(0, 255, 255,17)));
			Paragraph paragraph3 = new Paragraph();
			paragraph3.setSpacingBefore(20);
			title3.setAlignment(Element.ALIGN_CENTER);
			paragraph3.add(title3);
			document.add(paragraph3);
			canvas = writer.getDirectContent();
			canvas.saveState();
			canvas.setColorFill(BaseColor.LIGHT_GRAY);
			canvas.rectangle(50,550, 500, 200);
			canvas.fillStroke();
			canvas.restoreState();
			yAxis=580;
			}
		}
		document.close();
		}

}
