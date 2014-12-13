package gui;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class QuestionsPanel extends JPanel {

	private static final long serialVersionUID = -6141022189600476927L;

	public QuestionsPanel(ArrayList<Question> guiQuestions) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
		for(Question q: guiQuestions) 
			add(q); 
		for(Question q: guiQuestions)
			q.setMaximumSize(q.getPreferredSize());

	}
}