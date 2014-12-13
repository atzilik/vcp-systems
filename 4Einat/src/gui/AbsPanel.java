package gui;

import gui.Question.QType;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import logObjects.SingleLineFormatter;

public abstract class AbsPanel extends JPanel {
	public AbsPanel() {
	}

	private static final long serialVersionUID = 2367996910235371070L;
	protected static Logger logger = null;
	protected ArrayList<Question> guiQuestions;
	protected QuestionsPanel qPanel;
	
	
	protected void parseQuestions(String pathToCsv) {
		
	  
      String splitBy = ",";
      String[] splitLine;
      BufferedReader br = null;
      String line = null;      
      
      try {
			br = new BufferedReader(new FileReader(pathToCsv));
			guiQuestions = new ArrayList<Question>();
			 
			while ((line = br.readLine()) !=null) 
			{				   
				   splitLine = line.split(splitBy);
				   if(splitLine.length<2)
				   {
					   br.close();
					   throw new Exception("Invalid format in line:"+line);
				   }
				   else	
				   {
					   String qText = splitLine[0];
				   	   switch(splitLine[1])
					   {				   
					   		case "single_selection":
					   			
					   			ButtonGroup bg1 = new ButtonGroup();
					   			JPanel tmpPanel1 = new JPanel();
					   			for(int i=2; i<splitLine.length;i++)
					   			{
					   				JRadioButton rbtn = new JRadioButton(splitLine[i]);
					   				bg1.add(rbtn);
					   				tmpPanel1.add(rbtn);
					   			}
					   					
					   			
					   			Question q1 = new Question(qText,QType.SINGLE_SELECTION,tmpPanel1);
					   			guiQuestions.add(q1);					   								   			
					   		break;
					   		
					   		case "multi_selection":
					   			JPanel tmpPanel2 = new JPanel();
					   			for(int i=2; i<splitLine.length;i++)
					   			{
					   				JCheckBox chkbox = new JCheckBox(splitLine[i]);					   				
					   				tmpPanel2.add(chkbox);
					   			}
					   			
					   			Question q2 = new Question(qText,QType.MULTI_SELECTION,tmpPanel2);
					   			guiQuestions.add(q2);
					   		break;
					   		
					   		case "birth_date":
					   			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
								JFormattedTextField tf = new JFormattedTextField(df);

						        tf.setColumns(5);								        
						        try {
						            MaskFormatter dateMask = new MaskFormatter("##/##/####");
						            dateMask.install(tf);
						        } catch (ParseException ex) {
						            logger.severe("Unable to format date for text field");
						            ex.printStackTrace();
						        }
						        
						        Question q3 = new Question(qText,QType.BIRTH_DATE,tf);						
						        guiQuestions.add(q3);
						    break;
					   			
					   		case "free_text":
					   			int width = Integer.parseInt(splitLine[2]);
					   			int height = Integer.parseInt(splitLine[3]);
					   			JTextArea textArea = new JTextArea();
					   			textArea.setLineWrap(true);
					   			textArea.setWrapStyleWord(true);
					   			textArea.setPreferredSize(new Dimension(width,height));
					   			
					   			Question q4;
					   			if(splitLine.length==5)
					   			{
					   				String qExtraText = splitLine[4];
					   				q4 = new Question(qText,QType.FREE_TEXT,textArea,qExtraText);
					   			}					   				
					   			else
					   			{
					   				q4 = new Question(qText,QType.FREE_TEXT,textArea);
					   			}
					   			
					   			guiQuestions.add(q4);
					   		break;
					   		
					   		default:
					   			logger.severe(splitLine[1]+" is not a valid question type");
					   			throw new IllegalArgumentException(splitLine[1]+" is not a valid question type");    	   		    	   		    	   
					   }
				   }
				   				   				   					  				  
			  }
			
			try { 
				br.close();
			} catch (IOException e) {
				
				logger.severe(pathToCsv+" failed to be closed");
			}
			
			qPanel = new QuestionsPanel(guiQuestions);
			qPanel.setBounds(0, 88, 1000, 500);
			add(qPanel);	
      	}	
		catch (IOException e) {
			logger.severe(pathToCsv+" failed to be opened");
			e.printStackTrace();
		} 
		catch (Exception e) {
			
			logger.severe("Invalid format line:"+line);
			logger.severe("Terminating program...");
			   
			System.exit(1);
		}
	
	}

	
	protected void initLogger(String className) {

		
		try 
		{
			logger = Logger.getLogger(className);
			Path currentRelativePath = Paths.get("");
			String log_dir = currentRelativePath.toAbsolutePath().toString() + "\\logs\\";
			FileHandler fh = null;
			int limit = 1000000; // 1 Mb
			int numLogFiles = 3;
			String pattern = className+"%g.log";
			fh = new FileHandler(log_dir+pattern, limit ,numLogFiles);						    			 			
			fh.setFormatter(new SingleLineFormatter());
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);																				
		} 
		catch (SecurityException | IOException e1) 
		{				
			e1.printStackTrace();
		}
	}
		
}
	
