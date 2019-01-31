package io.pick5.email.service;

import javax.mail.Address;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

public class MyTransportListener implements TransportListener {

	@Override
	public void messageDelivered(TransportEvent te) {
	}

	@Override
	public void messageNotDelivered(TransportEvent te) {
		System.out.println("ERROR: " + te.getMessage());
		Address[] invalidAddresses = te.getInvalidAddresses();
		Address[] validUnsentAddresses = te.getValidUnsentAddresses();
		// make something happen
	}

	@Override
	public void messagePartiallyDelivered(TransportEvent te) {
		messageNotDelivered(te);
	}

}