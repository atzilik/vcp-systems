package client;

import java.io.IOException;

import Messages.Message;
import ocsf.client.*;


public class Client extends AbstractClient {

	private Message msgReply;
	
	final public static int DEFAULT_PORT = 5555;
	public Client(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
		try {
			openConnection();
		} catch(IOException exception) 
		{
			System.out.println("Error: Can't setup connection!"
					+ " Terminating client.");
			System.exit(1);
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		msgReply = (Message) msg;			
	}
	
	public synchronized Message getMessage() {
		
		while(msgReply==null) {			
			try {
				wait(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		Message temp = msgReply;
		msgReply = null;
		
		return temp;
	}
	
	public void send(Object message){
		try
		{
			sendToServer(message);
		}
		catch(IOException e)
		{
			System.out.println("Could not send message to server.  Terminating client.");
			quit();
		}
	}
	
	public void quit()
	{
		try
		{
			closeConnection();
		}
		catch(IOException e) {}
		System.exit(0);
	}

}
