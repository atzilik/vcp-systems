package Messages;
/**
 * reply message with the detials for activity report
 * @author Omri
 *
 */
public class MessageGetDetailsForActivityReportReply extends Message {
/**
 * frequency of details
 */
		private int freq[];
		private int avg;
		private int med;
		private int total;
		private boolean isOk;
		
		public MessageGetDetailsForActivityReportReply(int total, int fr[],int avg, int med, boolean isOk) {
			this.freq = fr;
			this.avg = avg;
			this.med = med;
			this.total = total;
			this.setOk(isOk);
		}
		
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getFreq() {
		return freq;
	}

	public void setFreq(int freq[]) {
		this.freq = freq;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public int getMed() {
		return med;
	}

	public void setMed(int med) {
		this.med = med;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

}
