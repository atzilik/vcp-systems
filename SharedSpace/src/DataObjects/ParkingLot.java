package DataObjects;

import java.io.Serializable;

public class ParkingLot implements Serializable{
	
	private int parkingLotID;
	private String name;
	private ParkingSpace[][][] parkingspace;
	private boolean active;
	private boolean full;
	private ParkingLot alternativePL;
	private int freepsace;
	private	Robot robot;
	final public static int ROWS_SIZE = 3;
	final public static int FLOORS_SIZE = 3;
	public int depthSize;
	
	
	
	public ParkingLot() {
		
	}
	
	public ParkingLot(int parkingLotID, String name, boolean active,
			boolean full, ParkingLot alternativePL, int depthSize) {

		this.parkingLotID = parkingLotID;
		this.name = name;
		this.depthSize = depthSize;
		parkingspace = new ParkingSpace[FLOORS_SIZE][ROWS_SIZE][depthSize];
		this.active = active;
		this.full = full;
		this.alternativePL = alternativePL;
		freepsace = FLOORS_SIZE * ROWS_SIZE * depthSize;
		robot = new Robot();
	}

	public int getParkingLotID() {
		return parkingLotID;
	}

	public void setParkingLotID(int parkingLotID) {
		this.parkingLotID = parkingLotID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParkingSpace[][][] getParkingspace() {
		return parkingspace;
	}

	public void setParkingspace(ParkingSpace[][][] parkingspace) {
		this.parkingspace = parkingspace;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public ParkingLot getAlternativePL() {
		return alternativePL;
	}

	public void setAlternativePL(ParkingLot alternativePL) {
		this.alternativePL = alternativePL;
	}

	public int getFreepsace() {
		return freepsace;
	}

	public void setFreepsace(int freepsace) {
		this.freepsace = freepsace;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public int getDepthSize() {
		return depthSize;
	}

	public void setDepthSize(int depthSize) {
		this.depthSize = depthSize;
	}
	

	
	
}
