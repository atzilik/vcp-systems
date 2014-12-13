package gui;

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

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import logObjects.SingleLineFormatter;
import dataObjects.AbsQData;
import dataObjects.AbsQData.QType;
import dataObjects.QDataBirthDate;
import dataObjects.QDataFreeText;

public abstract class AbsPanel extends JPanel {

	private static final long serialVersionUID = 2367996910235371070L;
	protected static Logger logger = null;
	protected ArrayList<AbsQData> DataQuestions;	
	protected ArrayList<Question> guiQuestions;
	protected QuestionsPanel qPanel;
	
	
	protected void parseQuestions(String pathToCsv) {
		
	  
      String splitBy = ",";
      String[] splitLine;
      BufferedReader br = null;
      String line = null;
      QType qType = null;
      
      try {
			br = new BufferedReader(new FileReader(pathToCsv));
									
			while ((line = br.readLine()) !=null) 
			{
				   AbsQData q = null;
				   splitLine = line.split(splitBy);
				   if(splitLine.length<2)
				   {
					   br.close();
					   throw new Exception("Invalid format in line:"+line);
				   }
				   else	
				   {
				   	   switch(splitLine[1])
					   {				   
					   		case "single_selection":
					   			qType = QType.SINGLE_SELECTION;
					   		break;
					   		
					   		case "multi_selection":
					   			qType = QType.MULTI_SELECTION;
					   		break;
					   		
					   		case "birth_date":
					   			qType = QType.BIRTH_DATE;
					   			q = new QDataBirthDate(splitLine[0], qType);
					   			
					   		case "free_text":
					   			qType = QType.FREE_TEXT;
					   			q = new QDataFreeText(splitLine[0], qType, Integer.parseInt(splitLine[2]));
					   		break;
					   		
					   		default:
					   			logger.severe(splitLine[1]+" is not a valid question type");
					   			throw new IllegalArgumentException(splitLine[1]+" is not a valid question type");    	   		    	   		    	   
					   }
				   }
				   				   
				   if(DataQuestions == null)
					   DataQuestions = new ArrayList<AbsQData>();
				   DataQuestions.add(q);
			  }
			
			try { 
				br.close();
			} catch (IOException e) {
				
				logger.severe(pathToCsv+" failed to be closed");
			}				
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

	protected void buildQuestions() {
		
		{			
			for(AbsQData qData: DataQuestions) {
				
				String qText = qData.getqText();
				
				switch(qData.getqType())
				{
				
					case BIRTH_DATE:																																				
						
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
				        
				        Question q = new Question(qText, tf);
				        
				        if(guiQuestions==null)
				        	guiQuestions = new ArrayList<Question>();
				        guiQuestions.add(q);			        			        			        																																
					break;
					
					case FREE_TEXT:
						
						QDataFreeText qDataFreeText = (QDataFreeText) qData;
						
						
					break;
					case MULTI_SELECTION:
					break;
					case SINGLE_SELECTION:
					break;
					default:
					break;			
				}			
			}
						
		}
		
		qPanel = new QuestionsPanel(guiQuestions);
		qPanel.setBounds(781, 88, 192, 650);
		add(qPanel);	
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
	
