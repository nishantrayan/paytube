package com.rayan.server.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rayan.server.dao.GenericDAO;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {
	public void save(T t) {
		getHibernateTemplate().save(t);
	}
}
