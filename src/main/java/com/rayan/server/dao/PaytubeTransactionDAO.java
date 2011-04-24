package com.rayan.server.dao;

import com.rayan.shared.PaytubeTransaction;

public interface PaytubeTransactionDAO {
	void saveTransaction(PaytubeTransaction transaction);
}
