package com.component.CreditComponent;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {
	
	//private static CreditManager mockedCreditManager;
	private static CreditManager creditManager;
	private static Operations operation;
	private static Credit credit;
	private static List<Operations> listOfOperations;
	private static CheckOperations checkOperations;

	@BeforeClass
	public static void setUpClass(){
		//mockedCreditManager = mock(CreditManager.class);
		credit = new Credit(300000 , 60, 2.0, 2.0, 0, 0, 0);
		operation = new Operations("Zmiana WIBOR", "15.07.2017", 2);
		listOfOperations = new ArrayList<Operations>();
		listOfOperations.add(operation);
	    creditManager = new CreditManager(credit, listOfOperations);
	    checkOperations = new CheckOperations();
	    
	    //when(mockedCreditManager.getCredit()).thenReturn(credit);
	    //when(mockedCreditManager.getOperations()).thenReturn(listOfOperations);
	    
	}
	@Before
    public void setUp() {
		credit = new Credit(300000 , 60, 2.0, 2.0, 0, 0, 0);
		operation = new Operations("Zmiana WIBOR", "15.07.2017", 2);
		listOfOperations = new ArrayList<Operations>();
		listOfOperations.add(operation);
	    creditManager = new CreditManager(credit, listOfOperations);
    }
	@Test
	public void testCheckOperations() throws ParseException{
		Operations operation = new Operations("Zmiana WIBOR", "22.07.2017", 2);
		assertTrue(CheckOperations.daty(operation, "21.07.2017", "22.07.2017"));
		assertTrue(CheckOperations.daty(operation, "21.07.2017", "23.07.2017"));
		assertFalse(CheckOperations.daty(operation, "21.07.2017", "20.07.2017"));
	}
	@Test
	public void testcalculateInstallment(){	
		assertEquals(7295.41, creditManager.calculateInstallment(),0.01);
		creditManager.setWIBOR(0);
		assertEquals(6082.918, creditManager.calculateInstallment(),0.01);		
	}
	
	@Test
	public void testcalculateCoefficient(){		
		credit.setMargin(0);
		creditManager.setWIBOR(20);
		credit.setDuration(3);
		assertEquals(1.213, creditManager.calculateCoefficient(),0.01);
		credit.setMargin(20);
		creditManager.setWIBOR(0);
		credit.setDuration(3);
		assertEquals(1.213, creditManager.calculateCoefficient(),0.01);
		credit.setMargin(5);
		creditManager.setWIBOR(5);
		credit.setDuration(0);
		assertEquals(1, creditManager.calculateCoefficient(),0.01);
		credit.setMargin(2);
		creditManager.setWIBOR(2);
		credit.setDuration(2);
		assertEquals(1.0268, creditManager.calculateCoefficient(),0.01);
	}
	
	
	@Test
	public void testcalculateInterest(){
		assertEquals(0.04, creditManager.calculateInterest(),0.01);
		creditManager.setWIBOR(1);
		credit.setMargin(1);
		credit.setLoanAmount(3000);
		assertEquals(0.02, creditManager.calculateInterest(),0.01);
		creditManager.setWIBOR(0);
		credit.setMargin(0);
		assertEquals(0, creditManager.calculateInterest(),0.01);
		creditManager.setWIBOR(0.22);
		credit.setMargin(1.023);
		credit.setLoanAmount(3000);
		assertEquals(0.01243, creditManager.calculateInterest(),0.01);
	}
	
	@Test
	public void testcalculateInterestPart(){
		assertEquals(4000, creditManager.calculateInterestPart(), 0.01);

	}

	@Test
	public void testcalculateRemainingAmount(){
	assertEquals(300000, creditManager.calculateRemainingAmount(),0.01);
	credit.setLoanAmount(200);
	credit.setRepaidAmount(300);
	assertEquals(0, creditManager.calculateRemainingAmount(),0.01);
	}
	
	@Test
	public void testcalculateCapitalPart(){
		assertEquals(7295.41 - 4000, creditManager.calculateCapitalPart(),0.01);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testmainCaluclate() throws ParseException{
		assertEquals(137725.02, creditManager.mainCaluclate(), 0.01);
	}		
	
		

}
