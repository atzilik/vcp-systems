package DataObjects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * represents a parking lot robot
 * @author Gal
 *
 */
public class Robot implements Serializable{
	/**
	 * parking lot map
	 */
	private ParkingSpace[][][] parkingSpace;
	/**
	 * spaceleft on each floor of the parking 
	 */
	private int[] spaceLeft;
	private int floorSize;
	private int rowSize;
	private int depthSize;
	/**
	 * free space in the entire parking lot
	 */
	private int freespace;
	private boolean parkingLotFull;
	/**
	 * constructor which calculate free space
	 * @param parkingSpace
	 * @param depthSize
	 */
	public Robot(ParkingSpace[][][] parkingSpace, int depthSize) {
		this.parkingSpace = parkingSpace;
		floorSize = ParkingLot.FLOORS_SIZE;
		rowSize = ParkingLot.FLOORS_SIZE;
		this.depthSize = depthSize;
		freespace = ParkingLot.FLOORS_SIZE * ParkingLot.ROWS_SIZE * depthSize;
		spaceLeft = new int[floorSize];
		for (int i = 0; i < floorSize; i++)
		{
			int count = 0;
			for (int j = 0; j < rowSize; j++)
			{
				for (int k = 0; k < this.depthSize; k++)
				{
					if (parkingSpace[i][j][k].isAvailable())
						count++;
					else
						freespace--;
				}
			}
			spaceLeft[i] = count;
		}
		if (freespace == 0)
			parkingLotFull = true;
		else
			parkingLotFull = false;
	}

	/**
	 * park a car
	 * @param carNumber
	 * @param checkOutdate
	 * @param checkOutTime
	 */
	public synchronized void parkCar(int carNumber, Date checkOutdate, Time checkOutTime){
		ParkingSpace ps = findParkingSpace(carNumber, checkOutdate, checkOutTime);
		spaceLeft[ps.getFloor()]--;
		freespace--;
		if (freespace == 0)
		{
			parkingLotFull = true;
		}
		ps.setOccupied(true);
	}

	/**
	 * unpark a car
	 * @param carNumber
	 * @param floor
	 * @param row
	 * @param depth
	 */
	public synchronized void unPark(int carNumber, int floor, int row, int depth){

		parkingSpace[floor][row][depth].setOccupied(false);
		parkingSpace[floor][row][depth].setCarNum(0);
		parkingSpace[floor][row][depth].setCheckOutdate(null);
		parkingSpace[floor][row][depth].setCheckOutTime(null);
		for (int i = floor; i >= 0; i--)
		{
			for (int j = row; j >= 0; j--)
			{
				for (int k = depth - 1; k >= 0; k--)
				{
					if (parkingSpace[i][j][k].isOccupied())
					{
						int carNum = parkingSpace[i][j][k].getCarNum();
						Date cotDate = parkingSpace[i][j][k].getCheckOutdate();
						Time cotTime = parkingSpace[i][j][k].getCheckOutTime();
						parkingSpace[i][j][k].setOccupied(false);
						parkingSpace[i][j][k].setReserved(false);
						parkingSpace[i][j][k].setCarNum(0);
						parkingSpace[i][j][k].setCheckOutdate(null);
						parkingSpace[i][j][k].setCheckOutTime(null);
						ParkingSpace ps = findParkingSpace(carNum, cotDate, cotTime);
						ps.setOccupied(true);
					}
					else
					{
						freespace++;
						spaceLeft[i]++;
						if (parkingLotFull)
							parkingLotFull = false;

						return;
					}
				}
			}
		}
	}

	/**
	 * find a proper parking space
	 * @param carNumber
	 * @param checkOutDate
	 * @param checkOutTime
	 * @return the chosen parking space
	 */
	public ParkingSpace findParkingSpace(int carNumber, Date checkOutDate, Time checkOutTime){
		int index = 1;
		while (floorSize - index >= 0)
		{
			if (spaceLeft[floorSize - index] > 0)
			{
				for (int i = rowSize - 1; i >= 0; i--)
				{
					for (int j = depthSize - 1; j >= 0; j--)
					{
						if (parkingSpace[floorSize - index][i][j].isParkable())
						{
							//insert to a new parking space
							parkingSpace[floorSize - index][i][j].setCarNum(carNumber);
							parkingSpace[floorSize - index][i][j].setCheckOutdate(checkOutDate);
							parkingSpace[floorSize - index][i][j].setCheckOutTime(checkOutTime);
							return parkingSpace[floorSize - index][i][j];
						}
						else if (parkingSpace[floorSize - index][i][j].isReserved())
						{
							//check if this space reserved for the current car
							if (carNumber == parkingSpace[floorSize - index][i][j].getCarNum())
							{
								//insert to a new parking space
								parkingSpace[floorSize - index][i][j].setReserved(false);
								parkingSpace[floorSize - index][i][j].setCarNum(0);
								parkingSpace[floorSize - index][i][j].setCheckOutdate(null);
								parkingSpace[floorSize - index][i][j].setCheckOutTime(null);
								return findParkingSpace(carNumber, checkOutDate, checkOutTime);

								//								parkingSpace[floorSize - index][i][j].setCarNum(carNumber);
								//								parkingSpace[floorSize - index][i][j].setCheckOutdate(checkOutDate);
								//								parkingSpace[floorSize - index][i][j].setCheckOutTime(checkOutTime);
								//								parkingSpace[floorSize - index][i][j].setReserved(false);
							}
						}
						else if (parkingSpace[floorSize - index][i][j].isOccupied())
						{
							java.util.Date parkedCarCheckOutDate = DateConvert.buildFullDate(parkingSpace[floorSize - index][i][j].getCheckOutdate(), parkingSpace[floorSize - index][i][j].getCheckOutTime());
							if (DateConvert.timeDifference(DateConvert.buildFullDate(checkOutDate, checkOutTime),parkedCarCheckOutDate) > 0)
							{
								//switch cars
								return switchCars(carNumber, checkOutDate, checkOutTime, parkingSpace[floorSize - index][i][j]);
							}
						}
					}
				}
			}
			else
			{
				index++;
			}
		}
		return null;

	}


	public boolean isParkingLotFull() {
		return parkingLotFull;
	}


	public void setParkingLotFull(boolean parkingLotFull) {
		this.parkingLotFull = parkingLotFull;
	}

	/**
	 * switch cars between 2 parking spaces
	 * @param carNumber
	 * @param checkOutdate
	 * @param checkOutTime
	 * @param parkingSpace
	 * @return parking space
	 */
	public ParkingSpace switchCars(int carNumber, Date checkOutdate, Time checkOutTime, ParkingSpace parkingSpace){
		int carNum = parkingSpace.getCarNum();
		Date date = parkingSpace.getCheckOutdate();
		Time time = parkingSpace.getCheckOutTime();
		parkingSpace.setCarNum(carNumber);
		parkingSpace.setCheckOutdate(checkOutdate);
		parkingSpace.setCheckOutTime(checkOutTime);
		return findParkingSpace(carNum, date, time);
	}

	public int getFreespace() {
		return freespace;
	}

	public void setFreespace(int freespace) {
		this.freespace = freespace;
	}



}
