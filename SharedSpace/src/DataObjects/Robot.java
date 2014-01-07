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
	
	public Robot(ParkingSpace[][][] parkingSpace, int depthSize) {
		this.parkingSpace = parkingSpace;
		busy = false;
		floorSize = ParkingLot.FLOORS_SIZE;
		rowSize = ParkingLot.FLOORS_SIZE;
		this.depthSize = depthSize;
		
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
				}
			}
			spaceLeft[i] = count;
		}
	}
	
	
	public void parkCar(int carNumber, Date checkOutdate, Time checkOutTime){
		busy = true;
		if (spaceLeft[floorSize - 1] > 0)
		{
			for (int i = rowSize - 1; i >= 0; i--)
			{
				for (int j = depthSize - 1; j >= 0; j++)
				{
					if (checkOutdate.after(parkingSpace[floorSize - 1][i][j].getCheckOutdate()))
					{
						
					}
				}
			}
		}
		
		
		
		
		busy = false;
	}
	
	public void unPark(){
		busy = true;
		
		
		
		
		
		busy = false;
	}


	public boolean isBusy() {
		return busy;
	}


	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	
	
	
}
