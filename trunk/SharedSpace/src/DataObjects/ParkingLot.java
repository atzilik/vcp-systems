package DataObjects;

import java.io.Serializable;


/**
 * represents a parking lot
 * @author Gal
 *
 */
public class ParkingLot implements Serializable{
	
	private int parkingLotID;
	private String name;
	/**
	 * used to map the parking lot
	 */
	private ParkingSpace[][][] parkingspace;
	/**
	 * identify if the parking lot is active
	 */
	private boolean active;
	/**
	 * identify id the parking lot is full
	 */
	private boolean full;
	/**
	 * alternative parking lot
	 */
	private int altparkingLotID;
	/**
	 * parking lot's robot
	 */
	private Robot robot;
	final public static int ROWS_SIZE = 3;
	final public static int FLOORS_SIZE = 3;
	public int depthSize;
	
	
	/**
	 * default constructor
	 */
	public ParkingLot() {
		
	}
	/**
	 * 
	 * @param parkingLotID
	 * @param name
	 * @param active
	 * @param full
	 * @param altparkingLotID
	 * @param depthSize
	 */
	public ParkingLot(int parkingLotID, String name, boolean active,
			boolean full, int altparkingLotID, int depthSize) {

		this.parkingLotID = parkingLotID;
		this.name = name;
		this.depthSize = depthSize;
		parkingspace = new ParkingSpace[FLOORS_SIZE][ROWS_SIZE][depthSize];
		this.active = active;
		this.full = full;
		this.altparkingLotID = altparkingLotID;
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

	public int getAlternativePL() {
		return altparkingLotID;
	}

	public void setAltparkingLotID(int altparkingLotID) {
		this.altparkingLotID = altparkingLotID;
	}

	public int getDepthSize() {
		return depthSize;
	}

	public void setDepthSize(int depthSize) {
		this.depthSize = depthSize;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot() {
		this.robot = new Robot(parkingspace, depthSize);
	}

	
	
}
