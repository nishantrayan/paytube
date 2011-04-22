package com.rayan.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


public class PaytubeTransaction implements IsSerializable {
	private String payer;
	private String payee;
	private double amount;
	private PaytubePaymentEvent paymentEvent;

	public PaytubePaymentEvent getPaymentEvent() {
		return paymentEvent;
	}

	public void setPaymentEvent(PaytubePaymentEvent paymentEvent) {
		this.paymentEvent = paymentEvent;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
