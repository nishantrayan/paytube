package com.rayan.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PaytubePaymentEvent implements IsSerializable {
	private Long eventId;
	private String place;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
