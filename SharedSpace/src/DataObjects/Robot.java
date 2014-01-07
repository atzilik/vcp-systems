package DataObjects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


public class Robot implements Serializable{
	private ParkingSpace[][][] parkingSpace;
	private int carNumber;
	private boolean busy;
	private int[] spaceLeft;
	private int floorSize;
	private int rowSize;
	private int depthSize;
	private int freepsace;
	public Robot(ParkingSpace[][][] parkingSpace, int depthSize) {
		this.parkingSpace = parkingSpace;
		busy = false;
		floorSize = ParkingLot.FLOORS_SIZE;
		rowSize = ParkingLot.FLOORS_SIZE;
		this.depthSize = depthSize;
		freepsace = ParkingLot.FLOORS_SIZE * ParkingLot.ROWS_SIZE * depthSize;
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
						freepsace--;
				}
			}
			spaceLeft[i] = count;
		}
	}
	
	
	public void parkCar(int carNumber, Date checkOutdate, Time checkOutTime){
		busy = true;
		ParkingSpace ps = findParkingSpace(carNumber, checkOutdate, checkOutTime);
		ps.setOccupied(true);
		busy = false;
	}
	
	public void unPark(){
		busy = true;
		
		
		
		
		
		busy = false;
	}
	
	public ParkingSpace findParkingSpace(int carNumber, Date checkOutdate, Time checkOutTime){
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
							spaceLeft[floorSize - index]--;
							freepsace--;
							parkingSpace[floorSize - index][i][j].setCarNum(carNumber);
							parkingSpace[floorSize - index][i][j].setCheckOutdate(checkOutdate);
							parkingSpace[floorSize - index][i][j].setCheckOutTime(checkOutTime);
							return parkingSpace[floorSize - index][i][j];
						}
						else if (parkingSpace[floorSize - index][i][j].isOccupied())
						{
							if (DateConvert.afterDate(checkOutdate, parkingSpace[floorSize - index][i][j].getCheckOutdate()))
							{
								//switch cars
								return switchCars(carNumber, checkOutdate, checkOutTime, parkingSpace[floorSize - index][i][j]);
							}
							else if (DateConvert.equalsDate(checkOutdate, parkingSpace[floorSize - index][i][j].getCheckOutdate()))
							{
								if (DateConvert.compareTime(checkOutTime, parkingSpace[floorSize - index][i][j].getCheckOutTime()) == 1)
								{
									//switch cars
									return switchCars(carNumber, checkOutdate, checkOutTime, parkingSpace[floorSize - index][i][j]);
								}
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
	
	public ParkingSpace switchCars(int carNumber, Date checkOutdate, Time checkOutTime, ParkingSpace parkingSpace){
		int carNum = parkingSpace.getCarNum();
		Date date = parkingSpace.getCheckOutdate();
		Time time = parkingSpace.getCheckOutTime();
		parkingSpace.setCarNum(carNumber);
		parkingSpace.setCheckOutdate(checkOutdate);
		parkingSpace.setCheckOutTime(checkOutTime);
		return findParkingSpace(carNum, date, time);
	}
	
	
}
