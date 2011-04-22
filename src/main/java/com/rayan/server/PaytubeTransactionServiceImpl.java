package com.rayan.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rayan.client.PaytubeTransactionService;
import com.rayan.shared.PaytubeTransaction;

@SuppressWarnings("serial")
public class PaytubeTransactionServiceImpl extends RemoteServiceServlet
		implements PaytubeTransactionService {

	public String[] getPersons() throws IllegalArgumentException {
		return new String[]{"Nishant Rayan","Maitreyee Korgaonkar", "Raghavendran Ram"};
	}

	public boolean submitTransaction(PaytubeTransaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

}
