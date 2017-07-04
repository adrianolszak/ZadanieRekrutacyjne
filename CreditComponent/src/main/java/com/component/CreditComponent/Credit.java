package com.component.CreditComponent;

public class Credit {
	private int loanAmount;
	private int duration;
	private double margin;
	private double wIBOR;
	private double overpaymentAmount;
	
	public Credit(int loanAmount, int duration, double margin, double wIBOR, double overpaymentAmount) {
		this.loanAmount = loanAmount;
		this.duration = duration;
		this.margin = margin;
		this.wIBOR = wIBOR;
		this.overpaymentAmount = overpaymentAmount;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getMargin() {
		return margin;
	}
	public void setMargin(double margin) {
		this.margin = margin;
	}
	public double getwIBOR() {
		return wIBOR;
	}
	public void setwIBOR(double wIBOR) {
		this.wIBOR = wIBOR;
	}
	public double getOverpaymentAmount() {
		return overpaymentAmount;
	}
	public void setOverpaymentAmount(double overpaymentAmount) {
		this.overpaymentAmount = overpaymentAmount;
	}
	
}

