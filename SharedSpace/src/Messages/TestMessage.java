package Messages;

public class TestMessage extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		System.out.println("DSFSDFD");
		return new TestMessage();
	}

}
