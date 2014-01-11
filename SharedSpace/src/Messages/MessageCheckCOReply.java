package Messages;

import javax.swing.JOptionPane;
	
public class MessageCheckCOReply extends Message{
		private int rep;
		
	public MessageCheckCOReply(int rep) {
			this.rep = rep;
	}
		
	public int getAns() {
		return rep;
	}

		@Override
		public Message doAction() {
			switch (rep)
			{
				case 0: {JOptionPane.showMessageDialog(null, "hello."); // good
				break;}		
				case 1: {JOptionPane.showMessageDialog(null, "you have to check out first.", "Error", JOptionPane.ERROR_MESSAGE);
				break;}
			}

			return null;
		}

}

