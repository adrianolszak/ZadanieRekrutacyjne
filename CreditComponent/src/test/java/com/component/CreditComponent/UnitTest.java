package com.component.CreditComponent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UnitTest {
	
	private  CreditManager creditManager;
	
	Operations operation = mock(Operations.class);
	Credit credit = mock(Credit.class);

	private  List<Operations> listOfOperations;
	private  CheckOperations checkOperations;

	@Before
    public void setUp() {
//		credit = new Credit(300000 , 60, 2.0, 2.0, 0, 0, 0);
//		operation = new Operations("Zmiana WIBOR", "15.07.2017", 2);

		listOfOperations = new ArrayList<Operations>();
		listOfOperations.add(operation);
	    checkOperations = new CheckOperations();
	    Mockito.when(credit.getDuration()).thenReturn(60);
	    Mockito.when(credit.getLoanAmount()).thenReturn(300000);
	    Mockito.when(credit.getMargin()).thenReturn(2.0);
//	    Mockito.when(credit.getOverpaidAmount()).thenReturn(0.0);
	    Mockito.when(credit.getOverpaymentAmount()).thenReturn(0.0);
//	    Mockito.when(credit.getRepaidAmount()).thenReturn(0.0);
	    Mockito.when(credit.getwIBOR()).thenReturn(2.0);
	    Mockito.when(operation.getDate()).thenReturn("15.07.2017");
	    Mockito.when(operation.getOperation()).thenReturn("Zmiana WIBOR");
	    Mockito.when(operation.getValue()).thenReturn(2.0);
	    creditManager = new CreditManager(credit, listOfOperations);


    }
	@Test
	public void testCheckOperations() throws ParseException{
		when(operation.getDate()).thenReturn("22.07.2017");
		assertTrue(CheckOperations.daty(operation, "21.07.2017", "22.07.2017"));
		assertTrue(CheckOperations.daty(operation, "21.07.2017", "23.07.2017"));
		assertTrue(CheckOperations.daty(operation, "21.07.2017", "01.08.2017"));

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
		when(credit.getMargin()).thenReturn(0.0);
		creditManager.setWIBOR(20);
		creditManager.setDuration(3);
		assertEquals(1.213, creditManager.calculateCoefficient(),0.01);
		when(credit.getMargin()).thenReturn(20.0);
		creditManager.setWIBOR(0);
		creditManager.setDuration(3);
		assertEquals(1.213, creditManager.calculateCoefficient(),0.01);
		when(credit.getMargin()).thenReturn(5.0);
		creditManager.setWIBOR(5);
		creditManager.setDuration(0);
		assertEquals(1, creditManager.calculateCoefficient(),0.01);
		when(credit.getMargin()).thenReturn(2.0);
		creditManager.setWIBOR(2);
		creditManager.setDuration(2);
		assertEquals(1.0268, creditManager.calculateCoefficient(),0.01);
	}
	
	
	@Test
	public void testcalculateInterest(){
		assertEquals(0.04, creditManager.calculateInterest(),0.01);
		creditManager.setWIBOR(1);
		when(credit.getMargin()).thenReturn(1.0);
		when(credit.getLoanAmount()).thenReturn(3000);
		assertEquals(0.02, creditManager.calculateInterest(),0.01);
		creditManager.setWIBOR(0);
		when(credit.getMargin()).thenReturn(0.0);
		assertEquals(0, creditManager.calculateInterest(),0.01);
		creditManager.setWIBOR(0.22);
		when(credit.getMargin()).thenReturn(1.023);
		when(credit.getLoanAmount()).thenReturn(3000);
		assertEquals(0.01243, creditManager.calculateInterest(),0.01);
	}
	
	@Test
	public void testcalculateInterestPart(){
		assertEquals(4000, creditManager.calculateInterestPart(), 0.01);

	}

	@Test
	public void testcalculateRemainingAmount(){
	assertEquals(300000, creditManager.calculateRemainingAmount(),0.01);
	when(credit.getLoanAmount()).thenReturn(200);
	creditManager.setRepaidAmount(300);
	assertEquals(0, creditManager.calculateRemainingAmount(),0.01);
	}
	
	@Test
	public void testcalculateCapitalPart(){
		assertEquals(7295.41 - 4000, creditManager.calculateCapitalPart(),0.01);
	}
	@Test
	public void testmainCaluclate() throws ParseException{
		assertEquals(137725.02, creditManager.mainCaluclate(),0.01);
		assertEquals(137725.02, creditManager.mainCaluclate(),0.01);

	}	

}
