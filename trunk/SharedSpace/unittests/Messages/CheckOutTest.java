package Messages;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DataObjects.DateConvert;
import DataObjects.FullMember;
import DataObjects.STDCustomer;
import DataObjects.STDMember;

public class CheckOutTest extends TestCase{
	private STDCustomer stdCustomer;
	private FullMember fullMember;
	private MessageCheckout mcoStandard;
	private MessageCheckout mcoFull;

	@Before
	public void setUp() throws Exception {
		stdCustomer = new STDCustomer("55", "55", "ron", "ron", "ron@gmail.com");
		fullMember	= new FullMember("888", "555", "avi", "nimni", "nimni@gmail.com", "663", DateConvert.stringToSQLDate("2014-02-01"));
		mcoStandard = new MessageCheckout(stdCustomer, 5);
		mcoFull = new MessageCheckout(fullMember, 5);
	}


	public void testValidCustomer(){

		//check that a valid standard customer is accepted and made a check in before checking out
		try {
			Assert.assertTrue(mcoStandard.checkPreviousCheckIn());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//check that system noticed when an instance of not a existing standard customer is create
		//in the database there isn't a standard customer with id = 4
		MessageCheckout mc = new MessageCheckout(new STDCustomer("4", "4", "cst", "cst", "cst@gmail.com"), 0);
		try {
			mc.checkPreviousCheckIn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(true);
		}

		//check if a valid standard customer did check in before trying to check out
		//in the database there is standard customer called "test test" but he didn't do check in yet
		mc = new MessageCheckout(new STDCustomer("77", "77", "test", "test", "test@gmail.com"), 0);
		try {
			Assert.assertFalse(mc.checkPreviousCheckIn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//check a valid standard customer with non existing parking lot id
		mc = new MessageCheckout(new STDCustomer("77", "77", "test", "test", "test@gmail.com"), 33);
		try {
			Assert.assertFalse(mc.checkPreviousCheckIn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//check that a valid full member is accepted and made a check in before checking out
		try {
			Assert.assertTrue(mcoFull.checkPreviousCheckIn());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//check that system noticed when an instance of not a existing full member is create
		//in the database there isn't a full member with member id = 4
		mc = new MessageCheckout(new FullMember("4", "4", "fName", "lName", "email", "4", DateConvert.stringToSQLDate("2014-02-01")), 5);
		try {
			Assert.assertFalse(mc.checkPreviousCheckIn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//check if a valid full member did check in before trying to check out
		//in the database there is full member with member id = 848741 but he didn't do check in yet
		mc = new MessageCheckout(new FullMember("123", "66566", "shultz", "butz", "shultz@gmail.com", "848741", DateConvert.stringToSQLDate("2014-02-01")), 0);
		try {
			Assert.assertFalse(mc.checkPreviousCheckIn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testBill(){

		//check standard customer bill for 3 hours park who didn't reserve in advance and without any delay is correct
		//the rate per hour for customer who didn't reserve in advance in parking lot 5 is 5.
		double expected = 3 * 5;
		double result = 0;

		mcoStandard.setCinDate(DateConvert.getCurrentSqlDate());
		mcoStandard.setCinTime(DateConvert.getCurrentSqlTime());
		mcoStandard.setEstCheckOutDate(DateConvert.addMinutes(DateConvert.getCurrentSqlDate(),180));
		mcoStandard.setEstCheckOutTime(DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), 180));
		mcoStandard.setRealCotDate(mcoStandard.getEstCheckOutDate());
		mcoStandard.setRealCotTime(mcoStandard.getEstCheckOutTime());
		try {
			result = mcoStandard.calculateBill(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(expected, result);

		//check the same as above but for 1.5 hour parking and for a standard customer who reserved in advance.
		//the rate per hour for customer who reserve in advance in parking lot 5 is 4.
		expected = 1.5 * 4;

		mcoStandard.setCinDate(DateConvert.getCurrentSqlDate());
		mcoStandard.setCinTime(DateConvert.getCurrentSqlTime());
		mcoStandard.setEstCheckOutDate(DateConvert.addMinutes(DateConvert.getCurrentSqlDate(),90));
		mcoStandard.setEstCheckOutTime(DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), 90));
		mcoStandard.setRealCotDate(mcoStandard.getEstCheckOutDate());
		mcoStandard.setRealCotTime(mcoStandard.getEstCheckOutTime());
		try {
			result = mcoStandard.calculateBill(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(expected, result);
	
		//check standard customer bill for 3.5 hours park who reserve in advance and was delay to check out by 1.5 hours
		//the rate per hour for customer who reserve in advance in parking lot 5 is 4.
		//the fine is calculated by 120% of the rate per hour * minutes late of the checkout
		expected = (2 * 4) + (1.2 * 4 * 1.5);

		mcoStandard.setCinDate(DateConvert.getCurrentSqlDate());
		mcoStandard.setCinTime(DateConvert.getCurrentSqlTime());
		mcoStandard.setEstCheckOutDate(DateConvert.addMinutes(DateConvert.getCurrentSqlDate(),120));
		mcoStandard.setEstCheckOutTime(DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), 120));
		mcoStandard.setRealCotDate(DateConvert.addMinutes(DateConvert.getCurrentSqlDate(),210));
		mcoStandard.setRealCotTime(DateConvert.addMinutes(DateConvert.getCurrentSqlTime(),210));
		try {
			result = mcoStandard.calculateBill(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(expected, result);
	}

}
