package com.component.CreditComponent;

import java.util.List;

public class CreditManager {
	private Credit credit;
	private List<Operations> operations;
	private double installment;
	private double WIBOR;
	
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
		this.installment = calculateInstallment();
	}
	
	public double calculateInstallment(){
		return credit.getLoanAmount() * calculateCoefficient() * calculateInterest() / 3 / (calculateCoefficient() - 1); 
	}
	
	public double calculateCoefficient(){
		return Math.pow(1 + calculateInterest()/3, credit.getDuration());
	}
	
	public double calculateInterest() {
        return (credit.getMargin() + WIBOR) / 100;
	}
	
	public double calculateInterestPart(){
		return calculateRemainingAmount() * calculateInterest() / 3;
	}
	
	public double calculateRemainingAmount(){
		if(credit.getLoanAmount() - credit.getRepaidAmount() > 0) return credit.getLoanAmount() - credit.getRepaidAmount();
		else return 0;
	}
	
	public void makeOperation(Operations operation){
		if(operation.getOperation().equals("Zmiana WIBOR")) credit.setwIBOR(operation.getValue());
		else credit.setOverpaymentAmount(operation.getValue());
	}
	
	public void checkOperations(String dataFrom, String dataTo){
		if(operations != null){
			for (Operations el : operations) changeValues(el,dataFrom,dataTo);
		}
	}
	
	public void changeValues(Operations operation, String dataFrom, String dataTo){
		if(CheckOperations.daty(operation,dataFrom, dataTo)) makeOperation(operation); 
	}
	
	public double calculateCapitalPart(){
		return installment - calculateInterestPart();
	}
	
	public double mainCaluclate(){
		int day = CheckOperations.getday();
		int month = CheckOperations.getmonth();
		int year = CheckOperations.getyear();
		String old = "" + day + "." + month + "." + year;
		int changeWibor=0;
		int duration = credit.getDuration();
		if(day>8) month += 1;
		day = 10;
		double interestPortion = 0;
		while(true){
			if (credit.getDuration() < 1) break;
			
			month += 1;
			if ((month % 13) == 0){
				month = 1; 
				year = year + 1; 
			}
			checkOperations(old , day + "." +month + "." + year);
			if (changeWibor > 2) {
				this.WIBOR = credit.getwIBOR();
				changeWibor = 0;
			}
			else changeWibor++;
			interestPortion += calculateInterestPart();
			credit.setRepaidAmount(calculateCapitalPart());
			credit.setDuration(credit.getDuration() - 1);
			credit.setOverpaidAmount(credit.getOverpaidAmount() + credit.getOverpaymentAmount());
			while(calculateCapitalPart() <= credit.getOverpaidAmount()){
				credit.setOverpaidAmount(credit.getOverpaidAmount() - calculateCapitalPart());
				credit.setDuration(credit.getDuration() - 1);
				if (credit.getDuration() < 1) break;
			}
			old =  day + "." +month + "." + year;			
		}
		credit.setDuration(duration);
		return interestPortion;	
	}
}