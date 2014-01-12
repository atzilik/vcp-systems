package server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import DataObjects.DateConvert;
import Messages.Message;
import Messages.MessageUpdateStatistics;
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
		serv.buildDailyStatistics();
	}

	public void buildDailyStatistics(){
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true)
				{
					//build next day date (midnight)
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateConvert.addDays(DateConvert.getCurrentSqlDate(), 1));
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					try {
						//wait until midnight
						wait(DateConvert.timeDifference(new java.util.Date(cal.getTimeInMillis()), DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime())));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//build statistics
					new MessageUpdateStatistics().doAction();
				}
			}
		}).start();
	}
	
	
	//		new Thread(new Runnable() {
	//			
	//			@Override
	//			public void run() {
	//				// TODO Auto-generated method stub
	//				while (true)
	//				{
	//					
	//					
	//					//wait a day
	//					try {
	//						wait(24*60*60*1000);
	//					} catch (InterruptedException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//				}
	//				
	//			}
	//		}).start();
}
