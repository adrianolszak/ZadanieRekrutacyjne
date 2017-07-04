package com.component.CreditComponent;

import java.text.ParseException;
import java.util.List;

public class CreditManager {
	private Credit credit;
	private List<Operations> operations;
	private double installment;
	private double WIBOR;
	private int duration;
	private double overpaidAmount;
	private double repaidAmount;
	public double getOverpaidAmount() {
	return overpaidAmount;
}
public void setOverpaidAmount(double overpaidAmount) {
	this.overpaidAmount = overpaidAmount;
}
public double getRepaidAmount() {
	return repaidAmount;
}
public void setRepaidAmount(double repaidAmount) {
	this.repaidAmount += repaidAmount;
}
	public double getWIBOR() {
		return WIBOR;
	}

	public void setWIBOR(double wIBOR) {
		WIBOR = wIBOR;
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public List<Operations> getOperations() {
		return operations;
	}

	public void setOperations(List<Operations> operations) {
		this.operations = operations;
	}

	public CreditManager(Credit credit, List<Operations> operations) {
		this.credit = credit;
		this.operations = operations;
		this.WIBOR = credit.getwIBOR();
		duration = credit.getDuration();
		this.overpaidAmount = 0;
		this.repaidAmount = 0;
		this.installment = calculateInstallment();
	}
	
	public double calculateInstallment(){
		return (credit.getLoanAmount() - getRepaidAmount()) * calculateCoefficient() * calculateInterest() / 3 / (calculateCoefficient() - 1); 
	}
	
	public double calculateCoefficient(){
		return Math.pow(1 + calculateInterest()/3, duration);
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double calculateInterest() {
        return (credit.getMargin() + WIBOR) / 100;
	}
	
	public double calculateInterestPart(){
		return calculateRemainingAmount() * calculateInterest() / 3;
	}
	
	public double calculateRemainingAmount(){
		if(credit.getLoanAmount() - getRepaidAmount() > 0) return credit.getLoanAmount() - getRepaidAmount();
		else return 0;
	}
	
	public void makeOperation(Operations operation){
		if(operation.getOperation().equals("Zmiana WIBOR")) credit.setwIBOR(operation.getValue());
		else credit.setOverpaymentAmount(operation.getValue());
	}
	
	public void checkOperations(String dataFrom, String dataTo) throws ParseException{
		if(operations != null){
			for (Operations el : operations) changeValues(el,dataFrom,dataTo);
		}
	}
	
	public void changeValues(Operations operation, String dataFrom, String dataTo) throws ParseException{
		if(CheckOperations.daty(operation,dataFrom, dataTo)) makeOperation(operation); 
	}
	
	public double calculateCapitalPart(){
		return installment - calculateInterestPart();
	}
	
	public double mainCaluclate() throws ParseException{
		int day = CheckOperations.getday();
		int month = CheckOperations.getmonth();
		int year = CheckOperations.getyear();
		String old = "" + day + "." + month + "." + year;
		int changeWibor=0;
		if(day>8) month += 1;
		day = 10;
		double interestPortion = 0;
		while(true){
			if (duration < 1) break;
			
			month += 1;
			if ((month % 13) == 0){
				month = 1; 
				year = year + 1; 
			}
			checkOperations(old , day + "." +month + "." + year);
			if (changeWibor > 2) {
				this.WIBOR = credit.getwIBOR();
				changeWibor = 0;
				this.installment = calculateInstallment();
			}
			else changeWibor++;
			interestPortion += calculateInterestPart();
			setRepaidAmount(calculateCapitalPart());
			duration = duration - 1;
			setOverpaidAmount(getOverpaidAmount() + credit.getOverpaymentAmount());
			while(calculateCapitalPart() <= getOverpaidAmount()){
				setOverpaidAmount(getOverpaidAmount() - calculateCapitalPart());
				duration = duration - 1;
				if (duration < 1) break;
			}
			old =  day + "." +month + "." + year;			
		}
	
		setOverpaidAmount(0);
		setRepaidAmount((-1) * getRepaidAmount());
		this.duration = credit.getDuration();
		this.installment = calculateInstallment();
		return interestPortion;	
	}
}
