package server;

import java.io.IOException;
import java.net.UnknownHostException;

import Messages.Message;
import ocsf.server.*;

public class Server extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	public Server(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		
		Message genMsg = (Message) msg;
		Message retMsg = (Message) genMsg.doAction();
		
		try {
			client.sendToClient(retMsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void serverStarted()
	{
		System.out.println
		("Server listening for connections on port " + getPort());
	}
	
	protected void serverStopped()
	{
		System.out.println
		("Server has stopped listening for connections.");
	}
	
	public static void main(String[] args){
		Server serv = new Server(Server.DEFAULT_PORT);
		try {
			serv.listen();			
		} catch (UnknownHostException ex){
			ex.printStackTrace();
		}
		catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
			System.exit(1);
		}
	}

}
