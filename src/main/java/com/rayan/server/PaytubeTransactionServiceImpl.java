package com.rayan.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rayan.client.PaytubeTransactionService;
import com.rayan.server.manager.PaytubeTransactionManager;
import com.rayan.shared.request.AddTransactionRequest;

@SuppressWarnings("serial")
public class PaytubeTransactionServiceImpl extends RemoteServiceServlet
		implements PaytubeTransactionService {

	private PaytubeTransactionManager paytubeTransactionManager;

	public String[] getPersons() throws IllegalArgumentException {
		System.out.println("Processing request..");
		return new String[] { "Nishant Rayan", "Maitreyee Korgaonkar",
				"Raghavendran Ram" };
	}

	public void setPaytubeTransactionManager(
			PaytubeTransactionManager paytubeTransactionManager) {
		this.paytubeTransactionManager = paytubeTransactionManager;
	}

	public Boolean addTransaction(AddTransactionRequest addTransactionRequest)
			throws IllegalArgumentException {
		System.out.println(addTransactionRequest.toString());
		return true;
	}

}
