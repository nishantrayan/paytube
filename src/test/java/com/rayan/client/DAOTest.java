package com.rayan.client;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.rayan.server.dao.PaytubeTransactionDAO;
import com.rayan.shared.PaytubePaymentEvent;
import com.rayan.shared.PaytubeTransaction;
import com.rayan.shared.PaytubeUser;

public class DAOTest extends AbstractTransactionalDataSourceSpringContextTests {

	private PaytubeTransactionDAO paytubeTransactionDAO;

	public void setPaytubeTransactionDAO(
			PaytubeTransactionDAO paytubeTransactionDAO) {
		this.paytubeTransactionDAO = paytubeTransactionDAO;
	}

	public String[] getConfigLocations() {
		setDependencyCheck(false);
		return new String[] { "applicationContext.xml" };
	}

	public void testSomething() throws Exception {
		assertNotNull(paytubeTransactionDAO);
		PaytubePaymentEvent event = new PaytubePaymentEvent();
		event.setPlace("Sathyam");
		PaytubeUser payer = new PaytubeUser();
		payer.setFirstName("Nishant");
		payer.setLastName("Rayan");
		PaytubeUser payee = new PaytubeUser();
		payee.setFirstName("Maitreyee");
		payee.setLastName("Korgaonkar");
		PaytubeTransaction transaction = new PaytubeTransaction();
		transaction.setPayee(payee);
		transaction.setPayer(payer);
		transaction.setAmount(120.0);
		transaction.setPaymentEvent(event);
		paytubeTransactionDAO.saveTransaction(transaction);
		tearDown();
	}

}
