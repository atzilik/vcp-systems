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
import javax.swing.text.MaskFormatter;

import logObjects.SingleLineFormatter;
import dataObjects.Answer;

public abstract class AbsForm extends JPanel {


	private static final long serialVersionUID = 2367996910235371070L;
	protected static Logger logger = null;
	protected ArrayList<Question> guiQuestions;
	protected QuestionsPanel qPanel;
	protected ArrayList<Answer> answers;
	
	
	protected void parseQuestions() {
		
		  
	  String pathToCsv = ".\\formCsv\\"+this.getClass().getSimpleName()+".csv";
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
					   			RadioButtonPanel rbtnPanel = new RadioButtonPanel();
					   			for(int i=2; i<splitLine.length;i++)
					   			{
					   				JRadioButton rbtn = new JRadioButton(splitLine[i]);
					   				bg1.add(rbtn);
					   				rbtnPanel.add(rbtn);
					   				rbtnPanel.addRbtnToList(rbtn);
					   			}
					   					
					   			
					   			Question q1 = new Question(qText,QType.SINGLE_SELECTION,rbtnPanel);
					   			guiQuestions.add(q1);					   								   			
					   		break;
					   		
					   		case "multi_selection":
					   			CheckBoxPanel chkBoxPanel = new CheckBoxPanel();
					   			for(int i=2; i<splitLine.length;i++)
					   			{
					   				JCheckBox chkBox = new JCheckBox(splitLine[i]);	
					   				chkBoxPanel.addCheckBox(chkBox);
					   				chkBoxPanel.add(chkBox);
					   			}
					   			
					   			Question q2 = new Question(qText,QType.MULTI_SELECTION,chkBoxPanel);
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
					   			TextArea textArea = new TextArea(width,height);			
					   			
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

	protected void getAnswers() throws Exception {
		
		answers = new ArrayList<Answer>();
		for(Question q: guiQuestions)
		{
			switch(q.getqType())
			{
				case FREE_TEXT:
					String qText1 = q.getQtext();
					JTextArea textArea = (JTextArea) q.getQcomponent();
					Answer answer1 = new Answer(qText1, textArea.getText());
					answers.add(answer1);					
				break;
				case BIRTH_DATE:
					String qText2 = q.getQtext();
					JFormattedTextField dateField = (JFormattedTextField) q.getQcomponent();
					Answer Answer2 = new Answer(qText2, dateField.getText());
					answers.add(Answer2);										
				break;
				case MULTI_SELECTION:
					String qText3 = q.getQtext();
					CheckBoxPanel chkBoxPanel = (CheckBoxPanel) q.getQcomponent();
					ArrayList<String> checkedBoxes = chkBoxPanel.getCheckedBoxes();
					Answer answer3 = new Answer(qText3, checkedBoxes);
					answers.add(answer3);					
				break;
				case SINGLE_SELECTION:
					String qText4 = q.getQtext();
					RadioButtonPanel rbtnPanel = (RadioButtonPanel) q.getQcomponent();
					Answer answer4 = new Answer(qText4, rbtnPanel.getSelected());
					answers.add(answer4);					
				break;
				default:
				break;
			
			}
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
	
