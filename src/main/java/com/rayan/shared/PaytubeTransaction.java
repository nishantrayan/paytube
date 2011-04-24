package com.rayan.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PaytubeTransaction implements IsSerializable {
	private PaytubeUser payer;
	private PaytubeUser payee;
	private double amount;
	private PaytubePaymentEvent paymentEvent;
	private Long transactionId;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public PaytubeUser getPayer() {
		return payer;
	}

	public void setPayer(PaytubeUser payer) {
		this.payer = payer;
	}

	public PaytubeUser getPayee() {
		return payee;
	}

	public void setPayee(PaytubeUser payee) {
		this.payee = payee;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaytubePaymentEvent getPaymentEvent() {
		return paymentEvent;
	}

	public void setPaymentEvent(PaytubePaymentEvent paymentEvent) {
		this.paymentEvent = paymentEvent;
	}

}
