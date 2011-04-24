package com.rayan.server.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rayan.server.dao.PaytubeTransactionDAO;
import com.rayan.shared.PaytubeTransaction;

public class PaytubeTransactionDAOImpl extends HibernateDaoSupport implements
		PaytubeTransactionDAO {

	public void saveTransaction(PaytubeTransaction transaction) {
		
		getHibernateTemplate().saveOrUpdate(transaction);
		
	}

}
