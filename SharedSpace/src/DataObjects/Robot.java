package DataObjects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


public class Robot implements Serializable{
	private ParkingSpace[][][] parkingSpace;
	private boolean busy;
	private int[] spaceLeft;
	private int floorSize;
	private int rowSize;
	private int depthSize;
	private int freespace;
	private boolean parkingLotFull;
	public Robot(ParkingSpace[][][] parkingSpace, int depthSize) {
		this.parkingSpace = parkingSpace;
		busy = false;
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
	
	//check reserve and car id
	public void parkCar(int carNumber, Date checkOutdate, Time checkOutTime){
		busy = true;
		ParkingSpace ps = findParkingSpace(carNumber, checkOutdate, checkOutTime);
		spaceLeft[ps.getFloor()]--;
		freespace--;
		System.out.println("Insert");
		System.out.println("freespace = " + freespace);
		System.out.println("spaceLeft[" + ps.getFloor() +"]= " + spaceLeft[ps.getFloor()]);
		if (freespace == 0)
		{
			parkingLotFull = true;
		}
		ps.setOccupied(true);
		busy = false;
	}
	
	public void unPark(int carNumber, int floor, int row, int depth){
		busy = true;
		
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
						System.out.println("Remove");
						System.out.println("freespace = " + freespace);
						System.out.println("spaceLeft[" + i +"]= " + spaceLeft[i]);
						return;
					}
				}
			}
		}

		busy = false;
	}
	
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
						if (parkingSpace[floorSize - index][i][j].isAvailable())
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
								parkingSpace[floorSize - index][i][j].setCarNum(carNumber);
								parkingSpace[floorSize - index][i][j].setCheckOutdate(checkOutDate);
								parkingSpace[floorSize - index][i][j].setCheckOutTime(checkOutTime);
								parkingSpace[floorSize - index][i][j].setReserved(false);
								return parkingSpace[floorSize - index][i][j];
							}
						}
						else if (parkingSpace[floorSize - index][i][j].isOccupied())
						{
							java.util.Date parkedCarCheckOutDate = DateConvert.buildFullDate(parkingSpace[floorSize - index][i][j].getCheckOutdate(), parkingSpace[floorSize - index][i][j].getCheckOutTime());
							if (DateConvert.timeDifference(checkOutDate,parkedCarCheckOutDate) > 0)
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


	public boolean isBusy() {
		return busy;
	}


	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	
	
	public boolean isParkingLotFull() {
		return parkingLotFull;
	}


	public void setParkingLotFull(boolean parkingLotFull) {
		this.parkingLotFull = parkingLotFull;
	}


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
