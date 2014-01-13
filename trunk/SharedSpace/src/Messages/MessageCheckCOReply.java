package Messages;

import javax.swing.JOptionPane;

/**
 * 
 * @author Boaz
 *This class is responsible for the check out checking of the customer.
 */

public class MessageCheckCOReply extends Message{
		private int rep;
		
		/**
		 * 
		 * @param rep the status answer of the customer check out
		 * 0 - did check out
		 * 1 - didn't check out
		 */
		
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

