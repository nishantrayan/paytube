package com.rayan.server.manager;

import com.rayan.shared.PaytubeTransaction;

public interface PaytubeTransactionManager {
	void saveTransaction(PaytubeTransaction transaction);
}
