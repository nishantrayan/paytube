package com.rayan.server.manager.impl;

import com.rayan.server.dao.GenericDAO;
import com.rayan.server.dao.PaytubeTransactionDAO;
import com.rayan.server.manager.PaytubeTransactionManager;
import com.rayan.shared.PaytubePaymentEvent;
import com.rayan.shared.PaytubeTransaction;

public class PaytubeTransactionManagerImpl implements PaytubeTransactionManager {
	private PaytubeTransactionDAO paytubeTransactionDAO;

	public void setPaytubeTransactionDAO(PaytubeTransactionDAO paytubeTransactionDAO) {
		
		this.paytubeTransactionDAO = paytubeTransactionDAO;
	}

	public void saveTransaction(PaytubeTransaction transaction) {
		paytubeTransactionDAO.saveTransaction(transaction);
	}

	

}
