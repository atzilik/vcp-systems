package gui;

import gui.Question.QType;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import logObjects.SingleLineFormatter;
import dataObjects.Answer;

public class AbsForm extends JPanel {


	private static final long serialVersionUID = 2367996910235371070L;
	protected static Logger logger = null;
	protected ArrayList<Question> guiQuestions;
	protected QuestionsPanel qPanel;
	protected ArrayList<Answer> answers;
	
	private INavigator navigator;		
	private AbsForm instance = this;		
	
	public AbsForm(INavigator navigator) {
		
		this.navigator = navigator;
		
		initLogger(Form1.class.getName());
		setLayout(null);		
		
		parseQuestions();						
		
		JButton button = new JButton("\u05E9\u05DE\u05D5\u05E8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					getAnswers();
					JOptionPane.showMessageDialog(instance, "הנתונים נשמרו בהצלחה", "הודעה", JOptionPane.INFORMATION_MESSAGE);
					instance.navigator.toMainMenu();
				} 
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(instance, "שגיאה בשמירת הנתונים:"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();					
				}														
				
			}
		});
		button.setBounds(863, 666, 97, 25);
		add(button);
	}
	
	protected void parseQuestions() {
		
		  
	  String pathToCsv = ".\\formCsv\\"+this.getClass().getSimpleName()+".csv";
      String splitBy = ",";
      String[] splitLine;
      BufferedReader br = null;
      String line = null;
       
      
      try {
    	  	 
			br = new BufferedReader(new FileReader(pathToCsv));
			guiQuestions = new ArrayList<Question>();
			Question q;
			while ((line = br.readLine()) !=null) 
			{				   
				   splitLine = line.split(splitBy);
				   if(splitLine.length<2)
				   {
					   br.close();
					   throw new IllegalArgumentException("Invalid format in line:"+line);
				   }
				   else	
				   {
					   String qText = splitLine[0];
					   int width, height;					  
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
					   					
					   			
					   			q = new Question(qText,QType.SINGLE_SELECTION,rbtnPanel);
					   			guiQuestions.add(q);					   								   			
					   		break;
					   		
					   		case "multi_selection":
					   			CheckBoxPanel chkBoxPanel = new CheckBoxPanel();
					   			for(int i=2; i<splitLine.length;i++)
					   			{
					   				JCheckBox chkBox = new JCheckBox(splitLine[i]);	
					   				chkBoxPanel.addCheckBox(chkBox);
					   				chkBoxPanel.add(chkBox);
					   			}
					   			
					   			q = new Question(qText,QType.MULTI_SELECTION,chkBoxPanel);
					   			guiQuestions.add(q);
					   		break;
					   		
					   		case "birth_date":
					   			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
								JFormattedTextField tf = new JFormattedTextField(df);
								tf.setBorder(BorderFactory.createLineBorder(Color.black));

						        tf.setColumns(5);								        
						        try {
						            MaskFormatter dateMask = new MaskFormatter("##/##/####");
						            dateMask.install(tf);
						        } catch (ParseException ex) {
						            logger.severe("Unable to format date for text field");
						            ex.printStackTrace();
						        }
						        
						        q = new Question(qText,QType.BIRTH_DATE,tf);						
						        guiQuestions.add(q);
						    break;
					   			
					   		case "free_text":
					   			width = Integer.parseInt(splitLine[2]);
					   			height = Integer.parseInt(splitLine[3]);
					   			TextArea textArea1 = new TextArea(width,height);			
					   								   			
					   			if(splitLine.length==5)
					   			{
					   				String qExtraText = splitLine[4];
					   				q = new Question(qText,QType.FREE_TEXT,textArea1,qExtraText);
					   			}					   				
					   			else
					   			{
					   				q = new Question(qText,QType.FREE_TEXT,textArea1);
					   			}
					   			
					   			guiQuestions.add(q);
					   		break;
					   		
					   		case "free_text_range":
					   			String qExtraText = splitLine[2];
					   			width = Integer.parseInt(splitLine[3]);
					   			height = Integer.parseInt(splitLine[4]);
					   			TextArea textArea2 = new TextArea(width,height);
					   			TextArea textArea3 = new TextArea(width, height);
					   								   			
					   			q = new Question(qText, QType.FREE_TEXT_RANGE, textArea2, textArea3, qExtraText);
					   			guiQuestions.add(q);
					   		break;			
					   		
					   		case "free_text_multi":					   
					   			width = Integer.parseInt(splitLine[2]);
					   			height = Integer.parseInt(splitLine[3]);
					   			
					   			MultiFreeTextPanel multiFreeTextPanel = new MultiFreeTextPanel();
					   			for(int i=4; i<splitLine.length;i++)
					   			{
					   				multiFreeTextPanel.addQuestion(splitLine[i], width, height);
					   			}
					   			
					   			q = new Question(qText, QType.FREE_TEXT_MULTI, multiFreeTextPanel);
					   			guiQuestions.add(q);
					   			
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
		catch (IllegalArgumentException e) {
			
			logger.severe("Invalid format line:"+line);
			logger.severe("Terminating program...");
			   
			System.exit(1);
		}
	
	}

	protected void getAnswers() throws Exception {
		
		answers = new ArrayList<Answer>();
		for(Question q: guiQuestions)
		{
			String qText;
			Answer answer;
			switch(q.getqType())
			{
				case FREE_TEXT:
					qText = q.getQtext();
					
					JTextArea textArea = (JTextArea) q.getQcomponents().get(0);
					answer = new Answer(qText, textArea.getText());
					answers.add(answer);					
				break;
				case BIRTH_DATE:
					qText = q.getQtext();
					JFormattedTextField dateField = (JFormattedTextField) q.getQcomponents().get(0);
					answer = new Answer(qText, dateField.getText());
					answers.add(answer);										
				break;
				case MULTI_SELECTION:
					qText = q.getQtext();
					CheckBoxPanel chkBoxPanel = (CheckBoxPanel) q.getQcomponents().get(0);
					ArrayList<String> checkedBoxes = chkBoxPanel.getCheckedBoxes();
					answer = new Answer(qText, checkedBoxes);
					answers.add(answer);					
				break;
				case SINGLE_SELECTION:
					qText = q.getQtext();
					RadioButtonPanel rbtnPanel = (RadioButtonPanel) q.getQcomponents().get(0);
					answer = new Answer(qText, rbtnPanel.getSelected());
					answers.add(answer);					
				break;
				
				case FREE_TEXT_RANGE:
					qText = q.getQtext();
					ArrayList<Component> comps = (ArrayList<Component>) q.getQcomponents();
					TextArea txtArea1 = (TextArea) comps.get(0);
					TextArea txtArea2 = (TextArea) comps.get(1);					
					ArrayList<String> mulAnswers = new ArrayList<String>();
					mulAnswers.add(txtArea1.getText());
					mulAnswers.add(txtArea2.getText());					
					answer = new Answer(qText, mulAnswers);
					answers.add(answer);					
				break;
				
				case FREE_TEXT_MULTI:
					qText = q.getQtext();
					MultiFreeTextPanel mulFreeTxtPanel = (MultiFreeTextPanel) q.getQcomponents().get(0);
					answer = new Answer(qText, mulFreeTxtPanel.getQuestions(), mulFreeTxtPanel.getAnswers());
					answers.add(answer);					
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
			String pattern = className+".log";
			fh = new FileHandler(log_dir+pattern);						    			 			
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
	
