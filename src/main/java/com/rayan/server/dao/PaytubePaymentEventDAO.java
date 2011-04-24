package com.rayan.server.dao;

import com.rayan.shared.PaytubePaymentEvent;

public interface PaytubePaymentEventDAO {
	void savePaymentEvent(PaytubePaymentEvent event);

}
