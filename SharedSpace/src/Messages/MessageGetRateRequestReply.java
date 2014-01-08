package Messages;

import java.util.ArrayList;

import DataObjects.RateRequest;
import DataObjects.Worker;

public class MessageGetRateRequestReply extends Message  {

	private ArrayList<RateRequest> rateArray = new ArrayList<>();
	private Worker wkr;
	
	public MessageGetRateRequestReply (ArrayList<RateRequest> rateArr) {
		setRateArray(rateArr);
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<RateRequest> getRateArray() {
		return rateArray;
	}
	public void setRateArray(ArrayList<RateRequest> rateArray) {
		this.rateArray = rateArray;
	}
	public Worker getWkr() {
		return wkr;
	}
	public void setWkr(Worker wkr) {
		this.wkr = wkr;
	}

}
