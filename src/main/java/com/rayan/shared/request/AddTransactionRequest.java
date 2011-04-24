package com.rayan.shared.request;

import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AddTransactionRequest implements IsSerializable {
	private String eventPlace;
	private String payer;

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	private Double totalAmount;
	/**
	 *@gwt.typeArgs <java.lang.String,java.lang.Double>
	 */
	Map<String, Double> payeeBreakup;

	public String getEventPlace() {
		return eventPlace;
	}

	@Override
	public String toString() {
		return "AddTransactionRequest [eventPlace=" + eventPlace
				+ ", payeeBreakup=" + payeeBreakup + ", payer=" + payer
				+ ", totalAmount=" + totalAmount + "]";
	}

	public Map<String, Double> getPayeeBreakup() {
		return payeeBreakup;
	}

	public void setPayeeBreakup(Map<String, Double> payeeBreakup) {
		this.payeeBreakup = payeeBreakup;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

}
