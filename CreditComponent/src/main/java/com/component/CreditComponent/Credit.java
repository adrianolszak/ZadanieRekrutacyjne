package com.component.CreditComponent;

public class Credit {
	private int loanAmount;
	private int duration;
	private double margin;
	private double wIBOR;
	private double overpaymentAmount;
	private double overpaidAmount;
	private double repaidAmount;
	
	public Credit(int loanAmount, int duration, double margin, double wIBOR, double overpaymentAmount) {
		this.loanAmount = loanAmount;
		this.duration = duration;
		this.margin = margin;
		this.wIBOR = wIBOR;
		this.overpaymentAmount = overpaymentAmount;
		this.overpaidAmount = 0;
		this.repaidAmount = 0;
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
	
}

