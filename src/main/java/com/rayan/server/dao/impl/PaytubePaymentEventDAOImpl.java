package com.rayan.server.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rayan.server.dao.PaytubePaymentEventDAO;
import com.rayan.shared.PaytubePaymentEvent;

public class PaytubePaymentEventDAOImpl extends HibernateDaoSupport implements
		PaytubePaymentEventDAO {

	public void savePaymentEvent(PaytubePaymentEvent event) {
		getHibernateTemplate().save(event);
	}

}
